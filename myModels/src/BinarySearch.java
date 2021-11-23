import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
        int[] test = {1, 2, 4, 7, 9, 11};
        System.out.println(binarySearch(test, 7));
        System.out.println(binarySearch2(test, 7));
        int[] test2 = {1,1,1,1,1,1,1,1,1,1,5,7,8,8,8,8,8,10};
        System.out.println(binarySearchBoundary(test2,8));
    }

    // with left always less than right
    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return -1;
    }

    private static int binarySearch2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearchBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[right] == target) {
            return right;
        }

        if (nums[left] == target) {
            return left;
        }
        return -1;
    }
}