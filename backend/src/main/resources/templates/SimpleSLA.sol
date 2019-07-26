pragma solidity ^0.5.9;
//pragma experimental ABIEncoderV2;


//import './ServiceLevelObjective.sol';

contract SimpleSLA {

    SLA deployedSLA;
    event CustomerDeposit(address indexed from, uint value);
    event SLATerminated(uint timeOfTermination);
    uint secondsPerDay = 86400;



    modifier onlySP() {
        require(deployedSLA.serviceProvider == msg.sender);
        _;
    }

    modifier onlyInvolvedParties() {
        require(msg.sender == deployedSLA.customer || msg.sender == deployedSLA.serviceProvider);
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
        //Slo[] slos;
        uint price; // price for the service
        bool paid; // is it paid?  = false
        bool terminated;
        uint status;
        validityPeriod validity;
    }

    constructor(uint _price, address payable _customer, uint _daysOfValidity) public{
        require(msg.sender != _customer);
        deployedSLA.price = _price;
        deployedSLA.serviceProvider = msg.sender;
        deployedSLA.customer = _customer;
        deployedSLA.paid = false;
        deployedSLA.terminated = false;
        deployedSLA.status = 3; // Accepted
        deployedSLA.validity.daysOfValidity = _daysOfValidity;
    }
    /*

    function addUptime(uint _id, uint _price, string memory _name, string memory _type, uint _availability) public onlySP{
        deployedSLA.slos.push(new Uptime(_id, _price, _name, _type, _availability));
        deployedSLA.price += _price;
    }*/

        /* SLA gets activated on deposit */
    function deposit() public payable returns (bool) {
        require(isCustomer(msg.sender) && !deployedSLA.paid);
        require(deployedSLA.price == msg.value);
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

    function getPrice() public view returns (uint) {
        return deployedSLA.price;
    }

    function isSLAinitialized() private view returns(bool){
        return deployedSLA.serviceProvider == address(0);
    }
}