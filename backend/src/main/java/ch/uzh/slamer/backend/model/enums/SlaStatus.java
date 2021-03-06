package ch.uzh.slamer.backend.model.enums;

public enum SlaStatus {
    IDENTIFIED("Identified", 1),
    REQUESTED("Requested", 2),
    ACCEPTED("Accepted", 3),
    REJECTED("Rejected", 4),
    ACTIVE("Active", 5),
    INACTIVE("Inactive", 6),
    DEPLOYMENT("Deployment", 7),
    PENDING_DEPLOYMENT("Pending Deployment", 8),
    PENDING_DEPOSIT("Pending Deposit", 9),
    FAILED("Failed", 10);

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
