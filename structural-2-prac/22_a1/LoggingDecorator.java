public class LoggingDecorator extends NotificationDecorator {
    public LoggingDecorator(Notification notification){
        super(notification);
    }

    @Override
    public void send(String msg){
        System.out.println("LOGGING: Audit record saved for message delivery.");
        super.send(msg);
    }
}
