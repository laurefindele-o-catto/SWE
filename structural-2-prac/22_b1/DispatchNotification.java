public class DispatchNotification extends Notification {
    public DispatchNotification(Channel channel){
        super(channel);
    }

    public void alertUser(){
        channel.send("Your bazar has been dispatched.");
    }
}
