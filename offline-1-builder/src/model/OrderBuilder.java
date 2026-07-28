package model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderBuilder implements iBuilder{
    private Order order;

    public OrderBuilder(){
        reset();
    }

    public void reset(){
        order = new Order();
    }

    public void setRequiredFields(String orderId, String customerName, String phone, List<OrderItem> items){
        order.setOrderId(orderId);
        order.setCustomerName(customerName);
        order.setPhone(phone);
        order.setItems(items);
    }

    public void setDeliveryType(DeliveryType deliveryType){
        order.setDeliveryType(deliveryType);
    }

    public void setDeliveryAddress(String deliveryAddress) {
        order.setDeliveryAddress(deliveryAddress);
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        order.setPaymentMethod(paymentMethod);
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        order.setScheduledTime(scheduledTime);
    }

    public void setCouponCode(String couponCode) {
        order.setCouponCode(couponCode);
    }

    public void setGiftWrap(boolean giftWrap) {
        order.setGiftWrap(giftWrap);
    }

    public void setCutleryRequired(boolean cutleryRequired){
        order.setCutleryRequired(cutleryRequired);
    }

    public void setLoyaltyPointsToRedeem(int loyaltyPointsToRedeem) {
        order.setLoyaltyPointsToRedeem(loyaltyPointsToRedeem);
    }

    public void setRushOrder(boolean rushOrder) {
        order.setRushOrder(rushOrder);
    }

    public void setSpecialInstructions(String specialInstructions) {
        order.setSpecialInstructions(specialInstructions);
    }

    public Order build(){
        Order returnOrder = this.order;
        reset();
        return returnOrder;
    }
}
