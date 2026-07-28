public class SMSNotification implements Notification {
    public void send(String msg){
        System.out.println("Sending SMS - " + msg);
    }
}
