public class Bicycle {
    private String frame;
    private String gearSystem;
    private String tireType;

    // Setters used by the builders
    public void setFrame(String frame) { this.frame = frame; }
    public void setGearSystem(String gearSystem) { this.gearSystem = gearSystem; }
    public void setTireType(String tireType) { this.tireType = tireType; }

    public void showSpecifications() {
        System.out.println("Bicycle Specs -> Frame: " + frame + " | Gears: " + gearSystem + " | Tires: " + tireType);
    }
}
