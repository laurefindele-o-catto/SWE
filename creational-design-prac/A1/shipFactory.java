public class shipFactory extends transportFactory {
    public transport createTransport(){
        return new shipTransport();
    }
}
