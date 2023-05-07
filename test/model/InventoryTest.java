package model;



import static org.junit.jupiter.api.Assertions.*;

import data.Inventory;
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
        inventory.saveProducts();
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
}
