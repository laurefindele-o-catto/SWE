class StandardPackage implements RamadanPackage {
    @Override
    public String getDescription() {
        return "Standard Ramadan Package (Dates, Rice, Oil, Chickpeas)";
    }

    @Override
    public double getPrice() {
        return 1500.0;
    }
}