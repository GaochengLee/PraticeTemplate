package algo.Search;

/**
 * @author 李高丞
 * @version 1.0 Beta
 */
public class Search {
    public static int BinarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            if (target < array[mid]) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else if (target > array[mid]) {
                left = mid + 1;
                mid = (left + right) / 2;
            } else return mid;
        }
        return -1;
    }


}
