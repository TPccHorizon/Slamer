package ch.uzh.slamer.backend.rule;

import ch.uzh.slamer.backend.model.enums.LifecyclePhase;
import ch.uzh.slamer.backend.model.enums.SlaStatus;
import ch.uzh.slamer.backend.model.pojo.SlaState;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ch.uzh.slamer.backend.model.enums.LifecyclePhase.*;
import static ch.uzh.slamer.backend.model.enums.SlaStatus.*;

@Component
public class SlaRule {

    private Map<String, SlaStatus> stateMap = new HashMap<>();

    private Map<String, LifecyclePhase> phaseMap = new HashMap<>();

    public SlaRule() {
        stateMap.put(IDENTIFIED.getStatus(), REQUESTED);
        stateMap.put(REQUESTED.getStatus(), ACTIVE);
        stateMap.put(ACTIVE.getStatus(), INACTIVE);
        stateMap.put(INACTIVE.getStatus(), null);

        phaseMap.put(IDENTIFIED.getStatus(), NEGOTIATION);
        phaseMap.put(REQUESTED.getStatus(), MONITORING);
        phaseMap.put(ACTIVE.getStatus(), MANAGEMENT);
//        phaseMap.put(MANAGEMENT.getPhase(), );
    }

    public SlaState getNextState(String currentStatus) throws InvalidKeyException {
        SlaStatus nextStatus = stateMap.get(currentStatus);
        LifecyclePhase nextPhase = phaseMap.get(currentStatus);
        if (nextStatus == null || nextPhase == null) {
            throw new InvalidKeyException("SLA is already Terminated");
        }
//        if (nextState.equals(INACTIVE))
        return new SlaState(nextStatus.getStatus(), nextPhase.getPhase());
    }

}
