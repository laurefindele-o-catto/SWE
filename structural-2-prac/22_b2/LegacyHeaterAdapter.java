public class LegacyHeaterAdapter implements SmartDevice{
    private final LegacyHeater heater;
    
    public LegacyHeaterAdapter(LegacyHeater heater){
        this.heater = heater;
    }

    @Override
    public void turnOn(){
        heater.startHeating();
    }

    @Override
    public void turnOff(){
        heater.stopHeating();
    }
}
