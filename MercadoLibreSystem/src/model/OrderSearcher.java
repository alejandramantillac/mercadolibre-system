package model;

import exceptions.OrderNotFoundException;

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

    public List<Order> searchByTotalRange(double lowerTotal, double upperTotal) throws OrderNotFoundException {
        List<Order> result = new ArrayList<>();
        List<Order> sortedList = new ArrayList<>(orderList);
        sortedList.sort(Comparator.comparing(Order::getTotal));

        int lowerIndex = Collections.binarySearch(sortedList, new Order(null, null, lowerTotal, null),
                Comparator.comparing(Order::getTotal));
        if (lowerIndex < 0) {
            lowerIndex = -(lowerIndex + 1);
        }
        int upperIndex = Collections.binarySearch(sortedList, new Order(null, null, upperTotal, null),
                Comparator.comparing(Order::getTotal));
        if (upperIndex < 0) {
            upperIndex = -(upperIndex + 1);
        } else {
            upperIndex++;
        }
        if (lowerIndex < sortedList.size() && upperIndex >= lowerIndex) {
            result.addAll(sortedList.subList(lowerIndex, upperIndex));
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