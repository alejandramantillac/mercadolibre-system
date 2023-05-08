package ui;


import model.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    private Controller controller;

    public Menu() {
        controller = new Controller();
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Controller getController() {
        return controller;
    }

    public void start() throws IOException {
        boolean exit = false;
        while (!exit) {
            showInitialMenu();

            int option = getOptionMenu();

            switch (option) {
                case 1:
                    controller.addProduct();
                    break;
                case 2:
                    controller.removeProduct();
                    break;
                case 3:
                    searchProductsMenu();
                    break;
                case 4:
                    controller.addOrder();
                    break;
                case 5:
                    // search orders menu
                    break;
                case 6:
                    controller.increaseProductQuantity();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public void showInitialMenu() {
        System.out.println("\n====== MENU ======");
        System.out.println("1. Add product\n2. Remove product\n3. Search products\n4. Add order\n5. Search orders\n6. Increase product quantity\nEnter an option: ");
    }

    public int getOptionMenu() {
        int option = scanner.nextInt();

        return option;
    }

    // Search products methods
    private void searchProductsMenu() {
        boolean exit = false;
        while (!exit) {
            int option = selectOptionToSearchProducts();

            switch (option) {
                case 1:
                    controller.searchProductsByName();
                    break;
                case 2:
                    controller.searchProductsByPrice();
                    break;
                case 3:
                    controller.searchProductsByCategory();
                    break;
                case 4:
                    controller.searchProductsByTimesPurchased();
                    break;
                case 5:
                    controller.searchProductsByQuantity();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public int selectOptionToSearchProducts() {
        System.out.println("\n====== SEARCH PRODUCTS ======");
        System.out.println("1. Search by name\n2.Search by price\n3. Search by category\n4. Search by times purcharsed\n5. Search by quantity \n6.Return to main menu\nEnter an option: ");
        int option = scanner.nextInt();

        return option;
    }

    public int selectOptionSearchOrders() {
        System.out.println("\n====== SEARCH ORDERS ======");
        System.out.println("1. Search by customer name \n2. Search by total price \n3. Search by order date  \n4. Return to main menu \nEnter an option: ");
        int option = scanner.nextInt();

        return option;
    }

}
