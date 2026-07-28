public class Main {
    public static void main(String[] args) {
        String targetAddress = "Plot 12, Road 4, Uttara, Dhaka";
        
        // Scenario 1: Standard order using a legacy Van
        Delivery standardVanOrder = new StandardDelivery(new Van());
        standardVanOrder.processOrder("ZB-1001", targetAddress);
        System.out.println("Total Delivery Cost: ৳" + standardVanOrder.calculateTotalCost(15.5));

        // Scenario 2: Express urgent order dispatched via the brand-new Drone
        // Look how cleanly Drone binds to the existing Express option without modifying ExpressDelivery!
        Delivery urgentDroneOrder = new ExpressDelivery(new Drone());
        urgentDroneOrder.processOrder("ZB-9909", targetAddress);
        System.out.println("Total Delivery Cost: ৳" + urgentDroneOrder.calculateTotalCost(2.0));
    }
}
