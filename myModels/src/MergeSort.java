import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] test = {1231, 123, 5, 11, 7, 9, 1, 11, 5, 6, 7};
        mergeSort(test);
        printArray(test);
    }

    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }

    private static void mergeSort(int[] nums) {
        merge(nums, 0, nums.length - 1);
    }

    private static void merge(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        merge(nums, left, mid);
        merge(nums, mid + 1, right);
        int i = left;
        int j = mid + 1;
        int p = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            tmp[p++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            tmp[p++] = nums[i++];
        }

        while (j <= right) {
            tmp[p++] = nums[j++];
        }

        for (int k = 0; k < tmp.length; k++) {
            nums[k + left] = tmp[k];
        }
    }
}
