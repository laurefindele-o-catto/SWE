public class PriorityDecorator extends NotificationDecorator {
    public PriorityDecorator(Notification notification){
        super(notification);
    }

    @Override
    public void send(String msg){
        System.out.print("High Priority!!!");
        super.send(msg);
    }
}
