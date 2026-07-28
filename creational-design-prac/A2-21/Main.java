import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String model = scan.nextLine();

        pcFactory factory;

        if(model.equals("workpro")){
            factory = new WorkproFactory();
        }
        else if(model.equals("litemax")){
            factory = new LiteMaxFactory();
        }
        else{
            System.out.println("not available");
            scan.close();
            return;
        }

        PC pc = new PC(model, factory);
        pc.describe();

        scan.close();
    }
}
