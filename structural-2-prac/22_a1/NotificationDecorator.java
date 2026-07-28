public abstract class NotificationDecorator implements Notification{
    Notification wrapee;

    public NotificationDecorator(Notification notification){
        this.wrapee = notification;
    }

    @Override
    public void send(String msg){
        wrapee.send(msg);
    }
}
