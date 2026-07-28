public interface BicycleBuilder {
    void reset();
    void buildFrame();
    void buildGearSystem();
    void buildTires();
    Bicycle getResult();
}
