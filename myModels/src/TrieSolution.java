import java.util.*;
import java.util.stream.Collectors;

public class TrieSolution {
    static class TrieNode {
        TrieNode[] children;
        Map<String, Integer> count;

        public TrieNode() {
            children = new TrieNode[27];
            count = new HashMap<>();
        }
    }
    static TrieNode root;
    static StringBuilder prefix;

    private static void addWord(String in, int count) {
        TrieNode cur = root;
        for (char c : in.toCharArray()) {
            int nextIdx = c == ' ' ? 26 : c - 'a';
            if (cur.children[nextIdx] == null) {
                cur.children[nextIdx] = new TrieNode();
            }
            cur = cur.children[nextIdx];
            cur.count.put(in, cur.count.getOrDefault(in, 0) + 1);
        }
    }

    private static List<String> input(char c) {
        if (c == '#') {
            addWord(prefix.toString(), 1);
            prefix.setLength(0);
            return new ArrayList<>();
        }
        prefix.append(c);
        TrieNode cur = root;
        for (char curChar : prefix.toString().toCharArray()) {
            int nextIdx = curChar == ' ' ? 26 : curChar - 'a';
            if (cur.children[nextIdx] == null) {
                return new ArrayList<>();
            }
            cur = cur.children[nextIdx];
        }
        return cur.count.entrySet().stream().sorted((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return o2.getValue() - o1.getValue();
            }
        }).map(o1 -> o1.getKey()).limit(3).collect(Collectors.toList());
    }

    public static void main (String[] args) {
        root = new TrieNode();
        prefix = new StringBuilder();
        String[] words = {"i love you", "island", "ironman", "i love leetcode"};
        int[] counts = {5, 3, 2, 2};
        for (int i = 0; i < words.length; i++) {
            addWord(words[i], counts[i]);
        }
        printList(input('i'));
        printList(input(' '));
    }

    private static void printList(List<String> in) {
        for (String word : in) {
            System.out.print(word + ", ");
        }
        System.out.println();
    }
}
