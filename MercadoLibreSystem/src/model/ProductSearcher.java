package model;

import data.Inventory;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Product> searchProductsByCategory(ProductCategory category) {
        List<Product> productList = inventory.getProducts();
        Comparator<Product> productComparator = Comparator.comparing(Product::getCategory);
        ProductBinarySearcher<ProductCategory> searcher = new ProductBinarySearcher<>(productList, category, productComparator);
        return searcher.search();
    }

    public List<Product> searchProductsByTimesPurchased(int timesPurchased) {
        List<Product> productList = inventory.getProducts();
        Comparator<Product> productComparator = Comparator.comparing(Product::getTimesPurchased);
        ProductBinarySearcher<Integer> searcher = new ProductBinarySearcher<>(productList, timesPurchased, productComparator);
        return searcher.search();
    }

    public List<Product> searchProductsByKeyword(String keyword) {
        List<Product> productList = inventory.getProducts();

        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(product);
            }
        }

        Comparator<Product> productComparator = Comparator.comparing(Product::getName);
        Collections.sort(filteredList, productComparator);

        ProductBinarySearcher<String> searcher = new ProductBinarySearcher<>(filteredList, keyword, productComparator);
        return searcher.search();
    }

    public List<Product> searchProductsByQuantity(int quantity) {
        List<Product> productList = inventory.getProducts();
        Comparator<Product> productComparator = Comparator.comparing(Product::getQuantity);
        ProductBinarySearcher<Integer> searcher = new ProductBinarySearcher<>(productList, quantity, productComparator);
        return searcher.search();
    }

}
