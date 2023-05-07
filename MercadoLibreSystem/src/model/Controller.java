package model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.Inventory;
import data.OrderManager;
import exceptions.InvalidDateFormatException;
import exceptions.InvalidOptionEnteredException;
import exceptions.*;

public class Controller {
    private Inventory inventory;
    private List<Order> orders;

    private OrderManager orderManager;
    private ProductSearcher productSearcher;
    private OrderSearcher orderSearcher;
    private Scanner scanner;


    public Controller() {
        this.inventory = new Inventory();
        this.orderManager = new OrderManager("orders.json");
        this.orders = new ArrayList<>();
        this.productSearcher = new ProductSearcher(this.inventory);
        this.orderSearcher = new OrderSearcher();
        this.scanner = new Scanner(System.in);
        orderManager.loadOrders();
    }

    // Add product method
    public void addProduct() {
        String name = getProductName();
        String description = getProductDescription();
        int categoryInt = getProductCategoryInt();
        String category = setCategoryToString(categoryInt);

        if (!category.equals("")) {
            ProductCategory productCategory = ProductCategory.valueOf(category);
            double price = getProductPrice();
            int quantity = getProductQuantity();
            int timesPurchased = getProductTimesPurchased();
            Product product = new Product(name, description, productCategory, price, quantity, timesPurchased);
            inventory.addProduct(product);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("\nInvalid category selected. Product not added.");
        }
    }


    // Remove product method

    // Search products methods

    public void searchProductsByName() throws ProductNotFoundException {
        int optionName = showOptionNamesMenu();
        scanner.nextLine();

        boolean isValid = validateRange(optionName, 1, 2);

        if (isValid) {
            String productName = getProductName();
            if (optionName == 1) {
                try {
                    List<Product> products = productSearcher.searchProductsByName(productName);
                    if (products.isEmpty()) {
                        System.out.println("No products found.");
                    } else if (products.size() == 1) {
                        for (Product product : products) {
                            System.out.println("Product found: " + product.toString());
                        }
                    } else {
                        System.out.println(products.size() + " products found.");
                        chooseWayPrintProduct();
                        int optionPrint = scanner.nextInt();

                        boolean isOnRange = validateRange(optionPrint, 1, 2);

                        if (isOnRange) {
                            if (optionPrint == 1) {
                                for (Product p : products) {
                                    System.out.println(p.toString());
                                }
                            } else {
                                Collections.reverse(products);
                                for (Product p : products) {
                                    System.out.println(p.toString());
                                }
                            }
                        } else {
                            System.out.println("Error. Invalid option entered.");
                        }
                    }
                } catch (ProductNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    List<Product> products = productSearcher.searchProductsByKeyword(productName);
                    for (Product product : products) {
                        System.out.println("Product found: " + product.toString());
                    }
                } catch (ProductNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void searchProductsByPrice() {
        double exactPrice, minPrice, maxPrice;
        showOptionPricesMenu();
        int optionPrices = scanner.nextInt();
        boolean isValid = validateRange(optionPrices, 1, 2);

        if (isValid) {
            if(optionPrices == 1) {
                exactPrice = getProductPrice();
                try {
                    List<Product> products = productSearcher.searchProductsByPrice(exactPrice);
                    System.out.println("Products found:");
                    for (Product product : products) {
                        System.out.println(product.toString());
                    }
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                int optionToShow = chooseWayPrintProduct();

                boolean isOnRange = validateRange(optionToShow, 1, 2);

                minPrice = getMinPrice();
                maxPrice = getMaxPrice();

                if(isOnRange) {
                    try {
                        List<Product> products = productSearcher.searchProductsByPriceRange(minPrice, maxPrice);

                        if(optionToShow == 1) {
                            System.out.println("Products found:");
                            Collections.reverse(products);
                            for (Product product : products) {
                                System.out.println(product.toString());
                            }
                        } else {
                            System.out.println("Products found:");
                            for (Product product : products) {
                                System.out.println(product.toString());
                            }
                        }
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Invalid option selected.");
                }
            }
        }
    }

    public void searchProductsByCategory() {
        System.out.println("\nEnter product category: \n1. Books \n2. Electronics \n3. Clothing & accesories \n4. Food and beverage \n5. Stationery \n6. Sports \n7. Beauty and personal care \n8. Toys and games ");
        int categoryInt = scanner.nextInt();
        scanner.nextLine();

        ProductCategory category;
        switch (categoryInt) {
            case 1: category = ProductCategory.BOOKS; break;
            case 2: category = ProductCategory.ELECTRONICS; break;
            case 3: category = ProductCategory.CLOTHING_ACCESSORIES; break;
            case 4: category = ProductCategory.FOOD_AND_BEVERAGE; break;
            case 5: category = ProductCategory.STATIONERY; break;
            case 6: category = ProductCategory.SPORTS; break;
            case 7: category = ProductCategory.BEAUTY_AND_PERSONAL_CARE; break;
            case 8: category = ProductCategory.TOYS_AND_GAMES; break;
            default: return;
        }

        try {
            List<Product> results = productSearcher.searchProductsByCategory(category);
            if (results.isEmpty()) {
                System.out.println("\nNo products found for category " + category);
            } else {
                System.out.println("\nProducts in category " + category + ":");
                for (Product product : results) {
                    System.out.println(product);
                }
            }
        } catch (ProductNotFoundException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void searchProductsByTimesPurchased() throws ProductNotFoundException {
        showOptionTimesPurchasedMenu();

        int optionTimesPurchased = scanner.nextInt();

        boolean isValid = validateRange(optionTimesPurchased, 1, 2);

        if(isValid) {
            if (optionTimesPurchased == 1) {
                int timesPurchased = getProductTimesPurchased();
                List<Product> products = productSearcher.searchProductsByTimesPurchased(timesPurchased);

                try {
                    if (products.isEmpty()) {
                        System.out.println("No products found within the specified range of times purchased.");
                    } else {
                        System.out.println("Products found: ");
                        for (Product product : products) {
                            System.out.println(product.toString());
                        }
                    }
                } catch(ProductNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    int minTimesPurchased = getMinTimesPurchased();
                    int maxTimesPurchased = getMaxTimesPurchased();

                    int optionToShow = chooseWayPrintProduct();

                    boolean isOnRange = validateRange(optionToShow, 1, 2);

                    if(isOnRange) {
                        List<Product> products = productSearcher.searchProductsByTimesRangePurchased(minTimesPurchased, maxTimesPurchased);

                        if(optionToShow == 1) {
                            Collections.reverse(products);
                            for (Product p : products) {
                                System.out.println(p.toString());
                            }
                        } else {
                            for (Product p : products) {
                                System.out.println(p.toString());
                            }
                        }
                    } else {
                        System.out.println("Invalid option selected.");
                    }

                } catch(ProductNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void searchProductsByQuantity() {
        showOptionQuantityMenu();

        int quantityOption = scanner.nextInt();

        boolean isValid = validateRange(quantityOption, 1, 2);

        if (isValid) {
            if (quantityOption == 1) {
                try {
                    int quantity = getProductQuantity();
                    List<Product> matchingProducts = productSearcher.searchProductsByQuantity(quantity);
                    System.out.println("Products with quantity " + quantity + ":");
                    for (Product product : matchingProducts) {
                        System.out.println(product.toString());
                    }
                } catch(ProductNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    int minQuantity = getMinQuantityToSearch();
                    int maxQuantity = getMaxQuantityToSearch();

                    int optionToShow = chooseWayPrintProduct();

                    boolean isOnRange = validateRange(optionToShow, 1, 2);

                    if(isOnRange) {
                        List<Product> products = productSearcher.searchProductsByTimesRangePurchased(minQuantity, maxQuantity);

                        if(optionToShow == 1) {
                            Collections.reverse(products);
                            for (Product p : products) {
                                System.out.println(p.toString());
                            }
                        } else {
                            for (Product p : products) {
                                System.out.println(p.toString());
                            }
                        }
                    } else {
                        System.out.println("Invalid option selected.");
                    }

                } catch(ProductNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProductName() {
        System.out.print("\nEnter product name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String getProductDescription() {
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        return description;
    }

    public int getProductCategoryInt() {
        System.out.println("Enter product category: \n1. Books \n2. Electronics \n3. Clothing & accesories \n4. Food and beverage \n5. Stationery \n6. Sports \n7. Beauty and personal care \n8. Toys and games ");
        int categoryInt = scanner.nextInt();

        return categoryInt;
    }

    public String setCategoryToString(int categoryInt) {
        String category;
        switch(categoryInt) {
            case 1: category = ProductCategory.BOOKS.toString(); break;
            case 2: category = ProductCategory.ELECTRONICS.toString(); break;
            case 3: category = ProductCategory.CLOTHING_ACCESSORIES.toString(); break;
            case 4: category = ProductCategory.FOOD_AND_BEVERAGE.toString(); break;
            case 5: category = ProductCategory.STATIONERY.toString(); break;
            case 6: category = ProductCategory.SPORTS.toString(); break;
            case 7: category = ProductCategory.BEAUTY_AND_PERSONAL_CARE.toString(); break;
            case 8: category = ProductCategory.TOYS_AND_GAMES.toString(); break;
            default: category = ""; break;
        }

        return category;
    }

    public double getProductPrice() {
        System.out.print("\nEnter product price: ");
        double price = scanner.nextDouble();

        return price;
    }

    public int getProductQuantity() {
        System.out.print("\nEnter product quantity: ");
        int quantity = scanner.nextInt();

        return quantity;
    }

    public int getProductTimesPurchased() {
        System.out.println("\nEnter product times purchased: ");
        int timesPurchased = scanner.nextInt();

        return timesPurchased;
    }

    // Auxiliar methods
    public int showOptionNamesMenu() {
        System.out.println("1. Search by exact name\n2. Search by keyword");
        int option = scanner.nextInt();

        return option;
    }

    public void showOptionPricesMenu() {
        System.out.println("Select an option: \n1. Search by exact price\n2. Search by price range");
    }

    public double getMinPrice() {
        System.out.print("Enter the minimum price: ");
        double minPrice = scanner.nextDouble();

        return minPrice;
    }

    public double getMaxPrice() {
        System.out.print("Enter the maximum price: ");
        double maxPrice = scanner.nextDouble();

        return maxPrice;
    }

    public void showOptionTimesPurchasedMenu() {
        System.out.println("Select an option: \n1. Search by exact value \n2. Search by range");
    }

    public int getMinTimesPurchased() {
        System.out.print("Enter the minimum number of times the product must have been purchased: ");
        int minTimesPurchased = scanner.nextInt();

        return minTimesPurchased;
    }

    public int getMaxTimesPurchased() {
        System.out.print("Enter the maximum number of times the product must have been purchased: ");
        int maxTimesPurchased = scanner.nextInt();

        return maxTimesPurchased;
    }

    public void showOptionQuantityMenu() {
        System.out.println("Select an option: \n1. Search by exact quantity \n2. Search by range");
    }

    public int getMinQuantityToSearch() {
        System.out.println("Type the exact quantity value to search: ");
        int minQuantity = scanner.nextInt();

        return minQuantity;
    }

    public int getMaxQuantityToSearch() {
        System.out.println("Type the exact quantity value to search: ");
        int maxQuantity = scanner.nextInt();

        return maxQuantity;
    }

    private int chooseWayPrintProduct() {
        System.out.println("How would you like to order the results?: \n1. Ascending \n2. Descending");
        int optionOrder = scanner.nextInt();

        return optionOrder;
    }

    public boolean validateRange(int value, int min, int max) {
        boolean isValid = true;
        if (value < min || value > max) {
            isValid = false;
        }
        return isValid;
    }
}