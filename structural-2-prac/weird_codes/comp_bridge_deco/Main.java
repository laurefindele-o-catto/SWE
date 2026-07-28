package comp_bridge_deco;
import java.util.ArrayList;
import java.util.List;

// ============================================================================
// 1. THE BRIDGE PATTERN: IMPLEMENTOR LAYER (Tracking & Logistics Operations)
// ============================================================================
interface TrackingSystem {
    void logTracking(String shipmentName);
    double getBaseTransitFee();
}

class StandardGPS implements TrackingSystem {
    @Override
    public void logTracking(String name) { System.out.println("   [GPS] Tracking " + name + " via standard cellular checkpoints."); }
    @Override
    public double getBaseTransitFee() { return 50.0; }
}

class SatelliteRealTime implements TrackingSystem {
    @Override
    public void logTracking(String name) { System.out.println("   [SATELLITE] Continuous real-time ping active for " + name); }
    @Override
    public double getBaseTransitFee() { return 250.0; }
}


// ============================================================================
// 2. THE BRIDGE PATTERN: ABSTRACTION LAYER (Shipping Tiers)
// ============================================================================
abstract class ShippingTier {
    protected TrackingSystem trackingSystem; // The Bridge Reference

    protected ShippingTier(TrackingSystem trackingSystem) {
        this.trackingSystem = trackingSystem;
    }

    public abstract void dispatchManifest(String shipmentName);
    public abstract double calculateOperationalCost(double baseWeight);
}

class EconomyTier extends ShippingTier {
    public EconomyTier(TrackingSystem trackingSystem) { super(trackingSystem); }

    @Override
    public void dispatchManifest(String name) {
        System.out.println(" -> Processing " + name + " under standard ground economy timetables.");
        trackingSystem.logTracking(name);
    }

    @Override
    public double calculateOperationalCost(double baseWeight) {
        return trackingSystem.getBaseTransitFee() + (baseWeight * 1.2);
    }
}

class PriorityExpressTier extends ShippingTier {
    public PriorityExpressTier(TrackingSystem trackingSystem) { super(trackingSystem); }

    @Override
    public void dispatchManifest(String name) {
        System.out.println(" -> PRIORITY ROUTING TRIGGERED for: " + name);
        trackingSystem.logTracking(name);
    }

    @Override
    public double calculateOperationalCost(double baseWeight) {
        return (trackingSystem.getBaseTransitFee() * 2.0) + (baseWeight * 3.5);
    }
}


// ============================================================================
// 3. THE COMPOSITE PATTERN: UNIFIED COMPONENT (Cargo Structure)
// ============================================================================
interface CargoComponent {
    void fulfill(ShippingTier tier);
    double estimateWeight();
    String getName();
}

// Composite Leaf node
class IndividualPackage implements CargoComponent {
    private final String name;
    private final double weight;

    public IndividualPackage(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public void fulfill(ShippingTier tier) {
        tier.dispatchManifest(name);
        System.out.println("    └─ Package Content Cost calculated: ৳" + tier.calculateOperationalCost(weight));
    }

    @Override
    public double estimateWeight() { return this.weight; }
    @Override
    public String getName() { return this.name; }
}

// Composite Branch Node (Container holding others)
class CargoContainer implements CargoComponent {
    private final String name;
    private final List<CargoComponent> contents = new ArrayList<>();

    public CargoContainer(String name) { this.name = name; }

    public void addCargo(CargoComponent component) { contents.add(component); }

    @Override
    public void fulfill(ShippingTier tier) {
        System.out.println("\n=== Group Processing Node: " + name + " ===");
        for (CargoComponent child : contents) {
            child.fulfill(tier); // Recursion across the tree hierarchy
        }
    }

    @Override
    public double estimateWeight() {
        double totalWeight = 0;
        for (CargoComponent child : contents) {
            totalWeight += child.estimateWeight();
        }
        return totalWeight;
    }

    @Override
    public String getName() { return this.name; }
}


// ============================================================================
// 4. THE DECORATOR PATTERN: WRAPPING THE COMPOSITE LEAF OR BRANCH
// ============================================================================
abstract class CargoHandlingDecorator implements CargoComponent {
    protected final CargoComponent decoratedCargo;

    public CargoHandlingDecorator(CargoComponent cargoComponent) {
        this.decoratedCargo = cargoComponent;
    }

    @Override
    public void fulfill(ShippingTier tier) { decoratedCargo.fulfill(tier); }
    @Override
    public double estimateWeight() { return decoratedCargo.estimateWeight(); }
    @Override
    public String getName() { return decoratedCargo.getName(); }
}

class FragileHandlingDecorator extends CargoHandlingDecorator {
    public FragileHandlingDecorator(CargoComponent cargoComponent) { super(cargoComponent); }

    @Override
    public void fulfill(ShippingTier tier) {
        System.out.println("   ⚠️ [SPECIAL HANDLING] Applying shock-absorbent layering before processing structural node.");
        super.fulfill(tier);
    }

    @Override
    public double estimateWeight() {
        return super.estimateWeight() + 1.5; // Adding protective material weight overhead
    }
}

class ColdChainDecorator extends CargoHandlingDecorator {
    public ColdChainDecorator(CargoComponent cargoComponent) { super(cargoComponent); }

    @Override
    public void fulfill(ShippingTier tier) {
        System.out.println("   ❄️ [TEMPERATURE CONTROL] Pre-chilling holding node down to -4°C.");
        super.fulfill(tier);
    }
}


// ============================================================================
// 5. TEST RUNNER (Demonstrating Unified Execution)
// ============================================================================
public class Main {
    public static void main(String[] args) {
        // Step 1: Establish Bridge Strategies
        ShippingTier basicEconomy = new EconomyTier(new StandardGPS());
        ShippingTier elitePriority = new PriorityExpressTier(new SatelliteRealTime());

        // Step 2: Build The Base Tree Structures (Leaves)
        CargoComponent pkg1 = new IndividualPackage("Dhaka Central Box A", 5.0);
        CargoComponent pkg2 = new IndividualPackage("Chittagong Port Box B", 12.5);

        // Step 3: Layer Decorators dynamically onto selected Leaves
        CargoComponent protectedFragilePackage = new FragileHandlingDecorator(pkg1);

        // Step 4: Assemble Tree Hierarchy (Composites)
        CargoContainer intermediatePallet = new CargoContainer("Regional Pallet #42");
        intermediatePallet.addCargo(protectedFragilePackage); // Holds a decorated leaf
        intermediatePallet.addCargo(pkg2);                  // Holds a regular leaf

        // Step 5: Decorate an Entire Branch Node at runtime!
        // Cold chain applies to the entire container and everything recursively inside it
        CargoComponent temperatureControlledPallet = new ColdChainDecorator(intermediatePallet);

        // Master Composite representing the truck
        CargoContainer masterTruckCargo = new CargoContainer("Master Distribution Truck - Fleet 09");
        masterTruckCargo.addCargo(temperatureControlledPallet);
        masterTruckCargo.addCargo(new IndividualPackage("Unregulated Direct Parcel", 2.0));

        // Step 6: Single Unified Trigger Execution over the entire layered architecture
        System.out.println("======= SCENARIO 1: PROCESSING TRUCK UNDER AIR PRIORITY STRATEGY =======");
        masterTruckCargo.fulfill(elitePriority);

        System.out.println("\n======= SCENARIO 2: WEIGHT RECURSION CHECKS =======");
        System.out.println("Total Truck Cargo Structural Weight: " + masterTruckCargo.estimateWeight() + " kg");
    }
}
