package algo;

import java.util.*;

/**
 * @author 李高丞
 * @version 1.0 Beta
 */
public class SortUtil {

    public static void BubbleSort(int[] array) {
        boolean isSwap = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    swap(array, i, j);
                    isSwap = true;
                }
            }
            if (!isSwap) break;
        }
    }

    public static void SelectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = minIndex + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) minIndex = j;
            }
            swap(array, i, minIndex);
        }
    }

    public static void InsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int leftIndex = i - 1;
            while (leftIndex >= 0 && array[leftIndex] > tmp) {
                array[leftIndex + 1] = array[leftIndex];
                leftIndex--;
            }
            array[leftIndex + 1] = tmp;
        }
    }

    public static void ShellSort(int[] array) {
        int length = array.length;
        int h = 1;
        while (h < length / 3) h = 3 * h + 1; // h = 1, 4, 13, 40, 121 ...
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (array[j] < array[j - h]) swap(array, j, j - h);
                }
            }
            h = h / 3;
        }
        printArray(array);
    }

    public static void HeapSort(int[] array) {
        // 构建大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, i, 0);
            adjustHeap(array, 0, i);
        }
    }

    private static void adjustHeap(int[] array, int i, int length) {
        int tmp = i;
        if (i >= length) return;
        if (2 * i + 1 < length && array[2 * i + 1] > array[tmp])
            tmp = 2 * i + 1;
        if (2 * i + 2 < length && array[2 * i + 2] > array[tmp])
            tmp = 2 * i + 2;

        if (tmp != i) {
            swap(array, i, tmp);
            adjustHeap(array, tmp, length);
        }
    }

    public static void QuickSort(int[] array, int left, int right) {
        if (left >= right) return;
        int i = partition(array, left, right);
        QuickSort(array, left, i - 1);
        QuickSort(array, i + 1, right);
    }

    public static void QuickSortThreeWay(int[] array, int low, int high) {
        if (high <= low) return;
        int left = low, i = low + 1, right = high;
        int value = array[low];
        while (i <= right) {
            if (array[i] < value) swap(array, left++, i++);
            else if (array[i] > value) swap(array, i, right--);
            else i++;
        }
        QuickSortThreeWay(array, low, left - 1);
        QuickSortThreeWay(array, right + 1, high);
    }

    private static int partition(int[] array, int left, int right) {
        int k = array[left];
        int i = left, j = right;

        while (i < j) {
            while (array[j] >= k && j > i) j--;
            if (j > i) {
                array[i] = array[j];
                i++;
            }
            while (array[i] < k && j > i) i++;
            if (j > i) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = k;
        return i;
    }

    public static void MergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            MergeSort(array, left, mid);
            MergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (array[i] < array[j]) tmp[k++] = array[i++];
            else tmp[k++] = array[j++];
        }
        while (i <= mid) tmp[k++] = array[i++];
        while (j <= right) tmp[k++] = array[j++];
        for (i = 0; i < tmp.length; i++) array[left + i] = tmp[i];
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
