package model;

/*
import com.google.gson.Gson;

 */
public class Product implements Comparable<Product> {
    private String name;
    private String description;
    private ProductCategory category;
    private double price;
    private int quantity;

    private int timesPurchased;

    public Product(String name, String description, ProductCategory category, double price, int quantity, int timesPurchased) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.timesPurchased = timesPurchased;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getTimesPurchased() {
        return timesPurchased;
    }

    public void setTimesPurchased(int timesPurchased) {
        this.timesPurchased = timesPurchased;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description=" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                ", timesPurchased=" + timesPurchased;
    }


    @Override
    public int compareTo(Product other) {
        int result = this.name.compareTo(other.name);
        if (result == 0) {
            result = Double.compare(this.price, other.price);
            if (result == 0) {
                result = this.category.compareTo(other.category);
                if (result == 0) {
                    result = Integer.compare(this.timesPurchased, other.timesPurchased);
                    if (result == 0) {
                        result = Integer.compare(this.quantity, other.quantity);
                    }
                }
            }
        }
        return result;
    }

    public Product setValue(Object value) {
        // Cast the value to the correct type and set it on the appropriate field
        if (value instanceof String) {
            setName((String) value);
        } else if (value instanceof Double) {
            setPrice((Double) value);
        } else if (value instanceof ProductCategory) {
            setCategory((ProductCategory) value);
        } else if (value instanceof Integer) {
            setTimesPurchased((Integer) value);
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
        }
        return this;
    }

}