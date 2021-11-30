import java.util.*;
import java.util.stream.IntStream;

public class Topo {
    // LC 210 course schedule

//    // BFS
    static class Node {
        int val;
        List<Node> children;
        int inDegree;

        public Node(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }
//    public static int[] findOrder(int numCourses, int[][] prerequisites) {
//        int[] results = new int[numCourses];
//        Node[] nodes = new Node[numCourses];
//
//        for (int i = 0; i < numCourses; i++) {
//            nodes[i] = new Node(i);
//        }
//
//        for (int[] prerequisite : prerequisites) {
//            nodes[prerequisite[1]].children.add(nodes[prerequisite[0]]);
//            nodes[prerequisite[0]].inDegree++;
//        }
//
//        Queue<Node> queue = new LinkedList<>();
//        Arrays.stream(nodes).filter(o -> o.inDegree == 0).forEach(o -> queue.offer(o));
//        int count = 0;
//        while (!queue.isEmpty()) {
//            Node cur = queue.poll();
//            results[count++] = cur.val;
//            for (Node child : cur.children) {
//                if (--child.inDegree == 0) {
//                    queue.offer(child);
//                }
//            }
//        }
//        return count == numCourses ? results : new int[0];
//    }

//    // DFS
//    enum Status {
//        VISITED, VISITING, INITIAL;
//    }
//
//    static int index;
//
//    static class Node {
//        int val;
//        List<Integer> nexts;
//        Status status;
//
//        public Node(int val) {
//            this.val = val;
//            nexts = new ArrayList<>();
//            status = Status.INITIAL;
//        }
//    }
//
//    private static boolean topoSort(int[] res, Node source, Node[] nodes) {
//        if (source.status == Status.VISITING) {
//            return false;
//        }
//        if (source.status == Status.VISITED) {
//            return true;
//        }
//        source.status = Status.VISITING;
//        for (Integer next: source.nexts) {
//            if (!topoSort(res, nodes[next], nodes)) {
//                return false;
//            }
//        }
//        source.status = Status.VISITED;
//        res[index--] = source.val;
//        return true;
//    }
//
//    public static int[] findOrder(int numCourses, int[][] prerequisites) {
//        Node[] nodes = new Node[numCourses];
//        IntStream.range(0, numCourses).forEach(i -> nodes[i] = new Node(i));
//        Arrays.stream(prerequisites).
//                forEach(prerequisite -> nodes[prerequisite[1]].nexts.add(prerequisite[0]));
//        int[] res = new int[numCourses];
//        index = numCourses - 1;
//        for (int i = 0; i < numCourses; i++) {
//            if (nodes[i].status==Status.INITIAL && !topoSort(res, nodes[i], nodes)) {
//                return new int[0];
//            }
//        }
//        return res;
//    }


    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
//        printCourses(findOrder(numCourses, prerequisites));
        int[][] edges = {{0,1}, {1,2}, {2,3}, {2,4}, {4,5}, {5,3}};
        System.out.println(findLongestPath(6, edges));
    }
    private static void printCourses(int[] results) {
        for(int result : results) {
            System.out.print(result + ", ");
        }
    }

    public static int findLongestPath(int numNodes, int[][] edges) {
        Node[] nodes = new Node[numNodes];
        IntStream.range(0, numNodes).forEach(i -> nodes[i] = new Node(i));
        Arrays.stream(edges).forEach(edge -> {
            nodes[edge[0]].children.add(nodes[edge[1]]);
            nodes[edge[1]].inDegree++;
        });
        Queue<Node> queue = new LinkedList<>();
        Arrays.stream(nodes).filter(n -> n.inDegree == 0).forEach(n -> queue.add(n));
        int longestPath = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                for (Node child : cur.children) {
                    if (--child.inDegree == 0) {
                        queue.offer(child);
                    }
                }
            }
            longestPath++;
        }
        return longestPath - 1;
    }
}
