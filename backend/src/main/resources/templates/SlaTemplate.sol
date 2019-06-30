pragma solidity ^0.5.9;
pragma experimental ABIEncoderV2;


contract SlaTemplate {

    event CustomerDeposit(address indexed from, uint value);
    event WrongDeposit(address indexed _from, uint _value);
    event CompensationOccured(address indexed _from, uint _value);
    event SLAViolated(address indexed from, uint value);
    event MonitorVerfied();
    event PerformanceLevelChanged();

    //structs
    struct validityPeriod {
        uint createdAt; // timestamp (seconds)
        uint startTime; // timestamp (seconds)
        uint endTime;
        uint validity; // days
    }
    struct slaStruct {
        string service;
        address payable serviceProvider;
        address payable customer;
        address monitoringService;
        uint price; // price for the service
        bool paid; // is it paid?  = false
        uint compensationPeriod; // interval for compensation (seconds)
        uint compensationValue; //value for compensation
        uint currentCompensationInterval; //current interval of compensation
        uint monitoringPeriod; //interval for compensation (seconds)  = 5 minutes
        uint violationCount; // how many times the SLA was violated in the Interval  = 0
        uint totalExpectedMeasuraments;
        validityPeriod period;
    }

    slaStruct private currentSla;
    address private serviceProviderAddress;

    bool isTerminated = false;
    bool isDefined = false;
    bool isLive = false;

    function isSP(address _sender) private view returns (bool) {
        return _sender == currentSla.serviceProvider;
    }

    function isCustomer(address _sender) private view returns (bool){
        return _sender == currentSla.customer;
    }

    function isMonitor(address _sender) private view returns (bool){
        return _sender == currentSla.monitoringService;
    }

    function isSlaFinished() public returns (bool) {
        if (block.timestamp >= currentSla.period.endTime) {
            terminateSLA();
            return true;
        }
        return false;
    }
     // SP is the owner
    constructor() public {
        serviceProviderAddress = msg.sender;
    }

    function newSLA(string memory _service,
                    uint _price,
                    uint _monitoringPeriod,
                    uint _validity,
                    address _monitoringService) public returns (bytes32) {
        require(isSP(msg.sender)); // Only the SP can create the SLA
        require(!isDefined); // Must not be defined yet

        currentSla.service = _service;
        currentSla.price = _price;
        currentSla.paid = false;
        currentSla.currentCompensationInterval = 1;
        currentSla.monitoringPeriod = _monitoringPeriod;
        currentSla.violationCount = 0;

        currentSla.period = validityPeriod({createdAt: block.timestamp, validity: _validity, startTime: 0, endTime: 0});
        isDefined = true;
        currentSla.monitoringService = _monitoringService;
    }

    //todo check this logic
  /* function setViolation(uint _level) public returns (bool) {
        require(monitorVerified());
        require(isMonitor(msg.sender));
        require(!isTerminated);
        //change to compensante when the period finishes
        if (block.timestamp <= currentSla.period.startTime + (currentSla.compensationPeriod * currentSla.currentCompensationInterval)) {
            //carry on with normal operation
        }
        else {
            currentSla.violationCount = 0;
            currentSla.currentCompensationInterval++;
        }
        //check if the period
        if (currentSla.currentPerfomanceLevel != _level)
            currentSla.currentPerfomanceLevel = _level;
        currentSla.violationCount = currentSla.violationCount + 1;
        uint periodViolated = ((currentSla.violationCount * 100)/ (currentSla.totalExpectedMeasuraments));
        if (periodViolated > currentSla.compensationThreshold) {
            compensateCustomer();
            currentSla.violationCount = 0;
        }
        isSlaFinished();
    }*/
    function checkCompensationPayment() public returns (bool) {
        if (block.timestamp <= currentSla.period.startTime + (currentSla.compensationPeriod * currentSla.currentCompensationInterval)) {
            return false;
        }

        currentSla.currentCompensationInterval++;
        return true;
    }
    function setViolation(uint _level) public returns (bool) {
        require(isMonitor(msg.sender));
        //require(!isTerminated);
        if (isTerminated)
        {
            return false;
        }
        //change to compensante when the period finishes
        //check if the period
        currentSla.violationCount = currentSla.violationCount + 1;
        if (block.timestamp < (currentSla.period.startTime + (currentSla.compensationPeriod * currentSla.currentCompensationInterval))) {
            //carry on with normal operation
        }
        isSlaFinished();
    }

    function compensateCustomer() public payable returns (bool) {
        if (currentSla.serviceProvider.balance >= currentSla.compensationValue) {
            currentSla.customer.transfer(currentSla.compensationValue);
        }
        return true;
    }
    /* Setter Functions */
    function setCustomer(address payable _customer) public returns (bool) {
        require(isSP(msg.sender));
        currentSla.customer = _customer;
        return true;
    }

    function setMonitor(address _monitor) public returns (bool) {
        currentSla.monitoringService = _monitor;
    }

    /* Getter Functions */
    function getBlockTimestamp() public view returns (uint) {
        return block.timestamp;
    }
    function getSLATerminated() public view returns (bool) {
        return isTerminated;
    }
    function getCompensationTimestamp() public view returns (uint) {
        return (currentSla.period.startTime + (currentSla.compensationPeriod * currentSla.currentCompensationInterval));
    }

    function getSLA() public view returns (slaStruct memory) {
        return currentSla;
    }
    /* Remove in Production */
    function getOwner() public view returns (address) {
        return serviceProviderAddress;
    }
    function getCompensationValue() public view returns (uint) {
        return currentSla.compensationValue;
    }
    function getViolatedPeriod() public view returns (uint) {
        return (currentSla.violationCount * 100) / (currentSla.totalExpectedMeasuraments);
    }
    function getCurrentCompensationPeriod() public view returns (uint) {
        return currentSla.currentCompensationInterval;
    }
    function getMonitor() public view returns (address) {
        return currentSla.monitoringService;
    }
    function getCreatedAt() public view returns (uint) {
        return currentSla.period.createdAt;
    }
    function getStartTime() public view returns (uint) {
        return currentSla.period.startTime;
    }
    function getEndTime() public view returns (uint) {
        return currentSla.period.endTime;
    }
    function getCompensationPeriod() public view returns (uint) {
        return currentSla.compensationPeriod;
    }
    function getTotalMeasurements() public view returns (uint) {
        return currentSla.totalExpectedMeasuraments;
    }
    function getValidity() public view returns (uint) {
        return currentSla.period.validity;
    }
    function getViolationCount() public view returns (uint) {
        return currentSla.violationCount;
    }

    //
    function getSLAPrice() public view returns (uint) {
        require (isSP(msg.sender) || isCustomer(msg.sender));
        return currentSla.price;
    }


    /* Payment Functions */
    function deposit() public payable returns (bool) {
        require(isCustomer(msg.sender) && (msg.value == currentSla.price) && !currentSla.paid);
        currentSla.paid = true;
        currentSla.period.startTime = block.timestamp;
        currentSla.period.endTime = block.timestamp + currentSla.period.validity;
        emit CustomerDeposit(msg.sender, msg.value);
        return true;
    }
    //production
    //function terminateSLA() private {
    //test
    function terminateSLA() public {
        isTerminated = true;
        //verificar se terminou mesmo (com a funcao existente) e verificar se eh o endereco do SP ou do client pra interagir
        currentSla.serviceProvider.transfer(address(this).balance); //deposit all the remaining value
    }

}
