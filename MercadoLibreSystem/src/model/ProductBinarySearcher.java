package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductBinarySearcher<T> {
    private List<Product> productList;
    private T value;
    private Comparator<Product> comparator;

    public ProductBinarySearcher(List<Product> productList, T value, Comparator<Product> comparator) {
        this.productList = productList;
        this.value = value;
        this.comparator = comparator;
    }

    public List<Product> search() {
        int index = Collections.binarySearch(productList, new Product("", "", null, 0, 0, 0) {
            @Override
            public int compareTo(Product other) {
                return comparator.compare(this, other);
            }
        }.setValue(value), comparator);

        if (index >= 0) {
            List<Product> result = new ArrayList<>();
            result.add(productList.get(index));
            for (int i = index - 1; i >= 0; i--) {
                if (comparator.compare(productList.get(i), result.get(0)) == 0) {
                    result.add(0, productList.get(i));
                } else {
                    break;
                }
            }
            for (int i = index + 1; i < productList.size(); i++) {
                if (comparator.compare(productList.get(i), result.get(result.size() - 1)) == 0) {
                    result.add(productList.get(i));
                } else {
                    break;
                }
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
