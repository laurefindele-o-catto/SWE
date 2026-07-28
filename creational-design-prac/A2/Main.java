public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        PackageBuilder relaxBuilder = new RelaxationPackageBuilder();
        director.constructPackage(relaxBuilder);
        HolidayPackage relaxPackage = relaxBuilder.getProduct();
        relaxPackage.display();

        PackageBuilder advBuilder = new VacationPackageBuilder();
        director.constructPackage(advBuilder);
        HolidayPackage advPackage = advBuilder.getProduct();
        advPackage.display();
    }
}
