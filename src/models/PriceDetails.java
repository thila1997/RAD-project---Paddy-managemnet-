package models;

public class PriceDetails {
    private String paddyVariety;
    private double paddyPrice;

    public PriceDetails(String paddyVariety, double paddyPrice) {
        this.paddyVariety = paddyVariety;
        this.paddyPrice = paddyPrice;
    }

    public String getPaddyVariety() {
        return paddyVariety;
    }

    public double getPaddyPrice() {
        return paddyPrice;
    }

    public void setPaddyVariety(String paddyVariety) {
        this.paddyVariety = paddyVariety;
    }

    public void setPaddyPrice(double paddyPrice) {
        this.paddyPrice = paddyPrice;
    }
}
