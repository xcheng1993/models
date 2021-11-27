import java.util.*;
public class LRU {
    LinkedNodeList list;
    Map<Integer, Node> map;
    int capacity;

    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }

    public LRU(int capacity) {
        list = new LinkedNodeList();
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node cur = map.get(key);
        if (cur == null) {
            return -1;
        }
        list.moveToHead(cur);
        return cur.val;
    }

    public void put(int key, int val) {
        Node node = map.get(key);
        if (node != null) {
            node.val = val;
            list.moveToHead(node);
        } else {
            node = new Node(key, val);
            map.put(key, node);
            list.addToHead(node);
            if (map.size() > capacity) {
                Node remove = list.getTail();
                list.removeTail();
                map.remove(remove.key);
            }
        }
    }

    static class Node {
        Node prev;
        Node next;
        int key;
        int val;
        public Node (int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class LinkedNodeList {
        Node dummyHead;
        Node dummyTail;

        public LinkedNodeList() {
            dummyHead = new Node(0, 0);
            dummyTail = new Node(0, 0);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public void moveToHead(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            addToHead(node);
        }

        public void addToHead(Node node) {
            node.next = dummyHead.next;
            node.prev = dummyHead;
            dummyHead.next.prev = node;
            dummyHead.next = node;
        }

        public void removeTail() {
            dummyTail.prev.prev.next = dummyTail;
            dummyTail.prev = dummyTail.prev.prev;
        }

        public Node getTail() {
            return dummyTail.prev;
        }
    }


}
