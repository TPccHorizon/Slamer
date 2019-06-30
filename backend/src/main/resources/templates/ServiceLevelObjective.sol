pragma solidity ^0.5.9;

interface Verifiable {
    function verify(uint _measured) external returns (bool);
    function calculateCompensation(uint _divergence) external returns (uint);
}

contract Slo is Verifiable{
        uint id;
        uint price;
        string name;
        string sloType;
        uint violations;
        uint compensation;

        constructor(uint _id, uint _price, string memory _name, string memory _type) public {
            id = _id;
            price = _price;
            name = _name;
            sloType = _type;
            violations = 0;
            compensation = 0;
        }


}

contract Uptime is Slo {
    uint percentageOfAvailability;

    constructor(uint _id, uint _price, string memory _name, string memory _type, uint _availability)
                Slo(_id, _price, _name, _type)
                public {
        percentageOfAvailability = _availability;
    }

    function verify(uint _measured) public returns (bool) {
        uint delta = percentageOfAvailability - _measured;
        if (delta < 0) {
            violations++;
            compensation += calculateCompensation(delta);
            return false;
        }
        return true;
    }

    function calculateCompensation(uint _divergence) public returns (uint) {
        return (_divergence * 100 / percentageOfAvailability) * price;
    }

}

contract Throughput is Slo {
        string dataUnit;
        uint dataSize;
        string operator;
        uint thresholdValue;
        string timeUnit;
}

contract AvrgResponseTime is Slo {
        string operator;
        uint avrgResponseTimeValue;
        string timeUnit;
}