package model;

import data.Inventory;
import exceptions.ProductNotFoundException;

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

    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> productList = inventory.getProducts();
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredList.add(product);
            }
        }
        Collections.reverse(filteredList);
        return filteredList;
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

    public List<Product> searchProductsByTimesRangePurchased(int minTimesPurchased, int maxTimesPurchased) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        List<Product> filteredList = new ArrayList<>();
        int totalPurchased = maxTimesPurchased - minTimesPurchased;

        int initialPurchased = minTimesPurchased;
        for(int i = 0; i <= totalPurchased; i++) {
            initialPurchased++;
            for (Product product : productList) {
                if (product.getTimesPurchased() == initialPurchased) {
                    filteredList.add(product);
                }
            }
        }
        Collections.reverse(filteredList);
        return filteredList;
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

    public List<Product> searchProductsByRangeQuantity(int minQuantity, int maxQuantity) {
        List<Product> productList = inventory.getProducts();
        List<Product> filteredList = new ArrayList<>();

        int initialRange = minQuantity;
        for(int i = minQuantity; i <= maxQuantity; i++) {
            initialRange++;
            for (Product product : productList) {
                if (product.getQuantity() == initialRange) {
                    filteredList.add(product);
                }
            }
        }
        Collections.reverse(filteredList);
        return filteredList;
    }
}
