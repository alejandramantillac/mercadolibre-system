package model;



import static org.junit.jupiter.api.Assertions.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InventoryTest {

    private static Inventory inventory;

    public void setupStage1() {
        inventory = new Inventory();
        inventory.getProducts().clear();
        inventory.addProduct(new Product("Product A", "Description A", ProductCategory.BOOKS, 22.0, 2, 2));
        inventory.addProduct(new Product("Product B", "Description B", ProductCategory.BEAUTY_AND_PERSONAL_CARE, 56.0, 6, 3));
        //inventory.saveProducts();
    }


    public void setupStage2() {
        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(new Product("Product 1", "the description 1", ProductCategory.STATIONERY, 10.0, 5, 1));
        inventory = new Inventory();
        inventory.getOrders().clear();
        inventory.addOrder(new Order("Alejandra", products1, 50, "06/06/2006"));

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(new Product("Product 2", "the description 2", ProductCategory.ELECTRONICS, 15.0, 7, 0));
        inventory.addOrder(new Order("Pablo", products2, 105, "07/07/2007"));
        inventory.saveOrders();
    }


    @Test
    public void testAddProduct() {
        setupStage1();
        assertEquals(2, inventory.getProducts().size());
        assertEquals("Product A", inventory.getProducts().get(0).getName());
    }

    @Test
    public void testAddProductMultiple(){
        setupStage1();

        inventory.addProduct(new Product("Product C", "Description C", ProductCategory.TOYS_AND_GAMES, 108, 1, 10));
        inventory.addProduct(new Product("Product D", "Description D", ProductCategory.CLOTHING_ACCESSORIES, 20, 7, 13));

        assertEquals(4, inventory.getProducts().size());
        assertEquals("Product D",inventory.getProducts().get(3).getName());
    }


    @Test
    public void testRemoveProduct() {
        setupStage1();
        inventory.removeProduct(inventory.getProducts().get(0));
        assertEquals(1, inventory.getProducts().size());
    }

    @Test
    public void testRemoveProductMultiple() {
        setupStage1();
        inventory.removeProduct(inventory.getProducts().get(0));
        inventory.removeProduct(inventory.getProducts().get(0));
        assertEquals(0, inventory.getProducts().size());
    }


    @Test
    public void testSaveLoadProducts() throws IOException {
        setupStage1();

        inventory.loadProducts();

        List<Product> loadedProducts = new Inventory().getProducts();
        assertEquals(2, loadedProducts.size());

        assertEquals("Product A", loadedProducts.get(0).getName());
        assertEquals(22, loadedProducts.get(0).getPrice());
        assertEquals(2, loadedProducts.get(0).getQuantity());
        assertEquals(2, loadedProducts.get(0).getTimesPurchased());
        assertEquals("Product B", loadedProducts.get(1).getName());
        assertEquals(56, loadedProducts.get(1).getPrice());
        assertEquals(6, loadedProducts.get(1).getQuantity());
        assertEquals(3, loadedProducts.get(1).getTimesPurchased());
    }

    @Test
    public void testAddOrder() {
        setupStage2();
        assertEquals(2, inventory.getOrders().size());
        assertEquals("Alejandra", inventory.getOrders().get(0).getCustomerName());
    }

    @Test
    public void testMultipleOrder(){
        setupStage2();
        ArrayList<Product> productss = new ArrayList<>();
        productss.add(new Product("Product 3", "the description 3", ProductCategory.STATIONERY, 30.0, 1, 30));
        inventory = new Inventory();
        inventory.addOrder(new Order("Mantilla", productss, 30, "02/12/2002"));

        ArrayList<Product> products2 = new ArrayList<>();
        productss.add(new Product("Product 4", "the description 4", ProductCategory.SPORTS, 14.0, 14, 1));
        inventory.addOrder(new Order("Pineda", productss, 350, "09/08/2006"));
        assertEquals(4, inventory.getOrders().size());
    }

    @Test
    public void testRemoveOrder() {
        setupStage2();
        inventory.removeOrder(inventory.getOrders().get(0));
        assertEquals(1, inventory.getOrders().size());
    }

    @Test
    public void SaveLoadOrders() {
        setupStage2();

        inventory.loadOrders();

        List<Order> loadedOrders = new inventory.getOrders();

        assertEquals("Alejandra", loadedOrders.get(0).getCustomerName());
        assertEquals(50, loadedOrders.get(0).getTotal());
        assertEquals("06/06/2006", loadedOrders.get(0).getDate());
        assertEquals("Pablo", loadedOrders.get(1).getCustomerName());
        assertEquals(105, loadedOrders.get(1).getTotal());
        assertEquals("07/07/2007", loadedOrders.get(1).getDate());
    }

}
