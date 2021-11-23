import java.util.*;

public class quickSort {
    public static void main(String[] args) {
        int[] test = {4, 6, 7, 8, 6, 6, 9, 9, 11};
//        quickSort(test);
        findLargestK(test, 5, 0, test.length - 1);
        printArray(test);
    }

    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }

    private static void findLargestK(int[] nums, int k, int left, int right) {
        if (left >= right || right < nums.length - k || left >= nums.length - k) {
            return;
        }
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        swap(nums, left, mid);
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (nums[i] <= pivot) {
                i++;
            } else if (nums[j] > pivot) {
                j--;
            } else {
                swap(nums, i, j);
            }
        }
        // swap value back to where it is
        swap(nums, left, j);
        findLargestK(nums, k,  left, j - 1);
        findLargestK(nums, k,j + 1, right);
    }

    private static void quickSort(int[] nums) {
        quick(nums, 0 , nums.length - 1);
    }

    private static void quick(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        swap(nums, left, mid);
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (nums[i] <= pivot) {
                i++;
            } else if (nums[j] > pivot) {
                j--;
            } else {
                swap(nums, i, j);
            }
        }
        // swap value back to where it is
        swap(nums, left, j);
        quick(nums, left, j - 1);
        quick(nums, j + 1, right);

    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
