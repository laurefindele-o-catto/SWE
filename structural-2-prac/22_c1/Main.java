public class Main {
    public static void main(String[] args){
        System.out.println("--- Order 1: Traditional Legacy Order ---");
        RamadanPackage customer1Order = new StandardPackage();
        System.out.println("Details: " + customer1Order.getDescription());
        System.out.println("Price: ৳" + customer1Order.getPrice());

        System.out.println("\n--- Order 2: Custom Gift Pack (Fruits + Premium Box) ---");
        
        RamadanPackage customer2Order = new SpecialPackage();
        customer2Order = new FruitPackageDecorator(customer2Order);
        customer2Order = new PremiumGiftPackagingDecorator(customer2Order);
        
         System.out.println("Details: " + customer2Order.getDescription());
        System.out.println("Price: ৳" + customer2Order.getPrice());

        System.out.println("\n--- Order 3: Fully Loaded Premium Package ---");
        
        RamadanPackage customer3Order = new PremiumGiftPackagingDecorator(
                                            new SweetPackageDecorator(
                                                new FruitPackageDecorator(
                                                    new PremiumPackage()
                                                )
                                            )
                                        );
        System.out.println("Details: " + customer3Order.getDescription());
        System.out.println("Price: ৳" + customer3Order.getPrice());
    }
}
