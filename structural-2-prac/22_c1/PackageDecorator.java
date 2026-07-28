abstract public class PackageDecorator implements RamadanPackage {
    protected final RamadanPackage wrappedPackage;

    public PackageDecorator(RamadanPackage ramadanPackage) {
        this.wrappedPackage = ramadanPackage;
    }

    @Override
    public String getDescription() {
        return wrappedPackage.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedPackage.getPrice();
    }
}