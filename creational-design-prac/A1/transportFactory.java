abstract class transportFactory {
    public abstract transport createTransport();

    public void planDelivery(){
        transport t = createTransport();
        t.deliver();
    }
}
