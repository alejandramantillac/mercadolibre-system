package model;

public enum ProductCategory {
    BOOKS("Books"),
    ELECTRONICS("Electronics"),
    CLOTHING_ACCESSORIES("Clothing and Accessories"),
    FOOD_AND_BEVERAGE("Food and Beverage"),
    STATIONERY("Stationery"),
    SPORTS("Sports"),
    BEAUTY_AND_PERSONAL_CARE("Beauty and Personal Care"),
    TOYS_AND_GAMES("Toys and Games");

    private String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}