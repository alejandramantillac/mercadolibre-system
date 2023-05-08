package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderSearcher {
    private List<Order> orderList;

    public OrderSearcher(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void createOrder(String customerName, ArrayList<Product> products, double total, String orderDate) {
        Order order = new Order(customerName, products, total, orderDate);
        order.setId(orderList.size() + 1);
        orderList.add(order);

    }
}
