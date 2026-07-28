public class Main {
    public static void main(String[] args) {
        ManufacturingDirector director = new ManufacturingDirector();

        // 1. Manufacture a Commuter Bike
        BicycleBuilder commuterBuilder = new CommuterBuilder();
        director.constructBicycle(commuterBuilder);
        Bicycle commuterBike = commuterBuilder.getResult();
        commuterBike.showSpecifications();

        // 2. Manufacture a Mountain Beast Bike
        BicycleBuilder mountainBuilder = new MountainBeastBuilder();
        director.constructBicycle(mountainBuilder);
        Bicycle mountainBike = mountainBuilder.getResult();
        mountainBike.showSpecifications();
    }
}