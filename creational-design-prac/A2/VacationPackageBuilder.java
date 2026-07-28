public class VacationPackageBuilder implements PackageBuilder{
    private HolidayPackage packageObj;

    public VacationPackageBuilder(){
        this.reset();
    }
    public void reset(){
        this.packageObj = new HolidayPackage();
    }

    public void buildFlight(){
        packageObj.setFlight("Economy flight");
    }

    public void buildHotel(){
        packageObj.setHotel("Mountain cabin");
    }

    public void buildActivity(){
        packageObj.setActivity("Hiking tour");
    }

    public HolidayPackage getProduct(){
        HolidayPackage product = this.packageObj;
        this.reset();
        return product;
    }
}
