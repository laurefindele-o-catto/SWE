public class HolidayPackage {
    private String flight;
    private String hotel;
    private String activity;

    public void setFlight(String flight) {
        this.flight = flight;
    }
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void display(){
        System.out.println("Flight - " + flight + "\nHotel - " + hotel + "\nActivity - " + activity);
        //
    }    
}
