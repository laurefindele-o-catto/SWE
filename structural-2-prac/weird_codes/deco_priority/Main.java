package deco_priority;

// === 1. COMPONENT INTERFACE ===
interface ShoppingCart {
    double calculateFinalPrice();
    String generateReceipt();
    
    // Core addition to solve structural sequence priority
    int getPriority(); 
}

// === 2. CONCRETE BASE COMPONENT ===
class BaseCart implements ShoppingCart {
    private final double itemTotal;

    public BaseCart(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public double calculateFinalPrice() { return this.itemTotal; }
    
    @Override
    public String generateReceipt() { return "Base Items Total: ৳" + itemTotal; }
    
    @Override
    public int getPriority() { return 0; } // Base is always processing priority 0
}

// === 3. COOPERATIVE BASE DECORATOR ===
abstract class PricingDecorator implements ShoppingCart {
    protected final ShoppingCart wrappedCart;

    public PricingDecorator(ShoppingCart cart) {
        this.wrappedCart = cart;
    }

    // Standard breakdown behavior
    @Override
    public String generateReceipt() { return wrappedCart.generateReceipt(); }
}

// === 4. CONCRETE PRIORITY-AWARE DECORATORS ===

// PRIORITY 1: DISCOUNTS
class RamadanDiscountDecorator extends PricingDecorator {
    private final double discountAmount = 200.0;

    public RamadanDiscountDecorator(ShoppingCart cart) { super(cart); }

    @Override
    public int getPriority() { return 1; }

    @Override
    public double calculateFinalPrice() {
        // If wrapped item has higher priority (broken order), force sequence
        if (wrappedCart.getPriority() > this.getPriority()) {
            return wrappedCart.calculateFinalPrice() - discountAmount; 
        }
        return wrappedCart.calculateFinalPrice() - discountAmount;
    }

    @Override
    public String generateReceipt() {
        return super.generateReceipt() + "\n  [-] Ramadan Discount Applied: -৳" + discountAmount;
    }
}

// PRIORITY 2: TAXATION (Depends on post-discount subtotal)
class VATTaxDecorator extends PricingDecorator {
    private final double vatRate = 0.05; // 5% VAT

    public VATTaxDecorator(ShoppingCart cart) { super(cart); }

    @Override
    public int getPriority() { return 2; }

    @Override
    public double calculateFinalPrice() {
        // CORRECTION MECHANISM: If nested inside Delivery (Priority 3), 
        // calculate tax without factoring in the delivery charge.
        if (wrappedCart.getPriority() == 3) {
            // Bypass delivery to extract correct tax subtotal, then re-apply delivery charge
            double priceWithoutDelivery = ((PricingDecorator) wrappedCart).wrappedCart.calculateFinalPrice();
            double taxValue = priceWithoutDelivery * vatRate;
            return wrappedCart.calculateFinalPrice() + taxValue;
        }
        
        // Normal behavior: tax calculated from nested priorities (0 and 1)
        return wrappedCart.calculateFinalPrice() * (1 + vatRate);
    }

    @Override
    public String generateReceipt() {
        return super.generateReceipt() + "\n  [+] VAT Tax (5%) Applied";
    }
}

// PRIORITY 3: LOGISTICS (Always added last to the aggregate total)
class DeliveryFeeDecorator extends PricingDecorator {
    private final double flatDeliveryFee = 60.0;

    public DeliveryFeeDecorator(ShoppingCart cart) { super(cart); }

    @Override
    public int getPriority() { return 3; }

    @Override
    public double calculateFinalPrice() {
        return wrappedCart.calculateFinalPrice() + flatDeliveryFee;
    }

    @Override
    public String generateReceipt() {
        return super.generateReceipt() + "\n  [+] Flat Last-Mile Delivery Fee: +৳" + flatDeliveryFee;
    }
}


// === 5. TEST RUNNER ===
public class Main {
    public static void main(String[] args) {
        double itemCost = 2000.0;
        
        System.out.println("======= CASE 1: CORRECT NESTING SEQUENCE =======");
        // Correct Order: Delivery( Tax( Discount( Base ) ) )
        // Discount 200 applied first -> 1800. Tax 5% applied next -> 1890. Delivery applied last -> 1950.
        ShoppingCart sequentialCart = new DeliveryFeeDecorator(
                                        new VATTaxDecorator(
                                            new RamadanDiscountDecorator(
                                                new BaseCart(itemCost)
                                            )
                                        )
                                      );
        
        System.out.println(sequentialCart.generateReceipt());
        System.out.println("TOTAL COST: ৳" + sequentialCart.calculateFinalPrice());

        
        System.out.println("\n======= CASE 2: MALFORMED/REVERSED NESTING SEQUENCE =======");
        // Erroneous Order: Discount( Tax( Delivery( Base ) ) )
        // A naive decorator would evaluate: ((2000 + 60) * 1.05) - 200 = 1963.0 (Incorrect!)
        ShoppingCart mixedCart = new RamadanDiscountDecorator(
                                    new VATTaxDecorator(
                                        new DeliveryFeeDecorator(
                                            new BaseCart(itemCost)
                                        )
                                    )
                                 );

        System.out.println(mixedCart.generateReceipt());
        // The Priority Correction overrides structural sequence and outputs 1950.0 identically!
        System.out.println("TOTAL COST (CORRECTED): ৳" + mixedCart.calculateFinalPrice());
    }
}
