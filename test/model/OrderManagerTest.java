package model;

import static org.junit.jupiter.api.Assertions.*;

import data.OrderManager;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class OrderManagerTest {

    private static OrderManager orderManager;

    public void setupStage1() {
        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(new Product("Product 1", "the description 1", ProductCategory.STATIONERY, 10.0, 5, 1));
        orderManager = new OrderManager("orders.json");
        orderManager.getOrders().clear();
        orderManager.addOrder(new Order("Alejandra", products1, 50, "06/06/2006"));

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(new Product("Product 2", "the description 2", ProductCategory.ELECTRONICS, 15.0, 7, 0));
        orderManager.addOrder(new Order("Pablo", products2, 105, "07/07/2007"));
        orderManager.saveOrders();
    }
    @Test
    public void testAddOrder() {
        setupStage1();
        assertEquals(2, orderManager.getOrders().size());
        assertEquals("Alejandra", orderManager.getOrders().get(0).getCustomerName());
    }

    @Test
    public void testRemoveOrder() {
        setupStage1();
        orderManager.removeOrder(orderManager.getOrders().get(0));
        assertEquals(1, orderManager.getOrders().size());
    }

    @Test
    public void testLoadOrders() {
        setupStage1();

        orderManager.loadOrders();

        List<Order> loadedOrders = orderManager.getOrders();

        assertEquals(2, loadedOrders.size());
    }


    @Test
    public void testSaveOrders() {
        setupStage1();

        orderManager.saveOrders();

        orderManager.loadOrders();

        List<Order> loadedOrders = orderManager.getOrders();

        assertEquals(orderManager.getOrders().size(), loadedOrders.size());
        for (int i = 0; i < orderManager.getOrders().size(); i++) {
            assertEquals(orderManager.getOrders().get(i), loadedOrders.get(i));
        }
    }

}

