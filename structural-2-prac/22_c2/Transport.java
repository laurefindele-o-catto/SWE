public interface Transport {
    void loadCargo(String orderId);
    void navigateToDestination(String address);
    double getBaseTransportCost();
}
