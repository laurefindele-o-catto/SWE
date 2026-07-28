import java.util.ArrayList;
import java.util.List;

public class GroceryPackage implements GroceryComponent {
    private String name;
    private List<GroceryComponent> items = new ArrayList<>();

    public GroceryPackage(String name) {
        this.name = name;
    }

    public void add(GroceryComponent item) {
        items.add(item);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (GroceryComponent item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public void print(String indent) {
        System.out.printf("%sPackage: %s%n", indent, name);
        for (GroceryComponent item : items) {
            // Pass a single space for nesting child items/packages
            item.print(indent + " ");
        }
    }

}
