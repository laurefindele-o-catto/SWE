public class ConfirmNotification extends Notification {
    public ConfirmNotification(Channel channel){
        super(channel);
    }

    public void alertUser(){
        channel.send("Your bazar is successfully confirmed.");
    }
}
