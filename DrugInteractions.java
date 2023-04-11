import java.util.*;
import java.io.*;

public class DrugInteractions {
    public static void main(String[] args) throws Exception {
        BufferedReader foodDrug2CategoryFile = new BufferedReader(new FileReader("foodDrug2Category.txt"));
        BufferedReader patient2FoodDrugFile = new BufferedReader(new FileReader("patient2FoodDrug.txt"));
        BufferedReader dontMixFile = new BufferedReader(new FileReader("dontMix.txt"));

        TreeMap<String, TreeSet<String>> drug2Category = new TreeMap<String, TreeSet<String>>();
        TreeMap<String, String> invDrug2Cat = new TreeMap<String, String>();

        while (foodDrug2CategoryFile.ready()) {
            String tokens[] = foodDrug2CategoryFile.readLine().split(",");
            String cat = tokens[0];
            TreeSet<String> drugs = new TreeSet<String>();
            for (int i = 1; i < tokens.length; ++i) {
                drugs.add(tokens[i]);
                invDrug2Cat.put(tokens[i], cat);
            }
            drug2Category.put(cat, drugs);
        }
        foodDrug2CategoryFile.close();

        // print the name out
        for (String cat : drug2Category.keySet()) {
            System.out.print(cat + " ");
            for (String drug : drug2Category.get(cat))
                System.out.print(drug + " ");
            System.out.println("");

        }

        // name to drug
        TreeMap<String, TreeSet<String>> patient2Drug = new TreeMap<String, TreeSet<String>>();

        while (patient2FoodDrugFile.ready()) {
            String tokens[] = patient2FoodDrugFile.readLine().split(",");
            String pat = tokens[0];
            TreeSet<String> drugs = new TreeSet<String>();
            for (int i = 1; i < tokens.length; ++i) {
                drugs.add(tokens[i]);
            }
            patient2Drug.put(pat, drugs);
        }
        patient2FoodDrugFile.close();
        System.out.println();
        // print the name out
        for (String pat : patient2Drug.keySet()) {
            System.out.print(pat + " ");
            for (String drug : patient2Drug.get(pat))
                System.out.print(drug + " ");
            System.out.println("");
        }

        // beginning of the dont mix

        ArrayList<ArrayList<String>> dontMix = new ArrayList<ArrayList<String>>();
        while (dontMixFile.ready()) {
            ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(dontMixFile.readLine().split(",")));
            dontMix.add(tokens);
        }
        dontMixFile.close();

        System.out.println();
        for (String pat : patient2Drug.keySet()) {
            ArrayList<String> categoriesOfPat = new ArrayList<String>();
            for (String drug : patient2Drug.get(pat)) {
                categoriesOfPat.add(invDrug2Cat.get(drug));
            }

            // loop that goes through dontMix and check if they are in list of categories

            for (int x = 0; x < dontMix.size(); x++) {
                for (int i = 0; i < dontMix.get(x).size(); i++) {
                    if (!categoriesOfPat.contains(dontMix.get(x).get(i)))
                        continue;
                    for (int j = i + 1; j < dontMix.get(x).size(); j++) {

                        if (categoriesOfPat.contains(dontMix.get(x).get(j))) {
                            System.out.println(pat);
                        }
                    }
                }
            }
        }
        System.out.println();

    }

} // END MAIN

// traverse p to d
// get key and val
// for patients
// new set patient's -- category
// go through all drugs
// put in corresponding cat
// if cat in d mix
// check again
// **dm[cat set, cat set] */

// END CLASS
