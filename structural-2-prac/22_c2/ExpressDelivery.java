public class ExpressDelivery extends Delivery {
    public ExpressDelivery(Transport transportMode) {
        super(transportMode);
    }

    @Override
    public void processOrder(String orderId, String address) {
        System.out.println("\n--- Processing Express Delivery (Urgent: Within 4 Hours) ---");
        transportMode.loadCargo(orderId);
        transportMode.navigateToDestination(address);
    }

    @Override
    public double calculateTotalCost(double orderWeight) {
        // Express delivery charges an additional premium safety fee on top of standard
        // calculation
        return (transportMode.getBaseTransportCost() * 1.5) + (orderWeight * 20.0);
    }
}
