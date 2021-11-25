public class UnionFindSolution {
    // leetcode 684
    static class UnionFind {
        int[] rank;
        int[] parent;

        public UnionFind(int size) {
            rank = new int[size];
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return false;
            } else if (px > py) {
                parent[py] = px;
            } else if (px < py) {
                parent[px] = py;
            } else {
                rank[px]++;
                parent[py] = px;
            }
            return true;
        }
    }

    public static void main (String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
        printEdge(findRedundantConnection(edges));
    }

    private static void printEdge(int[] edge) {
        System.out.print(edge[0] + ", " + edge[1]);
    }

    public static int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0] - 1, edge[1] - 1)) {
                return edge;
            }
        }
        return null;
    }

}
