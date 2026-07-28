public class notificationFactory {
    public Notification createNotification(String type){
        if(type == null || type.isEmpty())
            return null;

        switch(type.toUpperCase()){
            case "SMS":
                return new smsNotification();
            case "EMAIL":
                return new emailNotification();
            case "PUSH":
                return new pushNotification();
            default:
                throw new IllegalArgumentException("unknown type");
        }
    }
}
