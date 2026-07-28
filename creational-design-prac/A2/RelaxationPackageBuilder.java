public class RelaxationPackageBuilder implements PackageBuilder{
    private HolidayPackage packageObj;

    public RelaxationPackageBuilder(){
        this.reset();
    }
    public void reset(){
        this.packageObj = new HolidayPackage();
    }

    public void buildFlight(){
        packageObj.setFlight("Business class");
    }

    public void buildHotel(){
        packageObj.setHotel("5-Star hotel");
    }

    public void buildActivity(){
        packageObj.setActivity("Spa treatment");
    }

    public HolidayPackage getProduct(){
        HolidayPackage product = this.packageObj;
        this.reset();
        return product;
    }
}
