package data;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Inventory {
    private List<Product> products;
    private final Gson gson;
    private final Type productListType;

    public Inventory() {
        this.products = new ArrayList<>();
        this.gson = new Gson();
        this.productListType = new TypeToken<List<Product>>(){}.getType();
        loadProducts();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void sort(Comparator<Product> comparator) {
        products.sort(comparator);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        saveProducts();
    }

    public List<Product> searchProduct(String keyword) {
        List<Product> results = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getName().contains(keyword) || product.getDescription().contains(keyword)) {
                results.add(product);
            }
        }
        return results;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    private void saveProducts() {
        try {
            String json = gson.toJson(this.products, productListType);
            Files.write(Paths.get("products.json"), json.getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred while saving products.");
            e.printStackTrace();
        }
    }

    private void loadProducts() {
        try {
            File file = new File("products.json");
            if (file.exists()) {
                String json = new String(Files.readAllBytes(Paths.get("products.json")));
                this.products = gson.fromJson(json, productListType);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading products.");
            e.printStackTrace();
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        saveProducts();
    }

}