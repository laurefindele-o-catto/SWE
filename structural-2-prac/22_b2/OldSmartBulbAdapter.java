public class OldSmartBulbAdapter implements SmartDevice{
    private final OldSmartBulb bulb;
    
    public OldSmartBulbAdapter(OldSmartBulb bulb){
        this.bulb = bulb;
    }

    @Override
    public void turnOn(){
        bulb.powerOn();
    }

    @Override
    public void turnOff(){
        bulb.powerOff();
    }
}
