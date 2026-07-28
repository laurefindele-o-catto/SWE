public class DepositModule {
    public void deposit(double amount){
        Logger logger = Logger.getInstance();
        logger.log("Deposited " + amount + "tk");
    }
}
