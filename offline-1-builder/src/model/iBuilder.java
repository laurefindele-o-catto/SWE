package model;

import java.time.LocalDateTime;
import java.util.List;

public interface iBuilder {

    void reset();
    
    void setRequiredFields(String orderId, String customerName, String phone, List<OrderItem> items);


    // void setOrderId(String orderId);
    // void setCustomerName(String name);
    // void setPhone(String phone);
    //ei tinta e required, so alada func e

    void setDeliveryType(DeliveryType deliveryType);

    void setDeliveryAddress(String deliveryAddress);

    void setPaymentMethod(PaymentMethod paymentMethod);

    void setScheduledTime(LocalDateTime scheduledTime);

    void setCouponCode(String couponCode);

    void setGiftWrap(boolean giftWrap);

    void setCutleryRequired(boolean cutleryRequired);

    void setLoyaltyPointsToRedeem(int loyaltyPointsToRedeem);

    void setRushOrder(boolean rushOrder);

    void setSpecialInstructions(String specialInstructions);

    Order build();

}
