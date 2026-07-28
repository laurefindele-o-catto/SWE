class SweetPackageDecorator extends PackageDecorator {
    public SweetPackageDecorator(RamadanPackage ramadanPackage) { super(ramadanPackage); }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Predefined Assorted Sweets Box";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 500.0; 
    }
}