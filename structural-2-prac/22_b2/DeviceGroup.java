import java.util.ArrayList;
import java.util.List;

public class DeviceGroup implements SmartDevice {
    private final String groupName;
    private final List<SmartDevice> devices = new ArrayList<>();

    public DeviceGroup(String groupName) {
        this.groupName = groupName;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void removeDevice(SmartDevice device) {
        devices.remove(device);
    }

    @Override
    public void turnOn() {
        System.out.println("\n>>> [Group: " + groupName + "] Triggering Turn On Sequence <<<");
        for (SmartDevice device : devices) {
            device.turnOn(); 
        }
    }

    @Override
    public void turnOff() {
        System.out.println("\n>>> [Group: " + groupName + "] Triggering Turn Off Sequence <<<");
        for (SmartDevice device : devices) {
            device.turnOff(); 
        }
    }
}

