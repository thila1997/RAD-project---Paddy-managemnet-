package models;

public class PaddyStorage {
    private int paddyId;
    private double totalPaddyAmount;
    private String paddyTypeName;

    public String getPaddyTypeName() {
        return paddyTypeName;
    }

    public void setPaddyTypeName(String paddyTypeName) {
        this.paddyTypeName = paddyTypeName;
    }

    public int getPaddyId() {
        return paddyId;
    }

    public void setPaddyId(int paddyId) {
        this.paddyId = paddyId;
    }

    public double getTotalPaddyAmount() {
        return totalPaddyAmount;
    }

    public void setTotalPaddyAmount(double totalPaddyAmount) {
        this.totalPaddyAmount = totalPaddyAmount;
    }

    public PaddyStorage(String paddyTypeName, double totalPaddyAmount) {
        this.paddyTypeName=paddyTypeName;
        this.totalPaddyAmount=totalPaddyAmount;
    }
}
