package model;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class OrderTest {
    private Order order;
    private ArrayList<Product> products = new ArrayList<>();

    public void setupStage1() {
        products.add(new Product("Product A", "Description A", ProductCategory.ELECTRONICS, 10.0, 7, 2));
        products.add(new Product("Product B", "Description B", ProductCategory.SPORTS, 20.0, 5, 5));
        order = new Order("Pablo", products, 30.0, "01/01/2023");
    }

    @Test
    public void testGetCustomerName() {
        setupStage1();

        assertEquals("Pablo", order.getCustomerName());
    }

    @Test
    public void testGetProducts() {
        setupStage1();

        assertEquals(products, order.getProducts());
    }

    @Test
    public void testGetTotal() {
        setupStage1();


        assertEquals(30, order.getTotal());
    }

    @Test
    public void testGetDate() {
        setupStage1();

        assertEquals("01/01/2023", order.getOrderDate());
    }

    @Test
    public void testSetCustomerName() {
        setupStage1();

        order.setCustomerName("Alejandra");

        assertEquals("Alejandra", order.getCustomerName());
    }

    @Test
    public void testSetProducts() {
        setupStage1();

        ArrayList<Product> newProducts = new ArrayList<>();
        newProducts.add(new Product("Product C", "Description C", ProductCategory.BOOKS, 15.0, 2, 0 ));
        newProducts.add(new Product("Product D", "Description D", ProductCategory.BEAUTY_AND_PERSONAL_CARE, 25.0, 4, 1));

        order.setProducts(newProducts);

        assertEquals(newProducts, order.getProducts());
    }

    @Test
    public void testSetTotal() {
        setupStage1();
        double newTotal = 40.0;
        order.setTotal(newTotal);

        assertEquals(newTotal, order.getTotal());
    }

    @Test
    public void testSetDate() {
        setupStage1();

        order.setOrderDate("02/01/2023");

        assertEquals("02/01/2023", order.getOrderDate());
    }

}


