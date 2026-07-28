public class Drone implements Transport {
    @Override
    public void loadCargo(String id) {
        System.out.println("Locking order " + id + " to drone chassis. Initiating safety system checks.");
    }

    @Override
    public void navigateToDestination(String addr) {
        System.out.println("Drone flying via GPS coordinates to " + addr + ".");
    }

    @Override
    public double getBaseTransportCost() {
        return 250.0;
    }
}
