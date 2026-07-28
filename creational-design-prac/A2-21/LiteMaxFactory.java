public class LiteMaxFactory implements pcFactory{

    @Override
    public Processor createProcessor() {
        return new ARMProcessor();
    }

    @Override
    public Display createDisplay() {
        return new OLEDDisplay();
    }
    
}
