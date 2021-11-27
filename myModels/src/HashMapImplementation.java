// Java program to implement Rehashing

import java.util.ArrayList;

class myMap<K, V> {
    class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> next;
        public MapNode(K key, V value)
        {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    ArrayList<MapNode<K, V> > buckets;
    int size;
    int numBuckets;
    final double DEFAULT_LOAD_FACTOR = 0.75;

    public myMap()
    {
        numBuckets = 5;
        buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }
    }

    private int getBucketInd(K key) {
        int hashCode = key.hashCode();
        return (hashCode % numBuckets);
    }

    public void insert(K key, V value)
    {
        // find corresponding bucket for this node if exist
        int bucketInd = getBucketInd(key);
        MapNode<K, V> head = buckets.get(bucketInd);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        // add node to head (front) when the node does not exist
        MapNode<K, V> newElementNode = new MapNode<K, V>(key, value);
        head = buckets.get(bucketInd);
        newElementNode.next = head;
        buckets.set(bucketInd, newElementNode);
        // update size and loadFactor
        size++;
        double loadFactor = (1.0 * size) / numBuckets;
        if (loadFactor > DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    private void rehash()
    {
        ArrayList<MapNode<K, V> > temp = buckets;
        numBuckets *= 2;
        buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }
        size = 0;
        for (int i = 0; i < temp.size(); i++) {
            MapNode<K, V> head = temp.get(i);
            while (head != null) {
                K key = head.key;
                V val = head.value;
                insert(key, val);
                head = head.next;
            }
        }
    }

    public void printMap()
    {

        // The present bucket list is made temp
        ArrayList<MapNode<K, V> > temp = buckets;

        System.out.println("Current HashMap:");
        // loop through all the nodes and print them
        for (int i = 0; i < temp.size(); i++) {

            // head of the chain at that index
            MapNode<K, V> head = temp.get(i);

            while (head != null) {
                System.out.println("key = " + head.key + ", val = " + head.value);

                head = head.next;
            }
        }
        System.out.println();
    }
}

public class HashMapImplementation {

    public static void main(String[] args)
    {

        // Creating the Map
        myMap<Integer, String> map = new myMap<Integer, String>();

        // Inserting elements
        map.insert(1, "Geeks");
        map.printMap();

        map.insert(2, "forGeeks");
        map.printMap();

        map.insert(3, "A");
        map.printMap();

        map.insert(4, "Computer");
        map.printMap();

        map.insert(5, "Portal");
        map.printMap();
    }
}
