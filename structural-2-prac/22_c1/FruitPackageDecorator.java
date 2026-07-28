public class FruitPackageDecorator extends PackageDecorator {
    public FruitPackageDecorator(RamadanPackage ramadanPackage) { super(ramadanPackage); }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Predefined Seasonal Fruit Pack";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 350.0; // Dynamic addition of fruit pack cost
    }
}