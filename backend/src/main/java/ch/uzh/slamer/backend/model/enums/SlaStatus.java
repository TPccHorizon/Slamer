package ch.uzh.slamer.backend.model.enums;

public enum SlaStatus {
    IDENTIFIED("Identified"),
    REQUESTED("Requested"),
    INACTIVE("Inactive"),
    TERMINATED("Terminated");

    private String status;

    SlaStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
