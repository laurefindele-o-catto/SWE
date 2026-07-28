public class Main {
    public static void main(String[] args) {
        notificationFactory factory = new notificationFactory();

        String type = "smos";

        try{
            Notification notification = factory.createNotification(type);
            notification.notifyUser();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
