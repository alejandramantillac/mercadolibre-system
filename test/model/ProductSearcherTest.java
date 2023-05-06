package model;

import exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSearcherTest {

    private  Inventory inventory;

    //Stage
    public void setupStage1(){
        Product product1 = new Product("product1", "description1", ProductCategory.ELECTRONICS, 100, 5, 2);
        Product product2 = new Product("product2", "description2", ProductCategory.BOOKS, 50, 3, 3);
        Product product3 = new Product("product3", "description3", ProductCategory.ELECTRONICS, 200, 2, 0);

        inventory = new Inventory();
        inventory.getProducts().clear(); // Clean products loaded from JSON file
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);
    }

    //Verify the SearchProductByName method
    @Test
    public void testSearchProductsByName() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByName("product1");
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("product1", results.get(0).getName());
    }

    @Test
    public void testSearchProductsByNameNonExistent(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results = searcher.searchProductsByName("Product198688986");
            searcher.searchProductsByName("Product198688986");

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }
    }

    //Verify the SearchByPrice method
    @Test
    public void testSearchProductsByPrice(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByPrice(50);
        assertEquals(1, results.size());
        assertEquals(50, results.get(0).getPrice());

    }

    @Test
    public void testSearchProductsByPriceNonExistent(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);
        try{
            List<Product> results = searcher.searchProductsByPrice(1);
            searcher.searchProductsByPrice(1);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }

    }

    //Verify the SearchByCategory method
    @Test
    public void testSearchProductsByCategory() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByCategory(ProductCategory.ELECTRONICS);
        assertEquals(2, results.size());
        assertEquals("product1", results.get(0).getName());
        assertEquals("product3", results.get(1).getName());
    }

    @Test
    public void testSearchProductsByCategoryNonExistent() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results = searcher.searchProductsByCategory(ProductCategory.SPORTS);
            searcher.searchProductsByCategory(ProductCategory.SPORTS);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }

    }

    @Test
    public void testSearchProductsByQuantity(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results=searcher.searchProductsByQuantity(2);
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getQuantity());
    }

    @Test
    public void testSearchProductsByQuantityNonExistent(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results=searcher.searchProductsByQuantity(9);
            searcher.searchProductsByQuantity(9);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }
    }

    //Verify the SearchTimesPurchased method
    @Test
    public void testSearchProductsByTimesPurchased(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByTimesPurchased(2);
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getTimesPurchased());
    }

    @Test
    public void testSearchProductsByTimesPurchasedNonExistent(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results = searcher.searchProductsByTimesPurchased(500);
            searcher.searchProductsByTimesPurchased(500);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }

    }

    //Verify the SearchByRangePrice method
    @Test
    public void testSearchProductsByRangePrice() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByPriceRange(75, 150);
        assertEquals(1, results.size());
        assertEquals("product1", results.get(0).getName());
    }

    @Test
    public void testSearchProductsByRangePriceNonExistent() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results = searcher.searchProductsByPriceRange(1000, 1001);
            searcher.searchProductsByPriceRange(1000, 1001);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }

    }

    //Verify the SearchByRangeTimesPurchased method
    @Test
    public void testSearchProductsByRangeTimesPurchased() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByTimesRangePurchased(1, 4);
        assertEquals(2, results.size());
        assertEquals(2, results.get(0).getTimesPurchased());
        assertEquals(3, results.get(1).getTimesPurchased());
    }

    @Test
    public void testSearchProductsByRangeTimesPurchasedNonExistent() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results = searcher.searchProductsByTimesRangePurchased(10, 20);
            searcher.searchProductsByTimesRangePurchased(10, 20);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }

    }

    //Verify the SearchByRangeQuantity method
    @Test
    public void testSearchProductsByRangeQuantity(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results=searcher.searchProductsByRangeQuantity(1, 5);
        assertEquals(3, results.size());
        assertEquals(3, results.get(0).getQuantity());
        assertEquals(2, results.get(1).getQuantity());
    }

    @Test
    public void testSearchProductsByRangeQuantityNonExistent(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results=searcher.searchProductsByRangeQuantity(10, 50);
            searcher.searchProductsByRangeQuantity(10, 50);

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }
    }

    //Verify the SearchByKeyword method
    @Test
    public void testSearchProductsByKeyword() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchProductsByKeyword("description");
        assertEquals(3, results.size());
        assertEquals("product1", results.get(0).getName());
        assertEquals("product2", results.get(1).getName());
        assertEquals("product3", results.get(2).getName());
    }

    @Test
    public void testSearchProductsByKeywordNonExistent() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        try {
            List<Product> results = searcher.searchProductsByKeyword("&%$#/");
            searcher.searchProductsByKeyword("&%$#/");

        }catch (ProductNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No products found with that criteria.", e.getMessage());
        }
    }

}