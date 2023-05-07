package model;

import data.Inventory;

import java.util.Comparator;
import java.util.List;

public class ProductSearcher {


    private Inventory inventory;

    public ProductSearcher(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Product> searchProductsByName(String name) {
        List<Product> productList = inventory.getProducts();
        Comparator<Product> productComparator = Comparator.comparing(Product::getName);
        ProductBinarySearcher<String> searcher = new ProductBinarySearcher<>(productList, name, productComparator);
        return searcher.search();
    }

    public List<Product> searchProductsByPrice(double price) {
        List<Product> productList = inventory.getProducts();
        Comparator<Product> productComparator = Comparator.comparing(Product::getPrice);
        ProductBinarySearcher<Double> searcher = new ProductBinarySearcher<>(productList, price, productComparator);
        return searcher.search();
    }

}
