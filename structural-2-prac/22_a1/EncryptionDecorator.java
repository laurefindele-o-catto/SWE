public class EncryptionDecorator extends NotificationDecorator {
    public EncryptionDecorator(Notification notification){
        super(notification);
    }

    @Override
    public void send(String msg){
        System.out.print("ENCRYPTED: ");
        super.send(msg);
    }
}
