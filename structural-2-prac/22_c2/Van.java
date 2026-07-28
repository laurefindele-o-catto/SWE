public class Van implements Transport{
    @Override
    public void loadCargo(String id) {
        System.out.println("Loading large crate for order " + id + " into van cargo bay.");
    }

    @Override
    public void navigateToDestination(String addr) {
        System.out.println("Van navigating main roads to " + addr + ".");
    }

    @Override
    public double getBaseTransportCost() {
        return 120.0;
    }
}
