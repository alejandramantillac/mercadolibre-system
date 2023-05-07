package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderBinarySearcher<T> {
    private List<T> list;
    private Comparator<T> comparator;

    public OrderBinarySearcher(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.comparator = comparator;
    }

    public List<T> search(T value) {
        int index = binarySearch(list, value, comparator);
        if (index < 0) {
            return new ArrayList<>();
        }
        List<T> result = new ArrayList<>();
        result.add(list.get(index));
        for (int i = index - 1; i >= 0; i--) {
            if (comparator.compare(list.get(i), value) == 0) {
                result.add(0, list.get(i));
            } else {
                break;
            }
        }
        for (int i = index + 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i), value) == 0) {
                result.add(list.get(i));
            } else {
                break;
            }
        }
        return result;
    }

    private static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = list.get(mid);
            int cmp = c.compare(midVal, key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }
}

