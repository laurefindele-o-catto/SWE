public class StandardDelivery extends Delivery {
    public StandardDelivery(Transport transportMode) { super(transportMode); }

    @Override
    public void processOrder(String orderId, String address) {
        System.out.println("\n--- Processing Standard Delivery (Within 24 Hours) ---");
        transportMode.loadCargo(orderId);
        transportMode.navigateToDestination(address);
    }

    @Override
    public double calculateTotalCost(double orderWeight) {
        // Standard delivery multiplies cost moderately based on weight + flat base transport rate
        return transportMode.getBaseTransportCost() + (orderWeight * 10.0);
    }
}
