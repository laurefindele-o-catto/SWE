package adap_comp;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// 1. THE STANDARD TARGET INTERFACE (ZBazar's Core Platform)
// ============================================================================
interface InventoryComponent {
    int checkStock();

    String getExpiryStatus();

    String getName();
}

// ============================================================================
// 2. UNTOUCHABLE LEGACY THIRD-PARTY CLASSES (Adaptees)
// ============================================================================
// Legacy System A: FreshCo
class FreshCoInventory {
    private final String batchName;
    private final int units;

    public FreshCoInventory(String batchName, int units) {
        this.batchName = batchName;
        this.units = units;
    }

    public String getBatchId() {
        return this.batchName;
    }

    public int getAvailableUnits() {
        return this.units;
    }

    public String getBatchExpirationDate() {
        return "2026-08-15";
    } // Fixed date string
}

// Legacy System B: QuickMart
class QuickMartStock {
    private final String itemCode;
    private final int quantity;

    public QuickMartStock(String itemCode, int quantity) {
        this.itemCode = itemCode;
        this.quantity = quantity;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public int retrieveStockQuantity() {
        return this.quantity;
    }

    public int fetchShelfLifeRemainingDays() {
        return 5;
    } // Integer count
}

// ============================================================================
// 3. THE ADAPTER LAYER (Converting Legacy to Target Interface)
// ============================================================================
class FreshCoAdapter implements InventoryComponent {
    private final FreshCoInventory freshCoItem; // Composition

    public FreshCoAdapter(FreshCoInventory freshCoItem) {
        this.freshCoItem = freshCoItem;
    }

    @Override
    public int checkStock() {
        return freshCoItem.getAvailableUnits(); // Translation
    }

    @Override
    public String getExpiryStatus() {
        return "Expires on " + freshCoItem.getBatchExpirationDate(); // Translation
    }

    @Override
    public String getName() {
        return "FreshCo Batch [" + freshCoItem.getBatchId() + "]";
    }
}

class QuickMartAdapter implements InventoryComponent {
    private final QuickMartStock quickMartItem; // Composition

    public QuickMartAdapter(QuickMartStock quickMartItem) {
        this.quickMartItem = quickMartItem;
    }

    @Override
    public int checkStock() {
        return quickMartItem.retrieveStockQuantity(); // Translation
    }

    @Override
    public String getExpiryStatus() {
        return "Expires in " + quickMartItem.fetchShelfLifeRemainingDays() + " days"; // Translation
    }

    @Override
    public String getName() {
        return "QuickMart Item [" + quickMartItem.getItemCode() + "]";
    }
}

// ============================================================================
// 4. THE COMPOSITE LAYER (Hierarchical Warehouse Structures)
// ============================================================================
// Native Leaf: Standard ZBazar Item
class NativeInventoryItem implements InventoryComponent {
    private final String name;
    private final int stockCount;
    private final String expiry;

    public NativeInventoryItem(String name, int stockCount, String expiry) {
        this.name = name;
        this.stockCount = stockCount;
        this.expiry = expiry;
    }

    @Override
    public int checkStock() {
        return this.stockCount;
    }

    @Override
    public String getExpiryStatus() {
        return this.expiry;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

// Composite Branch Node: Warehouse Group
class WarehouseComposite implements InventoryComponent {
    private final String warehouseName;
    private final List<InventoryComponent> subInventories = new ArrayList<>();

    public WarehouseComposite(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void addInventory(InventoryComponent component) {
        subInventories.add(component);
    }

    @Override
    public int checkStock() {
        int totalStock = 0;
        for (InventoryComponent item : subInventories) {
            totalStock += item.checkStock(); // Recursive aggregation
        }
        return totalStock;
    }

    @Override
    public String getExpiryStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Expiry Report for: ").append(warehouseName).append(" ---");
        for (InventoryComponent item : subInventories) {
            sb.append("\n  * ").append(item.getName()).append(" -> ").append(item.getExpiryStatus());
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return this.warehouseName;
    }
}

// ============================================================================
// 5. TEST RUNNER (Demonstrating Unified Execution)
// ============================================================================
public class Main {
    public static void main(String[] args) {
        // Step 1: Create Native Inventory Elements
        InventoryComponent localApples = new NativeInventoryItem("ZB-Apples", 400, "Expires on 2026-08-01");
        InventoryComponent localRice = new NativeInventoryItem("ZB-Miniket Rice Bags", 150, "Expires on 2027-01-01");

        // Step 2: Bring in Legacy systems wrapped in their individual Adapters
        InventoryComponent adaptedFreshCoSpices = new FreshCoAdapter(new FreshCoInventory("FC-Spices-09", 75));
        InventoryComponent adaptedQuickMartMilk = new QuickMartAdapter(new QuickMartStock("QM-LiquidMilk-X", 220));

        // Step 3: Build a low-level Section Composite (Aisle 3)
        WarehouseComposite aisleThree = new WarehouseComposite("Aisle 3: Dairy & Imports");
        aisleThree.addInventory(adaptedQuickMartMilk); // Adding adapted QuickMart item
        aisleThree.addInventory(adaptedFreshCoSpices); // Adding adapted FreshCo item

        // Step 4: Build a high-level Regional Composite (Dhaka Hub)
        WarehouseComposite dhakaHub = new WarehouseComposite("Dhaka National Distribution Hub");
        dhakaHub.addInventory(localApples); // Adding native items directly
        dhakaHub.addInventory(localRice);
        dhakaHub.addInventory(aisleThree); // Nesting a composite inside a composite!

        // Step 5: Test Execution
        System.out.println("======= SYSTEM DATA AGGREGATION CHECK =======");
        System.out.println("Total Stock Count across entire Dhaka Hub: " + dhakaHub.checkStock() + " units.");

        System.out.println("\n======= TREE RECURSION REPORTING =======");
        System.out.println(dhakaHub.getExpiryStatus());
    }
}
