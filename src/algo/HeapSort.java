package algo;

import java.util.Arrays;

/**
 * @author 李高丞
 * @version 1.0
 */
public class HeapSort {

    private final Integer[] array;
    private int length = 0;

    public HeapSort(int max) {
        array = new Integer[max + 1];
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void insert(Integer value) {
        array[++length] = value;
        swim(length);
        System.out.println(Arrays.toString(array));
    }

    public int delMax() {
        int max = array[1];
        swap(1, length--);
        array[length + 1] = null;
        sink();
        return max;
    }

    // 上浮
    private void swim(int k) {
        while (k > 1 && array[k / 2] < array[k]) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    // 下沉
    private void sink() {
        int k = 1;
        while (2 * k <= array.length) {
            int j = 2 * k;
            if (j < array.length && array[j] < array[j + 1]) j++;
            if (array[k] > array[j]) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
