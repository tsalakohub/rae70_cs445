
import java.io.*;
import java.util.*;
import java.text.*;

public class MyHashCode {
    private int numBuckets;
    // private Bucket[] buckets; // YOU MUST DESIGN THIS CLASS FOR PROJECT
    private int[] bucketSizes;
    private int idealBucketSize;
    private int size; // # keys stored in set

    public MyHashCode(int numBuckets, int idealBucketSize) {
        size = 0;
        this.numBuckets = numBuckets;
        this.idealBucketSize = idealBucketSize;
        bucketSizes = new int[numBuckets]; // OF KEYS IN EACH [i] BUCKET
    }

    // KEYS ARE -NOT- REALLY BEING ADDED IN THIS LAB. JUST HASHED, AND BUCKET
    // COUNTER INCREMENTED
    public boolean add(String key) {
        int h = hashOf(key, numBuckets); // h MUST BE IN [0..numBuckets-1]
        ++bucketSizes[h];
        ++size;
        return true;
    }

    public double printStats() {
        System.out.format("#OfBuckets:(%s) x idealBucketSize:(%d)  =  #OfKeysHashed:(%s)\n",
                NumberFormat.getNumberInstance(Locale.US).format(bucketSizes.length), idealBucketSize,
                NumberFormat.getNumberInstance(Locale.US).format(size()));

        if (bucketSizes.length < 100)
            System.out.println("Bucket  Size   +/- ");
        if (bucketSizes.length < 100)
            System.out.println("-------------------");
        int minBucketSize = bucketSizes[0], maxBucketSize = bucketSizes[0];

        for (int i = 0; i < numBuckets; ++i) {
            if (bucketSizes.length < 100)
                System.out.format("%5d %5d  %5d\n", i, bucketSizes[i], bucketSizes[i] - idealBucketSize);
            if (bucketSizes[i] > maxBucketSize)
                maxBucketSize = bucketSizes[i];
            else if (bucketSizes[i] < minBucketSize)
                minBucketSize = bucketSizes[i];
        }
        if (bucketSizes.length < 100)
            System.out.println("-------------------");
        System.out.format("actual_minBucketSize %d  actual_maxBucketSize %d\n", minBucketSize, maxBucketSize);
        return variance(bucketSizes);
    }

    // Function for calculating variance
    double variance(int a[]) {
        int sum = 0;
        for (int i = 0; i < a.length; i++)
            sum += a[i];
        double mean = (double) sum / (double) a.length;

        double sqDiff = 0.0;
        for (int i = 0; i < a.length; i++)
            sqDiff += (a[i] - mean) * (a[i] - mean);
        return sqDiff / a.length;
    }

    double stdDev(int a[]) {
        return Math.sqrt(variance(a));
    }

    public int size() {
        return size;
    }

    // RETURNS INT IN RANGE [0..numBuckets-1]
    private int hashOf(String key, int numBuckets) // h MUST BE IN [0..numBuckets-1]
    {
        int total = 0;
        int power = 1;
        int pow = key.length() - 1;
        for (int i = 0; i < key.length(); i++) {
            // total *= 10;
            // int n = key.charAt(i) - '\0';
            int n = key.charAt(i) - 96;
            // n += 5;
            // n /= 5;
            // n -= 47;

            // int weighted = n * (int) Math.pow(26, key.length() - (1 + i));
            // total = total * 11
            // total += n * (29 ^ pow);
            // pow--;
            total += n * power;
            power *= 29;
            power %= numBuckets;
            total %= numBuckets;
            // total = total + weighted;
            // total += 97;
            // total -= 31;
            // total %= numBuckets;
            // total -= 23;
            // salt total

        }
        return Math.abs(total) % numBuckets;
    }

} // END CLASS
