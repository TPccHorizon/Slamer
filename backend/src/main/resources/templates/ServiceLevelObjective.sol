pragma solidity ^0.5.9;

interface Verifiable {
    function verify(uint _measured) external returns (bool);
}

contract Slo is Verifiable{
        uint id;
        uint violations;

        uint maxViolations = 5;

        event SloAdded();
        event Violated();

        constructor(uint _id) public {
            id = _id;
            violations = 0;
        }

        function getViolations() public view returns (uint) {
            return violations;
        }

        function getId() public view returns (uint) {
            return id;
        }

}

contract Uptime is Slo {
    uint percentageOfAvailability;

    constructor(uint _id, uint _availability)
                Slo(_id)
                public {
        percentageOfAvailability = _availability;
        emit SloAdded();
    }

    function verify(uint _measured) public returns (bool) {
        if (percentageOfAvailability - _measured < 0) {
            violations++;
            if (violations >= maxViolations) {
                emit Violated();
            }
            return false;
        }
        return true;
    }


}

contract Throughput is Slo {

        uint dataSize;
        uint thresholdValue;

        constructor(uint _id, uint _dataSize, uint _thresholdValue) Slo(_id) public {
            dataSize = _dataSize;
            thresholdValue = _thresholdValue;
        }

        function verify(uint _measured) public returns (bool) {
            // verification logic
        }
}

contract AvrgResponseTime is Slo {
        uint avrgResponseTimeValue;

        constructor(uint _id, uint _responseTime)
        Slo(_id) public {
            avrgResponseTimeValue = _responseTime;
            emit SloAdded();
        }

        function verify(uint _measured) public returns (bool) {
            if (_measured > avrgResponseTimeValue) {
                violations += 1;
                if (violations >= maxViolations) {
                    emit Violated();
                    return false;
                }
            }
            return true;
        }

}