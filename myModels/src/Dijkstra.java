import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    // leetcode 1631
    static class Node {
        int[] coodirate;
        int effort;
        public Node(int[] coodirate, int effort) {
            this.coodirate = coodirate;
            this.effort = effort;
        }
    }
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int minimumEffortPath(int[][] heights) {
        int[][] efforts = new int[heights.length][heights[0].length];
        Arrays.stream(efforts).forEach(o -> Arrays.fill(o, -1));
        efforts[0][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.effort - o2.effort);
        pq.offer(new Node(new int[]{0,0}, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.coodirate[0] + dir[0];
                int nextCol = cur.coodirate[1] + dir[1];
                if (nextRow >= 0 && nextRow < heights.length && nextCol >=0 && nextCol < heights[0].length) {
                    int newEffort = Math.max(cur.effort, Math.abs(
                            heights[cur.coodirate[0]][cur.coodirate[1]] - heights[nextRow][nextCol]));
                    if (efforts[nextRow][nextCol] == -1 || efforts[nextRow][nextCol] > newEffort) {
                        efforts[nextRow][nextCol] = newEffort;
                        pq.add(new Node(new int[]{nextRow, nextCol}, newEffort));
                    }
                }
            }
        }
        return efforts[heights.length - 1][heights[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] test = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println(minimumEffortPath(test));
        int[][] test2 = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        System.out.println(minimumEffortPath(test2));
    }
}
