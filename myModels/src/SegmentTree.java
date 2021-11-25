public class SegmentTree {
    // leetcode 307
    public static void main(String[] args) {
        int[] test = {4, 6, 7, 8, 6, 6, 9, 9, 11};
        SegmentTreeNode root = buildTree(test, 0, test.length - 1);
        System.out.println(stSumRange(root, 0, 4));
        stUpdate(root, test.length - 1, 9);
        System.out.println(stSumRange(root, test.length-2, test.length-1));
    }

    static class SegmentTreeNode {
        int start;
        int end;
        SegmentTreeNode left;
        SegmentTreeNode right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.left = null;
            this.right = null;
        }
    }

    private static SegmentTreeNode buildTree(int[] nums, int start, int end) {
        SegmentTreeNode cur = new SegmentTreeNode(start, end);
        if (start == end) {
            cur.sum = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            cur.left = buildTree(nums, start, mid);
            cur.right = buildTree(nums, mid + 1, end);
            cur.sum = cur.left.sum + cur.right.sum;
        }
        return cur;
    }

    private static void stUpdate(SegmentTreeNode cur, int index, int val) {
        if (cur.start == cur.end) {
            cur.sum = val;
            return;
        }
        int mid = cur.start + (cur.end - cur.start) / 2;
        if (index <= mid) {
            stUpdate(cur.left, index, val);
        } else {
            stUpdate(cur.right, index, val);
        }
        cur.sum = cur.left.sum + cur.right.sum;
    }

    private static int stSumRange(SegmentTreeNode cur, int start, int end) {
        if (cur.start >= start && cur.end <= end) {
            return cur.sum;
        }
        int result = 0;
        int mid = cur.start + (cur.end - cur.start) / 2;
        if (start <= mid) {
            result += stSumRange(cur.left, start, end);
        }
        if (end > mid) {
            result += stSumRange(cur.right, start, end);
        }
        return result;
    }
}
