package ch.uzh.slamer.backend.model.enums;

public enum LifecyclePhase {
    DEFINITION("Definition"),
    NEGOTIATION("Negotiation"),
    DEPLOYMENT("Deployment"),
    MONITORING("Monitoring"),
    MANAGEMENT("Management"),
    TERMINATION("Termination");

    private String phase;

    LifecyclePhase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return phase;
    }
}
