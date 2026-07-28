public abstract class Delivery {
    protected Transport transportMode;

    protected Delivery(Transport transportMode) {
        this.transportMode = transportMode;
    }

    public abstract void processOrder(String orderId, String address);
    public abstract double calculateTotalCost(double orderWeight);
}
