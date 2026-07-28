public interface PackageBuilder {
    void reset();
    void buildFlight();
    void buildHotel();
    void buildActivity();
    HolidayPackage getProduct();
}
