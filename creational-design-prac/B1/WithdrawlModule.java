public class WithdrawlModule {
    public void withdraw(double amount){
        Logger logger = Logger.getInstance();
        logger.log("Withdrew " + amount + "tk");
    }
}
