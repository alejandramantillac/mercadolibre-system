package model;

import data.Inventory;
import exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductSearcher {

    private ArrayList products;
    private Inventory inventory;

    public ProductSearcher(Inventory inventory) {
        this.inventory = inventory;

    }

    public List<Product> searchProductsByName(String name) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        Collections.sort(productList, Comparator.comparing(Product::getName));
        Comparator<Product> productComparator = Comparator.comparing(Product::getName);
        ProductBinarySearcher<String> searcher = new ProductBinarySearcher<>(productList, name, productComparator);
        List<Product> foundProducts = searcher.search();
        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return foundProducts;
    }

    public List<Product> searchProductsByPrice(double price) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        Collections.sort(productList, Comparator.comparing(Product::getPrice));
        Comparator<Product> productComparator = Comparator.comparing(Product::getPrice);
        ProductBinarySearcher<Double> searcher = new ProductBinarySearcher<>(productList, price, productComparator);
        List<Product> foundProducts = searcher.search();
        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return foundProducts;
    }

    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        Collections.sort(productList, Comparator.comparing(Product::getPrice));
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredList.add(product);
            }
        }
        Collections.reverse(filteredList);
        if (filteredList.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return filteredList;
    }

    public List<Product> searchProductsByCategory(ProductCategory category) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        Collections.sort(productList, Comparator.comparing(Product::getCategory));
        Comparator<Product> productComparator = Comparator.comparing(Product::getCategory);
        ProductBinarySearcher<ProductCategory> searcher = new ProductBinarySearcher<>(productList, category, productComparator);
        List<Product> foundProducts = searcher.search();
        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return foundProducts;
    }

    public List<Product> searchProductsByTimesPurchased(int timesPurchased) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        Collections.sort(productList, Comparator.comparing(Product::getTimesPurchased));
        Comparator<Product> productComparator = Comparator.comparing(Product::getTimesPurchased);
        ProductBinarySearcher<Integer> searcher = new ProductBinarySearcher<>(productList, timesPurchased, productComparator);
        List<Product> searchResult = searcher.search();
        if (searchResult.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return searchResult;
    }

    public List<Product> searchProductsByTimesRangePurchased(int minTimesPurchased, int maxTimesPurchased) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        Collections.sort(productList, Comparator.comparing(Product::getTimesPurchased));
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

        if (filteredList.isEmpty()) {
            throw new ProductNotFoundException();
        }

        Collections.reverse(filteredList);
        return filteredList;
    }

    public List<Product> searchProductsByKeyword(String keyword) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        List<Product> filteredList = new ArrayList<>();
        Collections.sort(productList, Comparator.comparing(Product::getName));

        for (Product product : productList) {
            String productName = product.getName();
            if (productName.contains(keyword)) {
                filteredList.add(product);
            }
        }

        Comparator<Product> productComparator = Comparator.comparing(Product::getName);
        filteredList.sort(productComparator);

        if (filteredList.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return filteredList;
    }

    public List<Product> searchProductsByQuantity(int quantity) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getQuantity() == quantity) {
                foundProducts.add(product);
            }
        }
        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return foundProducts;
    }

    public List<Product> searchProductsByRangeQuantity(int minQuantity, int maxQuantity) throws ProductNotFoundException {
        List<Product> productList = inventory.getProducts();
        List<Product> filteredList = new ArrayList<>();
        Collections.sort(productList, Comparator.comparing(Product::getQuantity));

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

        if (filteredList.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return filteredList;
    }
}
