public class CommuterBuilder implements BicycleBuilder {
    private Bicycle bicycle;

    public CommuterBuilder() { this.reset(); }
    public void reset() { this.bicycle = new Bicycle(); }

    public void buildFrame() { bicycle.setFrame("Aluminum Frame"); }
    public void buildGearSystem() { bicycle.setGearSystem("Single Speed Gear"); }
    public void buildTires() { bicycle.setTireType("Road Tires"); }

    public Bicycle getResult() {
        Bicycle finishedBike = this.bicycle;
        this.reset(); // Clear state so builder can be reused
        return finishedBike;
    }
}