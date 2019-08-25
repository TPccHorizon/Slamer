pragma solidity ^0.5.9;
//pragma experimental ABIEncoderV2;


import './ServiceLevelObjective.sol';

contract SimpleSLA {

    SLA deployedSLA;
    event CustomerDeposit(address indexed from, uint value);
    event ContractCreated();
    event ContractComplete();
    event SLATerminated(uint timeOfTermination);
    uint secondsPerDay = 86400;



    modifier onlySP() {
        require(deployedSLA.serviceProvider == msg.sender, "Only the Service Provider can call this function");
        _;
    }

    modifier onlyInvolvedParties() {
        require(msg.sender == deployedSLA.customer || msg.sender == deployedSLA.serviceProvider, "Only the involved parties can call this function");
        _;
    }

    modifier onlyCustomer() {
        require(msg.sender == deployedSLA.customer, "Only the customer can call this function");
        _;
    }

    modifier onlyMonitoringService() {
        require(msg.sender == deployedSLA.monitoringService, "Only the Monitoring Service can call this function");
        _;
    }

    struct validityPeriod {
        uint startTime; // timestamp (seconds)
        uint endTime;
        uint daysOfValidity; // days
    }

    struct SLA{
        address payable serviceProvider;
        address payable customer;
        address monitoringService;
        mapping(uint => Slo) slos;
        uint price; // price for the service
        bool paid; // is it paid?  = false
        bool terminated;
        uint status;
        validityPeriod validity;
    }

    constructor(address payable _customer, address _monitoringService,uint _price, uint _daysOfValidity) public{
        require(msg.sender != _customer, "The SP must not be the customer");
        require(msg.sender != _monitoringService, "The SP must not be the monitoring service");
        deployedSLA.serviceProvider = msg.sender;
        deployedSLA.customer = _customer;
        deployedSLA.monitoringService = _monitoringService;
        deployedSLA.price = _price;
        deployedSLA.paid = false;
        deployedSLA.terminated = false;
        deployedSLA.validity.daysOfValidity = _daysOfValidity;
        emit ContractCreated();
    }


    function addUptime(uint _id, uint _availability) public onlySP{
        deployedSLA.slos[_id] = new Uptime(_id, _availability);
    }

    function addAvrgResponseTime(uint _id, uint _responseTime) public onlySP {
        deployedSLA.slos[_id] = new AvrgResponseTime(_id, _responseTime);
    }

        /* SLA gets activated on deposit */
    function deposit() public payable onlyCustomer returns (bool) {
        require(!isActive(), "The contract must not be active yet");
        require(deployedSLA.price == msg.value, "The value must equal the SLA price");
        require(deployedSLA.status == 3, "The SLA must have status 3 (Accepted)");
        deployedSLA.paid = true;
        deployedSLA.validity.startTime = now;
        deployedSLA.validity.endTime = now + (deployedSLA.validity.daysOfValidity * secondsPerDay);
        emit CustomerDeposit(msg.sender, msg.value);
        deployedSLA.status = 5; // Active
        return true;
    }

    function getCurrentBalance() public view returns (uint) {
        return address(this).balance;
    }

    // is called from external source
    function terminateSLA() public {
        deployedSLA.terminated = true;
        if (isStillInValidity()) {
            compensateCustomer();
        }
        deployedSLA.serviceProvider.transfer(getCurrentBalance());
        deployedSLA.status = 6; // set INACTIVE
        emit SLATerminated(now);
    }

    function compensateCustomer() public payable{
        uint remainingTime = deployedSLA.validity.endTime - block.timestamp;
        uint total = deployedSLA.validity.endTime - deployedSLA.validity.startTime;
        uint factor = ((100* remainingTime)/ total);
        uint compensationValue = (factor * getCurrentBalance())/100;
        deployedSLA.customer.transfer(compensationValue);
    }

    function verifyAverageResponseTime(uint _sloId, uint _measured) public onlyMonitoringService {
        checkValidity();
        Slo avrgResTime = deployedSLA.slos[_sloId];
        if (!avrgResTime.verify(_measured)) {
            terminateSLA();
        }
    }

    function confirmComplete() public onlySP {
        deployedSLA.status = 3; // Accepted
        emit ContractComplete();
    }

    function checkValidity() private {
        if (isActive()) {
            return;
        }
        terminateSLA();
    }

    function isStillInValidity() public view returns (bool) {
        return deployedSLA.validity.startTime < block.timestamp && block.timestamp <= deployedSLA.validity.endTime;
    }

    function isActive() public view returns (bool) {
        return !deployedSLA.terminated && deployedSLA.status != 6 && isStillInValidity();
    }


    function getStartTime() public view returns (uint) {
        return deployedSLA.validity.startTime;
    }

    function getEndTime() public view returns (uint) {
        return deployedSLA.validity.endTime;
    }

    function isCustomer(address _addr) public view returns (bool) {
        return deployedSLA.customer == _addr;
    }

    function getCustomer() public view returns (address) {
        return deployedSLA.customer;
    }

    function getServiceProvider() public view returns (address) {
        return deployedSLA.serviceProvider;
    }

    function getMonitoringService() public view returns (address) {
        return deployedSLA.monitoringService;
    }

    function getPrice() public view returns (uint) {
        return deployedSLA.price;
    }

    function isSLAinitialized() private view returns(bool){
        return deployedSLA.serviceProvider == address(0);
    }

    function isSLOpresent(uint _id) public view returns (bool) {
        return deployedSLA.slos[_id].getId() != 0;
    }

    // is only used for simulating an expiration of the SLA
    function expireSLA() public {
        deployedSLA.validity.endTime = block.timestamp - 1;
    }


}