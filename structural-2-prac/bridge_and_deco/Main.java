// === EXISTING BRIDGE IMPLEMENTOR INTERFACE ===
interface Channel {
    void send(String message);
}

// === EXISTING CONCRETE IMPLEMENTORS ===
class EmailChannel implements Channel {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SMSChannel implements Channel {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// === NEW DECORATOR COMPONENT ===
// Abstract Decorator implements the same interface and holds a wrapper reference
abstract class ChannelDecorator implements Channel {
    protected Channel decoratedChannel;

    public ChannelDecorator(Channel channel) {
        this.decoratedChannel = channel;
    }

    @Override
    public void send(String message) {
        decoratedChannel.send(message); // Delegate to base behavior
    }
}

// === CONCRETE DECORATOR ===
// Adds the ability to attach an extra channel dynamically
class MultiChannelDecorator extends ChannelDecorator {
    private Channel extraChannel;

    public MultiChannelDecorator(Channel baseChannel, Channel extraChannel) {
        super(baseChannel);
        this.extraChannel = extraChannel;
    }

    @Override
    public void send(String message) {
        super.send(message);        // Sends via the primary base channel
        extraChannel.send(message); // Dynamically sends via the extra channel
    }
}

// === EXISTING BRIDGE ABSTRACTION ===
abstract class Notification {
    protected Channel channel;

    protected Notification(Channel channel) {
        this.channel = channel;
    }

    public abstract void alertUser();
}

// === EXISTING REFINED ABSTRACTION ===
class BazarConfirmedNotification extends Notification {
    public BazarConfirmedNotification(Channel channel) {
        super(channel);
    }

    @Override
    public void alertUser() {
        channel.send("Your monthly bazar order has been successfully confirmed!");
    }
}

// === DEMONSTRATION ===
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Standard Single Channel ---");
        // Traditional Bridge: Single event to a single channel
        Notification standardNotification = new BazarConfirmedNotification(new EmailChannel());
        standardNotification.alertUser();

        System.out.println("\n--- Scenario 2: Layered Decorator (Multi-Channel) ---");
        // Layering Decorator: Wrap Email with an extra SMS channel
        Channel multiChannel = new MultiChannelDecorator(new EmailChannel(), new SMSChannel());
        
        // Pass the decorated channel seamlessly across the Bridge
        Notification VIPNotification = new BazarConfirmedNotification(multiChannel);
        VIPNotification.alertUser();
    }
}
