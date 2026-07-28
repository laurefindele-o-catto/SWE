package service;

import model.MenuItem;
import model.Order;
import model.OrderBuilder;
import model.OrderDirector;
import model.OrderItem;
import model.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    private int nextNumber = 1001;
    private final OrderDirector orderDirector;

    public OrderService() {
        this.orderDirector = new OrderDirector(new OrderBuilder());
    }

    public OrderItem createOrderItem(MenuItem item, int quantity, Size size, boolean extraCheese, boolean spicy, String note) {
        return new OrderItem(item, quantity, size, extraCheese, spicy, note);
    }

    public Order createDeliveryOrder(String customerName,
                                     String phone,
                                     String address,
                                     List<OrderItem> items,
                                     String couponCode,
                                     boolean rushOrder,
                                     String specialInstructions) {
        return orderDirector.createDeliveryOrder(nextOrderId(), customerName, phone, address, items, couponCode, rushOrder, specialInstructions);
    }

    public Order createPickupOrder(String customerName, String phone, List<OrderItem> items) {
        return orderDirector.createPickupOrder(nextOrderId(), customerName, phone, items);
    }

    public Order createScheduledGiftOrder(String customerName,
                                          String phone,
                                          String address,
                                          List<OrderItem> items,
                                          LocalDateTime scheduledTime) {
        return orderDirector.createScheduledGiftOrder(nextOrderId(), customerName, phone, address, items, scheduledTime);
    }

    public Order createSampleFamilyOrder(MenuCatalog catalog) {
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(catalog.findByCode("P01"), 2, Size.LARGE, true, false, "half spicy"));
        items.add(new OrderItem(catalog.findByCode("B02"), 3, Size.MEDIUM, true, true, ""));
        items.add(new OrderItem(catalog.findByCode("D02"), 4, Size.MEDIUM, false, false, "less sugar"));
        items.add(new OrderItem(catalog.findByCode("S02"), 2, Size.LARGE, false, true, ""));

        return orderDirector.createSampleFamilyOrder(nextOrderId(), items);
    }

    private String nextOrderId() {
        return "FF-" + nextNumber++;
    }
}

