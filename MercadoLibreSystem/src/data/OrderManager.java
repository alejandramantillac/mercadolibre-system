package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Order;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final String ORDERS_FILE_NAME = "orders.json";
    private List<Order> orders;
    private Gson gson;

    public OrderManager() {
        this.orders = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        loadOrders();
    }

    public void addOrder(Order order) {
        order.setId(orders.size() + 1);
        orders.add(order);
        saveOrders();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void saveOrders() {
        try (Writer writer = new FileWriter(ORDERS_FILE_NAME)) {
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadOrders() {
        try (Reader reader = new FileReader(ORDERS_FILE_NAME)) {
            Type type = new TypeToken<List<Order>>(){}.getType();
            orders = gson.fromJson(reader, type);
            if (orders == null) {
                orders = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        saveOrders();
    }

}