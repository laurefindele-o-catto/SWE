public class Logger{
    private static Logger instance;
    private int logCount = 0;

    private Logger(){
        System.out.println("Logger init - should appear only once");
    }

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }

        return instance;
    }

    public void log(String msg){
        logCount++;
        System.out.println("Log " + logCount + " - " + msg);
    }
}