public class Main {
    public static void main(String[] args) {
        Notification userAconfirm = new ConfirmNotification(new EmailChannel());
        userAconfirm.alertUser();

        Notification userBDispatch = new DispatchNotification(new SMSChannel());
        userBDispatch.alertUser();
    }    
}
