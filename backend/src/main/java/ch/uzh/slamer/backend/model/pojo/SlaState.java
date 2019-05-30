package ch.uzh.slamer.backend.model.pojo;

public class SlaState {

    private String status;
    private String phase;

    public SlaState(String status, String phase) {
        this.status = status;
        this.phase = phase;
    }

    public String getStatus() {
        return status;
    }

    public String getPhase() {
        return phase;
    }
}
