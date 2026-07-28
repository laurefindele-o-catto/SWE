public class WorkproFactory implements pcFactory{

    @Override
    public Processor createProcessor() {
        return new IntelProcesssor();
    }

    @Override
    public Display createDisplay() {
        return new IPSDisplay();
    }
    
}
