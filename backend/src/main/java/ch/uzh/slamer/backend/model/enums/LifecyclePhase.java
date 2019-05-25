package ch.uzh.slamer.backend.model.enums;

public enum LifecyclePhase {
    DEFINITION("Definition"),
    NEGOTIATION("Negotiation"),
    MONITORING("Monitoring"),
    MANAGEMENT("Management"),
    TERMINATION("Termination"),
    PENALTY_ENFORCEMENT("Penalty Enforcement");

    private String phase;

    LifecyclePhase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return phase;
    }
}
