public abstract class Notification {
    Channel channel;

    Notification(Channel channel){
        this.channel = channel;
    }

    public abstract void alertUser();
}


// the "Abstraction" is always the component that the client application interacts with directly to kick off an action.

// Ask yourself: What does the user (or the client code) actually hold and interact with to get the job done?

// The application code (the client) is running a checkout background job. When the order goes through, the backend code needs to trigger an alert.The Client asks: "Hey system, trigger a BazarConfirmedNotification."The Client does NOT ask: "Hey SMSChannel, please figure out what a grocery order confirmation text looks like and send it."

// "The client code will hold a [Abstraction] and call methods on it. Behind the scenes, that object will delegate the technical platform work to a [Implementation]."

// The Problem: Application developers write high-level data logic (Queries, Updates), while database vendors (Oracle, MySQL, PostgreSQL) handle the low-level network protocols and data parsing.The Bridge Solution:Abstraction: The DatabaseConnection interface used by application developers (e.g., connection.executeQuery()).Implementation: The vendor-specific DatabaseDriver (e.g., MySQL Driver, Oracle Driver).

// Example 1: The user uses a [Remote] which tells the [TV] what to do.Example 2: The app updates a [UI Widget] which tells the [OS Graphics API] how to render it.Example 3: The code runs a [Database Query Object] which tells the [Database Driver] how to talk to the server network.Example 4: The workflow triggers a [Grocery Notification Event] which tells the [Communication Channel] to deliver it.