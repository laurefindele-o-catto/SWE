package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class OrderDirector {
    private static final DeliveryType DEFAULT_DELIVERY_TYPE = DeliveryType.PICKUP;
    private static final String DEFAULT_DELIVERY_ADDRESS = "";
    private static final PaymentMethod DEFAULT_PAYMENT_METHOD = PaymentMethod.CASH;
    private static final String DEFAULT_COUPON_CODE = "";
    private static final boolean DEFAULT_GIFT_WRAP = false;
    private static final boolean DEFAULT_CUTLERY_REQUIRED = true;
    private static final int DEFAULT_LOYALTY_POINTS_TO_REDEEM = 0;
    private static final boolean DEFAULT_RUSH_ORDER = false;
    private static final String DEFAULT_SPECIAL_INSTRUCTIONS = "";

    private iBuilder orderBuilder;

    public OrderDirector() {
        this.orderBuilder = new OrderBuilder();
    }

    public OrderDirector(iBuilder orderBuilder) {
        this.orderBuilder = Objects.requireNonNull(orderBuilder, "Order builder cannot be null");
    }

    public void setBuilder(iBuilder orderBuilder) {
        this.orderBuilder = Objects.requireNonNull(orderBuilder, "Order builder cannot be null");
    }

    public Order createDeliveryOrder(String orderId,
            String customerName,
            String phone,
            String address,
            List<OrderItem> items,
            String couponCode,
            boolean rushOrder,
            String specialInstructions) {
        orderBuilder.reset();
        applyDefaults();
        setRequiredFields(orderId, customerName, phone, items);
        orderBuilder.setDeliveryType(DeliveryType.DELIVERY);
        orderBuilder.setDeliveryAddress(requireNonBlank(address, "Delivery address"));
        orderBuilder.setCouponCode(normalizeCouponCode(couponCode));
        orderBuilder.setRushOrder(rushOrder);
        orderBuilder.setSpecialInstructions(normalizeText(specialInstructions));
        return orderBuilder.build();
    }

    public Order createPickupOrder(String orderId,
            String customerName,
            String phone,
            List<OrderItem> items) {
        orderBuilder.reset();
        applyDefaults();
        setRequiredFields(orderId, customerName, phone, items);
        orderBuilder.setDeliveryType(DeliveryType.PICKUP);
        return orderBuilder.build();
    }

    public Order createScheduledGiftOrder(String orderId,
            String customerName,
            String phone,
            String address,
            List<OrderItem> items,
            LocalDateTime scheduledTime) {
        orderBuilder.reset();
        applyDefaults();
        setRequiredFields(orderId, customerName, phone, items);
        orderBuilder.setDeliveryType(DeliveryType.DELIVERY);
        orderBuilder.setDeliveryAddress(requireNonBlank(address, "Delivery address"));
        orderBuilder.setPaymentMethod(PaymentMethod.CARD);
        orderBuilder.setScheduledTime(scheduledTime);
        orderBuilder.setCouponCode("WELCOME10");
        orderBuilder.setGiftWrap(true);
        orderBuilder.setCutleryRequired(false);
        orderBuilder.setLoyaltyPointsToRedeem(25);
        orderBuilder.setSpecialInstructions("Please call before delivery");
        return orderBuilder.build();
    }

    public Order createSampleFamilyOrder(String orderId, List<OrderItem> items) {
        orderBuilder.reset();
        applyDefaults();
        setRequiredFields(orderId, "Sample Family", "01711111111", items);
        orderBuilder.setDeliveryType(DeliveryType.DELIVERY);
        orderBuilder.setDeliveryAddress("House 25, Road 4, Dhanmondi");
        orderBuilder.setPaymentMethod(PaymentMethod.MOBILE_BANKING);
        orderBuilder.setCouponCode("FAMILY15");
        orderBuilder.setLoyaltyPointsToRedeem(50);
        orderBuilder.setRushOrder(true);
        orderBuilder.setSpecialInstructions("Deliver together");
        return orderBuilder.build();
    }

    private void applyDefaults() {
        orderBuilder.setDeliveryType(DEFAULT_DELIVERY_TYPE);
        orderBuilder.setDeliveryAddress(DEFAULT_DELIVERY_ADDRESS);
        orderBuilder.setPaymentMethod(DEFAULT_PAYMENT_METHOD);
        orderBuilder.setCouponCode(DEFAULT_COUPON_CODE);
        orderBuilder.setGiftWrap(DEFAULT_GIFT_WRAP);
        orderBuilder.setCutleryRequired(DEFAULT_CUTLERY_REQUIRED);
        orderBuilder.setLoyaltyPointsToRedeem(DEFAULT_LOYALTY_POINTS_TO_REDEEM);
        orderBuilder.setRushOrder(DEFAULT_RUSH_ORDER);
        orderBuilder.setSpecialInstructions(DEFAULT_SPECIAL_INSTRUCTIONS);
    }

    private void setRequiredFields(String orderId, String customerName, String phone, List<OrderItem> items) {
        orderBuilder.setRequiredFields(requireNonBlank(orderId, "Order id"),
                requireNonBlank(customerName, "Customer name"),
                requireNonBlank(phone, "Phone"),
                requireItems(items));
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return trimmed;
    }

    private static String normalizeText(String value) {
        return value != null ? value.trim() : "";
    }

    private static String normalizeCouponCode(String couponCode) {
        return normalizeText(couponCode).toUpperCase();
    }

    private static List<OrderItem> requireItems(List<OrderItem> items) {
        Objects.requireNonNull(items, "Items cannot be null");
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        return Collections.unmodifiableList(new ArrayList<>(items));
    }
}
