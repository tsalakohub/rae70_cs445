/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph {
    private final int NO_EDGE = -1; // all real edges are positive
    private int G[][]; // will point to a 2D array to hold our graph data

    private int numEdges;
    private int numNode;

    public Graph(String graphFileName) throws Exception // since readFild doesn't handle them either
    {
        loadGraphFile(graphFileName);

    }

    ///////////////////////////////////// LOAD GRAPH FILE
    ///////////////////////////////////// //////////////////////////////////////////
    //
    // FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
    // THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN
    ///////////////////////////////////// EDGE IN THE GRAPH

    private void loadGraphFile(String graphFileName) throws Exception {
        Scanner graphFile = new Scanner(new File(graphFileName));

        int dimension = graphFile.nextInt(); // THE OF OUR N x N GRAPH
        G = new int[dimension][dimension]; // N x N ARRAY OF ZEROS
        numEdges = 0;
        numNode = dimension;

        // WRITE A LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (!(row == col)) {
                    G[row][col] = NO_EDGE;
                }
            }
        }

        // NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
        // USE row & col AS WHERE TO STORE THE weights
        // i.e. g[row][col] = w;

        while (graphFile.hasNextInt()) {
            // read in the row,col,weight // that eat us this line
            // call add edge
            int row = graphFile.nextInt();
            int col = graphFile.nextInt();
            int weight = graphFile.nextInt();

            addEdge(row, col, weight);

        }

    } // END readGraphFile

    private void addEdge(int r, int c, int w) {
        G[r][c] = w;
        ++numEdges; // only this method adds edges so we do increment counter here only
    }

    private boolean hasEdge(int fromNode, int toNode) {
        return false;
    }

    // IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
    // NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
    private int inDegree(int node) {
        int total = 0;
        for (int row = 0; row < numNode; row++) {
            if (G[row][node] > 0) {
                total += 1;
            }
        }
        return total;
    }

    // OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
    // NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
    private int outDegree(int node) {
        int total = 0;
        for (int col = 0; col < numNode; col++) {
            if ((G[node][col]) > 0) {
                total += 1;
                // System.out.println("Node Value" + G[node][col]);
                // System.out.println(node);
                // System.out.println("Col val" + col);

            }
            // System.out.println("Col val" + col);
        }
        return total;
        // row
    }

    // DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY
    private int degree(int node) {
        return (inDegree(node) + outDegree(node));
        // sum
    }

    // PUBLIC METHODS

    public int maxOutDegree() {
        int total = outDegree(0);

        for (int i = 1; i < numNode - 1; i++) {
            if (outDegree(i) > total) {
                total = outDegree(i);
            }

        }
        return total;
    }

    public int maxInDegree() {
        int total = inDegree(0);
        for (int i = 1; i < numNode - 1; i++) {
            // System.out.println(inDegree(i));
            if (inDegree(i) > total) {
                total = inDegree(i);
            }

        }
        return total;
    }

    public int minOutDegree() {
        int total = outDegree(0);
        for (int i = 1; i < numNode - 1; i++) {
            if (outDegree(i) < total) {
                total = outDegree(i);
            }

        }
        return total;
    }

    public int minInDegree() {
        int total = inDegree(0);
        for (int i = 1; i < numNode - 1; i++) {
            if (inDegree(i) < total) {
                total = inDegree(i);
            }

        }
        return total;
    }

    public int maxDegree() {
        int total = degree(0);
        for (int i = 1; i < numNode - 1; i++) {
            if (degree(i) > total) {
                total = degree(i);
            }

        }
        return total;
    }

    public int minDegree() {
        int total = degree(0);
        for (int i = 1; i < numNode - 1; i++) {
            if (degree(i) < total) {
                total = degree(i);
            }

        }
        return total;
    }

    public void removeEdge(int fromNode, int toNode) throws Exception {
        /*
         * if caller passes in a row col pair that
         * out of bound or has no edge there, you must
         * throw and catch an exception. See my output.
         * 
         * if the edge is there then remove it by writing
         * in a NO_EDGE value at that coordinate in the grid
         */

        try {
            if (fromNode < 0 || toNode < 0 || (fromNode > numNode - 1) || (toNode > numNode - 1)
                    || G[fromNode][toNode] == -1) {
                throw new Exception("valid input");

            }
            G[fromNode][toNode] = -1;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.printf("java.lang.Exception: Non Existent Edge Exception: removeEdge(%d, %d)", fromNode,
                    toNode);
        }

        // G[fromNode][toNode] = -1;

    }

    // TOSTRING
    public String toString() {
        String the2String = "";
        for (int r = 0; r < G.length; ++r) {
            for (int c = 0; c < G[r].length; ++c)
                the2String += String.format("%3s", G[r][c] + " ");
            the2String += "\n";
        }
        return the2String;
    } // END TOSTRING

} // EOF
