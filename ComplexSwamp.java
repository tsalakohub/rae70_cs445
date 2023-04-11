import java.io.*;
import java.util.*;
// DO NOT IMPORT ANYTHING ELSE
// NO PACKAGE STATMENTS 
// NO OVERRIDE STATMENTS 

public class ComplexSwamp {
    static int[][] swamp; // NOW YOU DON'T HAVE PASS THE REF IN/OUT METHOD

    public static void main(String[] args) throws Exception {
        int[] dropInPt = new int[2]; // row and col will be on the 2nd line of input file;
        swamp = loadSwamp(args[0], dropInPt);
        // temp = new int[swamp.length][swamp.length];
        int row = dropInPt[0], col = dropInPt[1];
        String path = ""; // with each step grows to => "[2,3][3,4][3,5][4,6]" etc
        dfs(row, col, path);
    } // END MAIN

    // --YOU-- WRITE THIS METHOD
    // (you may copy from YOURSELF from YOUR lab7 not someone else's)
    // ----------------------------------------------------------------
    private static int[][] loadSwamp(String infileName, int[] dropInPt) throws Exception {
        int[][] theSwamp;
        // open infile with Scanner

        Scanner infile = new Scanner(new File(infileName));

        int dimension = infile.nextInt();
        theSwamp = new int[dimension][dimension];

        // read in the next two numbers with two calls to .nextInt()
        // store into origin[0] and origin[1]
        dropInPt[0] = infile.nextInt();
        dropInPt[1] = infile.nextInt();

        // now use a nested loop outer = row from 0 to dimension-1
        // inner loop = col from 0 to dimension-1
        // store each .nextInt() into theSwamp[row][col]
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                theSwamp[r][c] = infile.nextInt();

            }
            System.out.println();
        }
        infile.close();
        return theSwamp;
        // actually must return a loaded swamp grid SEE YOUr OWN LAB#7
    }

    static void dfs(int row, int col, String path) // dfs = DEPTH FIRST SEARCH
    {
        // IMPLEMENT THE DFS ALGORITHM IN HERE
        path += "[" + row + "," + col + "]";

        if (row == 0 || row == swamp.length - 1 || col == 0 || col == swamp.length - 1) {
            System.out.print(path);
            return;
        }
        // north

        if (swamp[row - 1][col] == 1) {
            swamp[row][col] = -1;
            dfs(row - 1, col, path);
            swamp[row][col] = 1;
        }
        // ne
        if (swamp[row - 1][col + 1] == 1) {
            swamp[row][col] = -1;
            dfs(row - 1, col + 1, path);
            swamp[row][col] = 1;
        }
        // east
        if (swamp[row][col + 1] == 1) {
            swamp[row][col] = -1;
            dfs(row, col + 1, path);
            swamp[row][col] = 1;
        }
        // southeast
        if (swamp[row + 1][col + 1] == 1) {
            swamp[row][col] = -1;
            dfs(row + 1, col + 1, path);
            swamp[row][col] = 1;
        }
        // south
        if (swamp[row + 1][col] == 1) {
            swamp[row][col] = -1;
            dfs(row + 1, col, path);
            swamp[row][col] = 1;
        }
        // southwest
        if (swamp[row + 1][col - 1] == 1) {
            swamp[row][col] = -1;
            dfs(row + 1, col - 1, path);
            swamp[row][col] = 1;
        }
        // west
        if (swamp[row][col - 1] == 1) {
            swamp[row][col] = -1;
            dfs(row, col - 1, path);
            swamp[row][col] = 1;
        }
        // northwest
        if (swamp[row - 1][col - 1] == 1) {
            swamp[row][col] = -1;
            dfs(row - 1, col - 1, path);
            swamp[row][col] = 1;
        }

        System.out.println();
        return;
    }

}
