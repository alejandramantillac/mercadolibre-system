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

    public List<Order> searchByCustomerName(String customerName) {
        List<Order> result = new ArrayList<>();
        OrderBinarySearcher<Order> searcher = new OrderBinarySearcher<>(orderList, Comparator.comparing(Order::getCustomerName));
        List<Order> matches = searcher.search(new Order(customerName, null, 0.0, null));
        for (Order order : matches) {
            if (order.getCustomerName().equals(customerName)) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> searchByTotal(double total) {
        List<Order> result = new ArrayList<>();
        OrderBinarySearcher<Order> searcher = new OrderBinarySearcher<>(orderList, Comparator.comparing(Order::getTotal));
        List<Order> matches = searcher.search(new Order(null, null, total, null));
        for (Order order : matches) {
            if (order.getTotal() == total) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> searchByTotalRange(double lowerTotal, double upperTotal) {
        List<Order> result = new ArrayList<>();
        OrderBinarySearcher<Order> searcher = new OrderBinarySearcher<>(orderList, Comparator.comparing(Order::getTotal));
        List<Order> matches = searcher.search(new Order(null, null, lowerTotal, null));
        for (Order order : matches) {
            if (order.getTotal() >= lowerTotal && order.getTotal() <= upperTotal) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> searchByOrderDate(String orderDate) {
        List<Order> result = new ArrayList<>();
        OrderBinarySearcher<Order> searcher = new OrderBinarySearcher<>(orderList, Comparator.comparing(Order::getOrderDate));
        List<Order> matches = searcher.search(new Order(null, null, 0.0, orderDate));
        for (Order order : matches) {
            if (order.getOrderDate().equals(orderDate)) {
                result.add(order);
            }
        }
        return result;
    }

}
