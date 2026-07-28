public class PC {
    private String modelName;
    private Processor processor;
    private Display display;

    public PC(String modelName, pcFactory factory){
        this.modelName = modelName;
        this.processor = factory.createProcessor();
        this.display = factory.createDisplay();
    }

    public void describe(){
        System.out.println(modelName);
        System.out.println(processor.getProcessorSpecs());
        System.out.println(display.getDisplaySpecs());
    }
}
