public class BinaryIndexTree {
//    static int[][] tree;
//    // for tracking old value to calculate delta
//    static int[][] nums;
//    static int m;
//    static int n;
//
//    public static void buildTree(int[][] matrix) {
//        m = matrix.length;
//        n = matrix[0].length;
//        tree = new int[m+1][n+1];
//        nums = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                update(i, j, matrix[i][j]);
//            }
//        }
//    }
//
//    private static void update(int r, int c, int val) {
//        int delta = val - nums[r][c];
//        nums[r][c] = val;
//        for (int i = r + 1; i <= m; i += i & -i) {
//            for (int j = c + 1; j <= n; j += j & -j) {
//                tree[i][j] += delta;
//            }
//        }
//    }
//
//    public static int sumRegion(int row1, int col1, int row2, int col2) {
//        if (m == 0 || n == 0) {
//            return 0;
//        }
//        return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
//    }
//
//    private static int sum(int row, int col) {
//        int sum = 0;
//        for (int i = row; i > 0; i -= i & -i) {
//            for (int j = col; j > 0; j -= j & -j) {
//                sum += tree[i][j];
//            }
//        }
//        return sum;
//    }

    // 1-D binary index tree
    class NumArray {
        int[] tree;
        int[] myNums;

        public void update(int index, int val) {
            int delta = val - myNums[index];
            myNums[index] = val;
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        public NumArray(int[] nums) {
            tree = new int[nums.length + 1];
            myNums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                update(i, nums[i]);
            }
        }

        public int sumRange(int left, int right) {
            // printTree();
            return sum(right) - sum(left - 1);
        }

        private void printTree() {
            for (int i : tree) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

        private int sum(int index) {
            int result = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                result += tree[i];
            }
            return result;
        }
    }


}
