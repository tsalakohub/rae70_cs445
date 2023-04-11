import java.util.*;
import java.io.*;

public class Potus {
    public static void main(String[] args) throws Exception {
        // BufferedReader state2PresidentsFile = new BufferedReader( new
        // FileReader("state2Presidents.txt") );
        // TreeMap<String,TreeSet<String>> state2Presidents= new
        // TreeMap<String,TreeSet<String>>();

        // BufferedReader allPresidentsFile = new BufferedReader( new
        // FileReader("allPresidents.txt") );
        // TreeSet<String> allPresidents = new TreeSet<String>();

        // BufferedReader allStatesFile = new BufferedReader( new
        // FileReader("allStates.txt") );
        // TreeSet<String> allStates = new TreeSet<String>();

        System.out.println("THESE STATES HAD THESE POTUS BORN IN THEM:\n");
        state2Presidents();

        System.out.println("\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");
        invstate2Presidents();
        System.out.println("\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");
        noState();
        System.out.println("\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");
        allStates();
    } // END MAIN

    // - - - - - - - - - - - H E L P E R M E T H O D S - - - - - - - -
    // state to pres
    public static void state2Presidents() throws Exception {
        TreeMap<String, TreeSet<String>> state2Presidents = new TreeMap<String, TreeSet<String>>();
        BufferedReader state2PresidentsFile = new BufferedReader(new FileReader("state2presidents.txt"));
        while (state2PresidentsFile.ready()) {
            String tokens[] = state2PresidentsFile.readLine().split("\\s+");
            String state = tokens[0];
            TreeSet<String> presidents = new TreeSet<String>();
            for (int i = 1; i < tokens.length; ++i) {
                presidents.add(tokens[i]);
            }
            state2Presidents.put(state, presidents);
        }
        state2PresidentsFile.close();
        for (String state : state2Presidents.keySet()) {
            System.out.print(state + " ");
            for (String president : state2Presidents.get(state)) // prdouces a treeset //returns the presidents names in
                                                                 // sorted order
                System.out.print(president + " ");
            System.out.println("");
        }
    }

    // pres to state
    public static void invstate2Presidents() throws Exception {
        TreeMap<String, String> inv = new TreeMap<String, String>();
        BufferedReader invstate2PresidentsFile = new BufferedReader(new FileReader("state2presidents.txt"));
        while (invstate2PresidentsFile.ready()) {
            String tokens[] = invstate2PresidentsFile.readLine().split("\\s+");
            String state = tokens[0];
            for (int i = 1; i < tokens.length; ++i) {
                inv.put(tokens[i], state);
            }
        }
        invstate2PresidentsFile.close();

        for (String invPres : inv.keySet()) {
            System.out.print(invPres + " ");
            System.out.print(inv.get(invPres) + " ");
            System.out.println("");
        }

    }

    // pres no state
    public static void noState() throws Exception {
        TreeMap<String, String> inv = new TreeMap<String, String>();
        TreeSet<String> noState = new TreeSet<String>();
        BufferedReader state2PresidentsFile = new BufferedReader(new FileReader("state2presidents.txt"));
        BufferedReader allPresidentsFile = new BufferedReader(new FileReader("allPresidents.txt"));
        while (state2PresidentsFile.ready()) {
            String tokens[] = state2PresidentsFile.readLine().split("\\s+");
            String state = tokens[0];
            for (int i = 1; i < tokens.length; ++i) {
                inv.put(tokens[i], state);
            }
        }
        state2PresidentsFile.close();

        // Start Presidents While
        while (allPresidentsFile.ready()) {
            String pres = allPresidentsFile.readLine();
            if (!inv.containsKey(pres)) {
                noState.add(pres);
            }
        }
        allPresidentsFile.close();
        for (String no : noState)
            System.out.println(no);
    }

    // state no pres
    public static void allStates() throws Exception {
        TreeMap<String, TreeSet<String>> state2Presidents = new TreeMap<String, TreeSet<String>>();
        TreeSet<String> allStates = new TreeSet<String>();
        BufferedReader state2PresidentsFile = new BufferedReader(new FileReader("state2presidents.txt"));
        BufferedReader allStatesFile = new BufferedReader(new FileReader("allStates.txt"));
        while (state2PresidentsFile.ready()) {
            String tokens[] = state2PresidentsFile.readLine().split("\\s+");
            String state = tokens[0];
            TreeSet<String> presidents = new TreeSet<String>();
            for (int i = 1; i < tokens.length; ++i) {
                presidents.add(tokens[i]);
            }
            state2Presidents.put(state, presidents);
        }
        state2PresidentsFile.close();

        while (allStatesFile.ready()) {
            String state = allStatesFile.readLine();
            if (!state2Presidents.containsKey(state)) {
                allStates.add(state);
            }
        }
        allStatesFile.close();
        for (String state : allStates)
            System.out.println(state);

    }
} // END CLASS