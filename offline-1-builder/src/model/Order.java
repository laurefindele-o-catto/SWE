package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Represents a placed food order.
 *
 * Design note for the assignment:
 * This class works, but its construction API is intentionally awkward.
 * The long constructor mixes required fields, optional fields, defaults,
 * validation, and pricing flags. Students should refactor this design without
 * changing the observable behavior of the program.
 */
public class Order {
    protected String orderId;
    protected String customerName;
    protected String phone;
    protected DeliveryType deliveryType;
    protected String deliveryAddress;
    protected PaymentMethod paymentMethod;
    protected LocalDateTime scheduledTime;
    protected String couponCode;
    protected boolean giftWrap;
    protected boolean cutleryRequired;
    protected int loyaltyPointsToRedeem;
    protected boolean rushOrder;
    protected List<OrderItem> items;
    protected String specialInstructions;
    private final Discount discount;
    private final ServiceCharges serviceCharge;

    public Order() {
        this.items = Collections.emptyList();
        this.discount = new Discount(this);
        this.serviceCharge = new ServiceCharges();
    }


    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public boolean isCutleryRequired() {
        return cutleryRequired;
    }

    public int getLoyaltyPointsToRedeem() {
        return loyaltyPointsToRedeem;
    }

    public boolean isRushOrder() {
        return rushOrder;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setGiftWrap(boolean giftWrap) {
        this.giftWrap = giftWrap;
    }

    public void setCutleryRequired(boolean cutleryRequired) {
        this.cutleryRequired = cutleryRequired;
    }

    public void setLoyaltyPointsToRedeem(int loyaltyPointsToRedeem) {
        this.loyaltyPointsToRedeem = loyaltyPointsToRedeem;
    }

    public void setRushOrder(boolean rushOrder) {
        this.rushOrder = rushOrder;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public double getDiscount() {
        return discount.getDiscount(couponCode, loyaltyPointsToRedeem);
    }

    public double getServiceCharges() {
        return serviceCharge.getServiceCharges(deliveryType, rushOrder, giftWrap);
    }

    public double getTotal() {
        return Math.max(0.0, getSubtotal() + getServiceCharges() - getDiscount());
    }

}
