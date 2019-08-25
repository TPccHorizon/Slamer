
# Service Level Objective
solc src/main/resources/templates/ServiceLevelObjective.sol --bin --abi --optimize -o src/main/java/ch.uzh.slamer.backend.solidity

web3j solidity generate -b .\src\main\resources\templates\AvrgResponseTime.bin -a src/main/resources/templates/AvrgResponseTime.abi -o src/main/java/ch.uzh.slamer.backend.solidity -p backend.slamer.backend.contracts
web3j solidity generate -b .\src\main\resources\templates\Slo.bin -a src/main/resources/templates/Slo.abi -o src/main/java/ch.uzh.slamer.backend.solidity -p backend.slamer.backend.contracts
web3j solidity generate -b .\src\main\resources\templates\Throughput.bin -a src/main/resources/templates/Throughput.abi -o src/main/java/ch.uzh.slamer.backend.solidity -p backend.slamer.backend.contracts
web3j solidity generate -b .\src\main\resources\templates\Uptime.bin -a src/main/resources/templates/Uptime.abi -o src/main/java/ch.uzh.slamer.backend.solidity -p backend.slamer.backend.contracts
web3j solidity generate -b .\src\main\resources\templates\Verifiable.bin -a src/main/resources/templates/Verifiable.abi -o src/main/java/ch.uzh.slamer.backend.solidity -p backend.slamer.backend.contracts

# simple sla
solc src/main/resources/templates/SimpleSLA.sol --bin --abi --optimize -o src/main/java/ch.uzh.slamer.backend.solidity

web3j solidity generate -b .\src\main\resources\templates\SimpleSLA.bin -a src/main/resources/templates/SimpleSLA.abi -o src/main/java/ch.uzh.slamer.backend.solidity -p backend.slamer.backend.contracts
