package ch.uzh.slamer.backend.model.enums;

public enum SloType {
    AVERAGE_RESPONSE_TIME("Average Response Time"),
    AVAILABILITY("Availability"),
    THROUGHPUT("Throughput");

    private String type;

    SloType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
