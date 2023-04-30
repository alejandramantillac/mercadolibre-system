package model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    public void testRemoveProduct() {
        setupStage1();
        inventory.removeProduct(inventory.getProducts().get(0));
        assertEquals(1, inventory.getProducts().size());
    }

    @Test
    public void testSaveLoadProducts() throws IOException {
        setupStage1();

        inventory.loadProducts();

        List<Product> loadedProducts = new Inventory().getProducts();
        Assertions.assertEquals(2, loadedProducts.size());

        Assertions.assertEquals("Product A", loadedProducts.get(0).getName());
        Assertions.assertEquals(22, loadedProducts.get(0).getPrice());
        Assertions.assertEquals(2, loadedProducts.get(0).getQuantity());
        Assertions.assertEquals(2, loadedProducts.get(0).getTimesPurchased());
        Assertions.assertEquals("Product B", loadedProducts.get(1).getName());
        Assertions.assertEquals(56, loadedProducts.get(1).getPrice());
        Assertions.assertEquals(6, loadedProducts.get(1).getQuantity());
        Assertions.assertEquals(3, loadedProducts.get(1).getTimesPurchased());
    }



}
