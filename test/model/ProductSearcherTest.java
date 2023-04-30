package model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testSearchProductByName() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByName("Product1");
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("Product1", results.get(0).getName());
    }

    //Verify the SearchByPrice method
    @Test
    public void testSearchByPrice(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByPrice(50);
        assertEquals(1, results.size());
        assertEquals(50, results.get(0).getPrice());

    }

    ////Verify the SearchByCategory method
    @Test
    public void testSearchByCategory() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByCategory(ProductCategory.ELECTRONICS);
        assertEquals(2, results.size());
        assertEquals("product1", results.get(0).getName());
        assertEquals("product3", results.get(1).getName());
    }

    //Verify the SearchTimesPurchased method
    @Test
    public void testSearchByTimesPurchased(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByTimesPurchased(2);
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getTimesPurchased);
    }

    //Verify the SearchByRangePrice method
    @Test
    public void testSearchByRangePrice() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByRangePrice(75, 150);
        assertEquals(1, results.size());
        assertEquals("product1", results.get(0).getName());
    }

    //Verify the SearchByRangeTimesPurchased method
    @Test
    public void testSearchByRangeTimesPurchased() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByRangeTimesPurchased(1, 4);
        assertEquals(2, results.size());
        assertEquals(2, results.get(0).getTimesPurchased);
        assertEquals(3, results.get(1).getTimesPurchased);
    }

    //Verify the SearchByRangeQuantity method
    @Test
    public void testSearchByRangeQuantity(){
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results=searcher.searchByRangeQuantity(1, 5);
        assertEquals(3, results.size());
        assertEquals(3, results.get(0).getQuantity());
        assertEquals(2, results.get(1).getQuantity());
    }

    //Verify the SearchByKeyword method
    @Test
    public void testSearchByKeyword() {
        setupStage1();

        ProductSearcher searcher = new ProductSearcher(inventory);

        List<Product> results = searcher.searchByKeyword("description");
        assertEquals(3, results.size());
        assertEquals("product1", results.get(0).getName());
        assertEquals("product2", results.get(1).getName());
        assertEquals("product3", results.get(2).getName());
    }

}
