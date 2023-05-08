package model;

import exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import data.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {
    private Product product;

    private Inventory inventory;

    public void setup1() {
        product = new Product("Test Product", "A product for testing purposes", ProductCategory.ELECTRONICS, 999.99, 10, 0);
        inventory=new Inventory();
    }

    @Test
    public void testGetName() {
        setup1();

        assertEquals("Test Product", product.getName());
    }

    @Test
    public void testGetDescription() {
        setup1();

        assertEquals("A product for testing purposes", product.getDescription());
    }

    @Test
    public void testGetCategory() {
        setup1();

        assertEquals(ProductCategory.ELECTRONICS, product.getCategory());
    }

    @Test
    public void testGetPrice() {
        setup1();

        assertEquals(999.99, product.getPrice(), 0.01);
    }

    @Test
    public void testGetQuantity() {
        setup1();

        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testGetTimesPurchased() {
        setup1();

        assertEquals(0, product.getTimesPurchased());
    }


    @Test
    public void testSetName() {
        setup1();

        product.setName("New Name");
        assertEquals("New Name", product.getName());
    }

    @Test
    public void testSetDescription() {
        setup1();

        product.setDescription("New Description");
        assertEquals("New Description", product.getDescription());
    }

    @Test
    public void testSetCategory() {
        setup1();

        product.setCategory(ProductCategory.CLOTHING_ACCESSORIES);
        assertEquals(ProductCategory.CLOTHING_ACCESSORIES, product.getCategory());
    }

    @Test
    public void testSetPrice() {
        setup1();

        product.setPrice(1499.99);
        assertEquals(1499.99, product.getPrice(), 0.01);
    }

    @Test
    public void testSetQuantity() {
        setup1();

        product.setQuantity(20);
        assertEquals(20, product.getQuantity());
    }

    @Test
    public void testSetTimesPurchased() {
        setup1();

        product.setTimesPurchased(2);
        assertEquals(2, product.getTimesPurchased());
    }

    @Test
    public void increaseProductQuantity(){
        setup1();
        inventory.addProduct(product);

        List<Product> products = inventory.searchProduct("Test Product");
        int quantity=10;


        products.get(0).setQuantity(products.get(0).getQuantity()+quantity);

        assertEquals(20, product.getQuantity());
    }

    @Test
    public void increaseProductQuantityNonExistent(){
        setup1();
        inventory.addProduct(product);

        try {
            List<Product> products = inventory.searchProduct("Producto punto");
            inventory.searchProduct("Producto punto");

            int quantity=15;

            products.get(0).setQuantity(products.get(0).getQuantity()+quantity);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }
    }

    @Test
    public void decreaseProductQuantity(){
        setup1();
        inventory.addProduct(product);

        List<Product> products = inventory.searchProduct("Test Product");
        int quantity=-1;

        products.get(0).setQuantity(products.get(0).getQuantity()+quantity);

        assertEquals(9, product.getQuantity());
    }

    @Test
    public void decreaseProductQuantityNonExistent(){
        setup1();
        inventory.addProduct(product);

        try {
            List<Product> products = inventory.searchProduct("Producto punto");
            inventory.searchProduct("Producto punto");

            int quantity=-2;

            products.get(0).setQuantity(products.get(0).getQuantity()+quantity);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }
    }
}
