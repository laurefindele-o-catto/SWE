public class EmailNotification implements Notification {
    public void send(String msg){
        System.out.println("Sending email - " + msg);
    }
}
