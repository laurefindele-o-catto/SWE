package model;

public class Discount {
    private final Order order;

    public Discount(Order order) {
        this.order = order;
    }

    public double getDiscount(String couponCode, int loyaltyPointsToRedeem){
        double subtotal = order.getSubtotal();
        double couponDiscount = 0.0;
        if ("WELCOME10".equals(couponCode)) {
            couponDiscount = subtotal * 0.10;
        } else if ("FAMILY15".equals(couponCode) && subtotal >= 1000.0) {
            couponDiscount = subtotal * 0.15;
        }

        double loyaltyDiscount = Math.min(loyaltyPointsToRedeem, 100);

        return couponDiscount + loyaltyDiscount;
    }
}
