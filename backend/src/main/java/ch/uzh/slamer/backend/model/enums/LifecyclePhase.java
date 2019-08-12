package ch.uzh.slamer.backend.model.enums;

public enum LifecyclePhase {
    DEFINITION("Definition"),
    ESTABLISHMENT("Establishment"),
    MONITORING("Monitoring"),
    TERMINATION("Termination"),
    PENALTY_ENFORCEMENT("PenaltyEnforcement");

    private String phase;

    LifecyclePhase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return phase;
    }
}
