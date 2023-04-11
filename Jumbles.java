
import java.util.*;
import java.io.*;

public class Jumbles {
    public static void main(String[] args) throws Exception {
        BufferedReader dict = new BufferedReader(new FileReader(args[0]));
        BufferedReader scramble = new BufferedReader(new FileReader(args[1]));
        TreeSet<String> jum = new TreeSet<String>();
        TreeMap<String, TreeSet<String>> lookup = new TreeMap<String, TreeSet<String>>();

        while (dict.ready()) {
            String words[] = dict.readLine().split("\\s+");
            for (int i = 0; i < words.length; i++) {
                String key = toCanonical(words[i]);

                if (lookup.containsKey(key)) {
                    TreeSet<String> cano = lookup.get(key);
                    cano.add(words[i]);
                    lookup.put(key, cano);
                } else {
                    TreeSet<String> cano = new TreeSet<String>();
                    cano.add(words[i]);
                    lookup.put(key, cano);
                }
            }

        }
        dict.close();
        while (scramble.ready()) {
            String words[] = scramble.readLine().split("\\s+");
            for (int i = 0; i < words.length; i++) {
                jum.add(words[i]);
            }
        }
        scramble.close();

        for (String word : jum) {
            System.out.print(word + " ");
            String key = toCanonical(word);
            TreeSet<String> trueWords = lookup.get(key);
            if (trueWords != null && !trueWords.isEmpty()) {
                for (String trueJum : trueWords)
                    System.out.print(trueJum + " ");
            }
            System.out.println();
        }

    }

    public static String toCanonical(String s) // assume s = "zebra"
    {
        char[] letters = s.toCharArray(); // letters -> [z][e][b][r][a]
        Arrays.sort(letters); // now letters -gt; [a][b][e][r][z]
        return new String(letters); // read String API it has a constructor that accepts a char array and does the
                                    // obvious with it
    }

}