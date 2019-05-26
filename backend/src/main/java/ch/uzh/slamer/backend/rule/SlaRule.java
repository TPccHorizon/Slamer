package ch.uzh.slamer.backend.rule;

import ch.uzh.slamer.backend.model.enums.SlaStatus;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ch.uzh.slamer.backend.model.enums.SlaStatus.*;

@Component
public class SlaRule {

    private Map<String, SlaStatus> stateMap = new HashMap<>();

    public SlaRule() {
        stateMap.put(IDENTIFIED.getStatus(), REQUESTED);
        stateMap.put(REQUESTED.getStatus(), INACTIVE);
        stateMap.put(INACTIVE.getStatus(), TERMINATED);
        stateMap.put(TERMINATED.getStatus(), null);
    }

    public String getNextState(String currentState) throws InvalidKeyException {
        SlaStatus nextState = stateMap.get(currentState);
        if (nextState == null) {
            throw new InvalidKeyException("SLA is already Terminated");
        }
        return nextState.getStatus();
    }
}
