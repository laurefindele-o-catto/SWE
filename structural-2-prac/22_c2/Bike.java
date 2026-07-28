public class Bike implements Transport {
    @Override
    public void loadCargo(String id) {
        System.out.println("Securing order " + id + " into courier backpack.");
    }

    @Override
    public void navigateToDestination(String addr) {
        System.out.println("Bike cutting through city traffic to " + addr + ".");
    }

    @Override
    public double getBaseTransportCost() {
        return 40.0;
    }
}
