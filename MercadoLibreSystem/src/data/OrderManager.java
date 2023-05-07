package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Order;
import model.Product;

public class OrderManager {
    private List<Order> orders;
    private String fileName;

    public OrderManager(String fileName) {
        this.fileName = fileName;
        loadOrders();
    }

    public void loadOrders() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Gson gson = new Gson();
            orders = gson.fromJson(reader, new TypeToken<List<Order>>(){}.getType());
            reader.close();
        } catch (IOException e) {
            orders = new ArrayList<>();
        }
    }

    public void saveOrders() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("orders.json"));
            Gson gson = new Gson();
            gson.toJson(orders, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        saveOrders();
    }
}
