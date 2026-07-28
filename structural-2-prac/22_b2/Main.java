public class Main {
    public static void main(String[] args) {
        // App collection holding all devices uniformly via the SmartDevice interface
        java.util.List<SmartDevice> homeDevices = new java.util.ArrayList<>();

        // Adding standard native devices
        homeDevices.add(new SmartLight("random smart light"));

        // Integrating third-party devices using their respective adapters
        homeDevices.add(new OldSmartBulbAdapter(new OldSmartBulb()));
        homeDevices.add(new LegacyHeaterAdapter(new LegacyHeater()));

        System.out.println("--- Activating All Devices via Smart Home App ---");
        for (SmartDevice device : homeDevices) {
            device.turnOn();
        }

        System.out.println("\n--- Deactivating All Devices via Smart Home App ---");
        for (SmartDevice device : homeDevices) {
            device.turnOff();
        }



        //testing composites

        SmartDevice kitchenLight = new SmartLight("Kitchen Ceiling Light");
        SmartDevice livingRoomBulb = new OldSmartBulbAdapter(new OldSmartBulb());
        SmartDevice basementHeater = new LegacyHeaterAdapter(new LegacyHeater());

        
        DeviceGroup livingRoom = new DeviceGroup("Living Room");
        livingRoom.addDevice(livingRoomBulb);
        livingRoom.addDevice(new SmartLight("Living Room Accent Strip"));

        DeviceGroup wholeHouse = new DeviceGroup("Entire Smart Home");
        wholeHouse.addDevice(kitchenLight);     
        wholeHouse.addDevice(basementHeater); 
        wholeHouse.addDevice(livingRoom);

        System.out.println("--- Scenario 1: Controlling a Single Group (Living Room) ---");
        livingRoom.turnOn();

         System.out.println("\n--- Scenario 2: Controlling the Master Composite (Whole House) ---");
        wholeHouse.turnOff();
    }
}
