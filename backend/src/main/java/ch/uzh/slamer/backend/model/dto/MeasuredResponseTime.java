package ch.uzh.slamer.backend.model.dto;

public class MeasuredResponseTime {

    private double measured;

    private int sloId;

    private int slaId;

    private String wallet;

    public MeasuredResponseTime() {
    }

    public MeasuredResponseTime(double measured, int sloId, int slaId, String wallet) {
        this.measured = measured;
        this.sloId = sloId;
        this.slaId = slaId;
        this.wallet = wallet;
    }

    public double getMeasured() {
        return measured;
    }

    public void setMeasured(double measured) {
        this.measured = measured;
    }

    public int getSloId() {
        return sloId;
    }

    public void setSloId(int sloId) {
        this.sloId = sloId;
    }

    public int getSlaId() {
        return slaId;
    }

    public void setSlaId(int slaId) {
        this.slaId = slaId;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
