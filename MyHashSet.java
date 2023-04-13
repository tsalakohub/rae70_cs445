import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface {
    private int numBuckets; // changes over life of the hashset due to resizing the array
    private Node[] bucketArray;
    private int size; // total # keys stored in set right now

    // THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE
    // MOVING AWAY FROM (1)
    private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20; // **DO NOT CHANGE THIS NUMBER**

    public MyHashSet(int numBuckets) {
        size = 0;
        this.numBuckets = numBuckets;
        bucketArray = new Node[numBuckets]; // array of linked lists
        System.out.format(
                "IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n",
                numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE);
    }

    public boolean add(String key) {
        // your code here to add the key to the table and ++ your size variable

        ++size; // you just added a key to one of the lists
        if (size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
            upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
        return true;
    }

    public boolean contains(String key) {
        return false; // just to make it compile.
        // You hash this key. goto that list. look for this key in that list
    }

    private void upSize_ReHash_AllKeys() {
        System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
                size, bucketArray.length, bucketArray.length * 2);
        Node[] biggerArray = new Node[bucketArray.length * 2];
        this.numBuckets = biggerArray.length;

        /*
         * FOR EACH LIST IN THE ARRAY
         * FOR EACH NODE IN THAT LIST
         * HASH THAT KEY INTO THE BIGGER TABLE
         * BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
         */
        bucketArray = biggerArray;
    } // END OF UPSIZE & REHASH

    public boolean remove(String key) {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public void clear() {
    }

    public int size() {
        return 0;
    }

} // END MyHashSet CLASS

class Node {
    String data;
    Node next;

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
}
