class PremiumGiftPackagingDecorator extends PackageDecorator {
    public PremiumGiftPackagingDecorator(RamadanPackage ramadanPackage) { super(ramadanPackage); }

    @Override
    public String getDescription() {
        return super.getDescription() + " [Wrapped in Premium Festive Gift Box]";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 150.0; 
    }
}