public class Director {
    public void constructPackage(PackageBuilder builder){
        builder.buildFlight();
        builder.buildActivity();
        builder.buildHotel();
    }
}
