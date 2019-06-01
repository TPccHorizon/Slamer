package ch.uzh.slamer.backend.model.enums;

public enum SlaStatus {
    IDENTIFIED("Identified", 1),
    REQUESTED("Requested", 2),
    IN_REVIEW("In Review", 3),
    ACTIVE("Active", 4),
    INACTIVE("Inactive", 5);

    private String status;
    private int step;

    SlaStatus(String status, int step) {
        this.status = status;
        this.step = step;
    }

    public String getStatus() {
        return status;
    }

    public int getStep() {
        return step;
    }


}
