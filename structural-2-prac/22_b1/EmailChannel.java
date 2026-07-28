public class EmailChannel implements Channel {
    public void send(String msg){
        System.out.println("Sending email - " + msg);
    }    
}
