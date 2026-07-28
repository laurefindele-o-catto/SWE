public class Main {
    public static void main(String[] args) {
        String alert = "Intruder detected in the Backyard!";

        System.out.println("--- Scenario 1: Standard SMS ---");
        Notification baseSms = new SMSNotification();
        baseSms.send(alert);

        System.out.println("\n--- Scenario 2: High Priority Encrypted Email ---");
        // Wrapping an Email with Encryption, then wrapping that inside a Priority label
        Notification enhancedEmail = new PriorityDecorator(
                new EncryptionDecorator(
                        new EmailNotification()));

        enhancedEmail.send(alert);
        System.out.println("\n--- Scenario 3: All Features Activated on Push ---");
        // Nesting all 3 optional decorators dynamically over a Push channel
        Notification maximumSecurityPush = new LoggingDecorator(
                new PriorityDecorator(
                        new EncryptionDecorator(
                                new PushNotification())));
        maximumSecurityPush.send(alert);
    }
}
