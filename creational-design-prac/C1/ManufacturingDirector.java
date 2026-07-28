public class ManufacturingDirector {
    // Directs the exact step-by-step construction order
    public void constructBicycle(BicycleBuilder builder) {
        builder.buildFrame();
        builder.buildGearSystem();
        builder.buildTires();
    }
}
