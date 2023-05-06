package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import exceptions.ProductNotFoundException;
import org.testng.annotations.Test;

public class OrderSearcherTest {

    private OrderSearcher searcher;


    public void setupStage1(){
        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(new Product("product1", "description1", ProductCategory.SPORTS, 10.0, 15, 9));

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(new Product("product2", "description2", ProductCategory.STATIONERY, 5.0, 12, 6));

        Order order1 = new Order("customer1", products1, 20.5, "18/07/2020");
        Order order2 = new Order("customer2", products2, 50.7, "31/12/2022");

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        searcher= new OrderSearcher(orders);
    }

    @Test
    public void testSearchByCustomerNameOrder() {
        setupStage1();

        List<Order>results = searcher.searchByCustomerName("customer1");
        assertEquals(1, results.size());
        assertEquals("customer1", results.get(0).getCustomerName());
    }

    @Test
    public void testSearchByCustomerNameOrderNonExistent() {
        setupStage1();

        try{
            List<Order>results = searcher.searchByCustomerName("customer1");
            searcher.searchByCustomerName("customer1");
        }catch (OrderNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No orders found with that criteria.", e.getMessage());
        }
    }

    @Test
    public void testSearchByTotalOrder(){
        setupStage1();

        List<Order>results = searcher.searchOrdersByTotal(20.5);
        assertEquals(1, results.size());
        assertEquals("customer1", results.get(0).getCustomerName());
    }

    @Test
    public void testSearchByTotalOrderNonExistent(){
        setupStage1();

        try{
            List<Order>results = searcher.searchOrdersByTotal(40);
            searcher.searchOrdersByTotal(40);
        }catch (OrderNotFoundException e){
            assertNotNull(e.getMessage());
            assertEquals("No orders found with that criteria.", e.getMessage());
        }
    }

    @Test
    public void testSearchByDateOrder(){
        setupStage1();

        List<Order>results = searcher.searchOrdersByDate("18/07/2020");
        assertEquals(1, results.size());
        assertEquals("customer1", results.get(0).getCustomerName());
    }

}
