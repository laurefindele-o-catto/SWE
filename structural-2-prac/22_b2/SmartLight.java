class SmartLight implements SmartDevice {
    private final String name;
    public SmartLight(String name) { this.name = name; }

    @Override
    public void turnOn() { System.out.println(name + " is now ON."); }
    @Override
    public void turnOff() { System.out.println(name + " is now OFF."); }
}