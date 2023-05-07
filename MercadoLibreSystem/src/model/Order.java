package model;

import java.util.ArrayList;
/*
import com.google.gson.Gson;

 */

public class Order {
    private int id;
    private String customerName;
    private ArrayList<Product> products;
    private double total;
    private String orderDate;

    public Order(String customerName, ArrayList<Product> products, double total, String orderDate) {
        this.customerName = customerName;
        this.products = products;
        this.total = total;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<String> getProducts() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Product product : products) {
            productNames.add(product.getName());
        }
        return productNames;
    }

    /*
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Order fromJson(String json) {
        return new Gson().fromJson(json, Order.class);
    }

     */
}
