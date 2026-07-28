import java.util.ArrayList;
import java.util.List;

public class SetMenu implements OrderItem {
    private String name;
    private List<Food> menuItems = new ArrayList<>();
    private final double discount = 10;

    public SetMenu(String name) {
        this.name = name;
    }

    public void addFood(Food f) {
        menuItems.add(f);
    }

    @Override
    public double getPrice() {
        double total = 0;

        for (Food f : menuItems) {
            total += f.getPrice();
        }

        return total * ((100 - discount) / 100);
    }

    @Override
    public void print(String indent) {
        System.out.printf("%sSet Menu: %s%n", indent, name);
        for (Food food : menuItems) {
            // Pass a single space for nesting child items
            food.print(indent + " ");
        }
    }
}
