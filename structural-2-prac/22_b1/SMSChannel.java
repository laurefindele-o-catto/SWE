public class SMSChannel implements Channel {
    public void send(String msg){
        System.out.println("Sending sms - " + msg);
    } 
}
