import java.util.Scanner;

public class Main {
    private static transportFactory factory;
    public static void main(String[] args){
        configure();
    }

    static void configure(){
        Scanner scan = new Scanner(System.in);

        String delivery = scan.nextLine().toLowerCase();
        //int age = scan.nextInt();

        if(delivery.equals("truck")){
            factory = new truckFactory();
        }
        else if(delivery.equals("ship")){
            factory = new shipFactory();
        }
        else{
            scan.close();
            throw new IllegalArgumentException("unknown transportation");
        }

        scan.close();
    }

    static void runBusinessLogic(){
        factory.planDelivery();
    }
}
