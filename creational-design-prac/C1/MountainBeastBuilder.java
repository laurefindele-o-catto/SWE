// Concrete Builder 2: The Mountain Beast
public class MountainBeastBuilder implements BicycleBuilder {
    private Bicycle bicycle;

    public MountainBeastBuilder() { this.reset(); }
    public void reset() { this.bicycle = new Bicycle(); }

    public void buildFrame() { bicycle.setFrame("Carbon Fiber Frame"); }
    public void buildGearSystem() { bicycle.setGearSystem("12-Speed Gear"); }
    public void buildTires() { bicycle.setTireType("Off-road Grip Tires"); }

    public Bicycle getResult() {
        Bicycle finishedBike = this.bicycle;
        this.reset();
        return finishedBike;
    }
}
