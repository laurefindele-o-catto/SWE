public class Main {
    public static void main(String[] args) {
        DepositModule depositClient = new DepositModule();
        WithdrawlModule withdrawClient = new WithdrawlModule();

        depositClient.deposit(500);
        withdrawClient.withdraw(100);

        System.out.println("next proof");

        Logger log1 = Logger.getInstance();
        Logger log2 = Logger.getInstance();

        System.out.println(log1 == log2);
        System.out.println("Log1 hashcode - " + log1.hashCode());
        System.out.println("Log2 hashcode - " + log2.hashCode());
    }
}
