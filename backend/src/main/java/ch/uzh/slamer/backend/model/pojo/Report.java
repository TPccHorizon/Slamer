package ch.uzh.slamer.backend.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Report {

    @JsonProperty
    private int definition;

    @JsonProperty
    private int negotiation;

    @JsonProperty
    private int monitoring;

    @JsonProperty
    private int management;

    @JsonProperty
    private int termination;

    @JsonProperty
    private int penaltyEnforcement;

    public Report() { }

    public Report(int definition, int negotiation, int monitoring, int management, int termination, int penaltyEnforcement) {
        this.definition = definition;
        this.negotiation = negotiation;
        this.monitoring = monitoring;
        this.management = management;
        this.termination = termination;
        this.penaltyEnforcement = penaltyEnforcement;
    }

    public int getManagement() { return management; }

    public int getDefinition() {
        return definition;
    }

    public int getNegotiation() {
        return negotiation;
    }

    public int getMonitoring() {
        return monitoring;
    }

    public int getTermination() {
        return termination;
    }

    public int getPenaltyEnforcement() {
        return penaltyEnforcement;
    }
}
