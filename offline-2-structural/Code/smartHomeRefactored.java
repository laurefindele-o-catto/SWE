import java.util.*; 

interface SmartDevice {
    void activate();
    void deactivate();
    double getPowerUsage();
    String getStatus();
    Class<?> getDeviceType();
}


class SmartLight implements SmartDevice {
    private boolean active = false;

    @Override
    public void activate() {
        this.active = true;
    }

    @Override
    public void deactivate() {
        this.active = false;
    }

    @Override
    public double getPowerUsage() {
        if(active)
            return 10.0;
        else
            return 0;
    }

    @Override
    public String getStatus() {
        return "Light: " + (active ? "ON" : "OFF");
    }

    @Override public Class<?> getDeviceType() { return this.getClass(); }
}

class SmartThermostat implements SmartDevice {
    private boolean active = false;

    @Override
    public void activate() {
        this.active = true;
    }

    @Override
    public void deactivate() {
        this.active = false;
    }

    @Override
    public double getPowerUsage() {
        if(active)
            return 150.0;
        else
            return 0;
    }

    @Override
    public String getStatus() {
        return "Thermostat: " + (active ? "ON" : "OFF");
    }

    @Override public Class<?> getDeviceType() { return this.getClass(); }
}

class SmartSpeaker implements SmartDevice {
    private boolean active = false;

    @Override
    public void activate() {
        this.active = true;
    }

    @Override
    public void deactivate() {
        this.active = false;
    }

    @Override
    public double getPowerUsage() {
        if(active)
            return 5.0;
        else
            return 0;
    }

    @Override
    public String getStatus() {
        return "Speaker: " + (active ? "ON" : "OFF");
    }

    @Override public Class<?> getDeviceType() { return this.getClass(); }
}


abstract class DeviceCollection implements SmartDevice {
    String name;
    List<SmartDevice> children = new ArrayList<>();

    DeviceCollection(String str){
        this.name = str;
    }

    public List<SmartDevice> getChildren(){
        return this.children;
    }

    public void activate(){
        for(SmartDevice child: children){
            child.activate();
        }
    }

    public void deactivate(){
        for(SmartDevice child: children){
            child.deactivate();
        }
    }

    public double getPowerUsage(){
        double total = 0.0;

        for(SmartDevice child:children){
            total += child.getPowerUsage();
        }

        return total;
    }

    // public String getStatus(){
    //     StringBuilder s = new StringBuilder("");
    // } //individually kori, ekhane na kore

    @Override public Class<?> getDeviceType() { return this.getClass(); }
}

class Room extends DeviceCollection {
    public Room(String name){
        super(name);
    }

    public String getStatus(){
        StringBuilder s = new StringBuilder("Room - " + this.name);
        
        for(SmartDevice child: this.children){
            s.append("\n    ").append(child.getStatus().replace("\n", "\n    "));
        }

        return s.toString();
    }

    public void addDevice(SmartDevice device){
        this.children.add(device);
    }
}


class Home extends DeviceCollection {
    public Home(String name){
        super(name);
    }

    public String getStatus(){
        StringBuilder s = new StringBuilder("Home - " + this.name);
        
        for(SmartDevice child: this.children){
            s.append("\n    ").append(child.getStatus().replace("\n", "\n    "));
        }

        return s.toString();
    }

    public void addRoom(SmartDevice room){
        this.children.add(room);
    }
}

abstract class SmartDeviceDecorator implements SmartDevice {
    SmartDevice decoratedDevice;

    public SmartDeviceDecorator(SmartDevice device){
        this.decoratedDevice = device;
    }

    public void activate(){
        decoratedDevice.activate();
    }

    public void deactivate(){
        decoratedDevice.deactivate();
    }

    public double getPowerUsage() {
        return decoratedDevice.getPowerUsage();
    }

    public String getStatus() {
        return decoratedDevice.getStatus();
    }

    public SmartDevice getDecoratedDevice() {
        return decoratedDevice;
    }

    @Override
    public Class<?> getDeviceType() {
        return decoratedDevice.getDeviceType();
    }
}

class AccessRestricted extends SmartDeviceDecorator{
    private final int pin;
    private boolean locked = true;

    public AccessRestricted(SmartDevice device, int pin) {
        super(device);
        this.pin = pin;
    }

    public void unlock(int code){
        if(code == this.pin)
        {
            this.locked = false;
        }
    }
    
    public void lock(){
        this.locked = true;
    }

    @Override
    public void activate(){
        if(!locked){
            decoratedDevice.activate();
        }
    }

    @Override
    public void deactivate(){
        if(!locked){
            decoratedDevice.deactivate();
        }
    }

    @Override
    public double getPowerUsage() {
        return decoratedDevice.getPowerUsage();
    }

    @Override
    public String getStatus() {
        String base = decoratedDevice.getStatus();
        return locked ? base + " [LOCKED]" : base;
    }

    @Override
    public Class<?> getDeviceType() {
        return decoratedDevice.getDeviceType();
    }
}

class TimerControlled extends SmartDeviceDecorator{
    private final int seconds;
    private boolean running = false;

    public TimerControlled(SmartDevice device, int seconds){
        super(device);
        this.seconds = seconds;
    }

    @Override
    public void activate() {
        decoratedDevice.activate();
        running = true;
    }

    @Override
    public void deactivate() {
        decoratedDevice.deactivate();
        running = false;  
    }

    public void simulateTimerExpiry(){
        if(running){
            decoratedDevice.deactivate();
            running = false;
        }
    }

    @Override
    public String getStatus() {
        String base = decoratedDevice.getStatus();
        return running ? base + " [auto-off in " + seconds + "s]" : base;
    }
}

class PowerThrottled extends SmartDeviceDecorator {
    private final double throttledPower;

    public PowerThrottled(SmartDevice device, double throttledPower){
        super(device);
        this.throttledPower = throttledPower;
    }

    @Override
    public double getPowerUsage(){
        return Math.min(decoratedDevice.getPowerUsage(), throttledPower);
    }

    @Override
    public String getStatus(){
        return decoratedDevice.getStatus() + " [throttled to " + throttledPower + "W]";
    }
}


class EcoMode extends SmartDeviceDecorator{
    private final DeviceCollection deviceCollection;
    private final double budget;

    public EcoMode(DeviceCollection collection, double budget){
        super(collection);
        this.deviceCollection = collection;
        this.budget = budget;
    }

    @Override
    public void activate(){
        deviceCollection.activate();
        reduceToBudget();
    }

    private void reduceToBudget(){
        List<SmartDevice> children = deviceCollection.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            if (deviceCollection.getPowerUsage() <= budget) {
                break;
            }
            children.get(i).deactivate();   
        }
    }

    @Override
    public double getPowerUsage() {
        return deviceCollection.getPowerUsage();
    }

    @Override
    public String getStatus() {
        return deviceCollection.getStatus() + "\n    [EcoMode budget: " + budget + "W]";
    }
}

class GuestMode extends SmartDeviceDecorator{
    private final DeviceCollection collection;
    private final Set<Class<?>> allowedTypes;
    public GuestMode(DeviceCollection collection, Set<Class<?>> allowedTypes) {
        super(collection);
        this.collection = collection;
        this.allowedTypes = allowedTypes;
    }

    private boolean isAllowed(SmartDevice child) {
        return allowedTypes.contains(child.getDeviceType());
    }

    @Override
    public void activate() {
        for (SmartDevice child : collection.getChildren()) {
            if (isAllowed(child)) {
                child.activate();
            }
        }
    }

    @Override
    public void deactivate() {
        for (SmartDevice child : collection.getChildren()) {
            if (isAllowed(child)) {
                child.deactivate();
            }
        }
    }

    @Override
    public double getPowerUsage() {
        double total = 0.0;
        for (SmartDevice child : collection.getChildren()) {
            if (isAllowed(child)) {
                total += child.getPowerUsage();
            }
        }
        return total;
    }

    @Override
    public String getStatus() {
        StringBuilder s = new StringBuilder(collection.getStatus());
        boolean anyRestricted = false;
        for (SmartDevice child : collection.getChildren()) {
            if (!isAllowed(child)) {
                anyRestricted = true;
                break;
            }
        }
        if (anyRestricted) {
            s.append("\n    [guest-restricted devices present]");
        }
        return s.toString();
    }
}


public class smartHomeRefactored {
    public static void main(String[] args) {
        System.out.println("Refactored this");
    }
}
