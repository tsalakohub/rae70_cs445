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

    public int hashOf(String key) // h MUST BE IN [0..numBuckets-1]
    {
        int total = 0;
        int power = 1;
        for (int i = 0; i < key.length(); i++) {

            int n = key.charAt(i) - 96;

            total += n * power;
            power *= 29;
            power %= numBuckets;
            total %= numBuckets;

        }
        return Math.abs(total) % numBuckets;
    }

    public Node search(String key) {

        Node cur = bucketArray[hashOf(key)];
        while (cur != null && !key.equals(cur.data)) {
            cur = cur.next;
        }
        return cur; // YOUR CODE HERE
    }

    public boolean removeAtFront(String key) // RETURNS TRUE IF THERE WAS NODE TO REMOVE
    {
        if (bucketArray[hashOf(key)] == null) {
            return false;
        }
        bucketArray[hashOf(key)] = bucketArray[hashOf(key)].next;
        size--;
        return true;

        // YOUR CODE HERE
    }

    public boolean add(String key) {
        // your code here to add the key to the table and ++ your size variable
        int index = hashOf(key);
        Node cur = bucketArray[index];
        if (cur == null) {
            bucketArray[index] = new Node(key, null);
            size++;
            return true;
        }
        while (cur.next != null) {
            if (cur.data.equals(key)) {
                return false;

            }
            cur = cur.next;

        }
        cur.next = new Node(key, null);

        ++size; // you just added a key to one of the lists
        if (size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
            upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
        return true;
    }

    public boolean contains(String key) {
        return search(key) != null;
        // just to make it compile.
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
         * 
         * HASH THAT KEY INTO THE BIGGER TABLE
         * BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
         * 
         */

        for (int i = 0; i < bucketArray.length; i++) {
            Node cur = bucketArray[i];
            while (cur.next != null) {
                int index = hashOf(cur.data);
                Node temp = biggerArray[index];
                if (temp == null) {
                    biggerArray[index] = new Node(cur.data, null);
                } else {
                    while (temp.next != null) {
                        if (temp.data.equals(cur.data)) {
                            break;
                        }
                        temp = temp.next;
                    }
                    if (temp.next == null) {
                        temp.next = new Node(cur.data, null);
                    }
                }
                cur = cur.next;
            }

        }
        bucketArray = biggerArray;
    } // END OF UPSIZE & REHASH

    public boolean remove(String key) {
        if (bucketArray[hashOf(key)] == null || bucketArray[hashOf(key)].data.equals(key)) {
            return removeAtFront(key);
        }

        Node cur = bucketArray[hashOf(key)];
        while (cur.next != null && !cur.next.data.equals(key)) {
            cur = cur.next;
        }
        if (cur.next == null) {
            return false;
        } else {
            cur.next = cur.next.next;
            size--;
            return true;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        for (int i = 0; i < bucketArray.length; i++) {
            bucketArray[i] = null;
        }
    }

    public int size() {
        return size;
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
