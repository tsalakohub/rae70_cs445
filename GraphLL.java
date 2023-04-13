import java.io.*;
import java.util.*;

public class GraphLL {
    private final int NO_EDGE = -1; // all real edges are positive
    private Edge[] G; // every G[i] is the head of a linked list, i.e ref to an Edge

    private int numEdges;
    private int numNode;

    public GraphLL(String graphFileName) throws Exception // since readFild doesn't handle them either
    {
        loadGraphFile(graphFileName);
    }

    ///////////////////////////////////// LOAD GRAPH FILE
    ///////////////////////////////////// //////////////////////////////////////////

    private void loadGraphFile(String graphFileName) throws Exception {
        Scanner graphFile = new Scanner(new File(graphFileName));
        int numNodes = graphFile.nextInt();
        G = new Edge[numNodes];
        numEdges = 0;
        numNode = numNodes;

        // NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
        // DO an insertAtFront using g[SRC] as the head

        while (graphFile.hasNextInt()) {

            int src = graphFile.nextInt();
            int dest = graphFile.nextInt();
            int weight = graphFile.nextInt();

            addEdge(src, dest, weight);

            // read in the src, dest, weight
            // call addEdge
        }

    } // END readGraphFile

    // GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
    private void addEdge(int src, int dest, int weight) {

        Edge temp = new Edge(dest, weight, G[src]);
        G[src] = temp;
    }

    private boolean hasEdge(int src, int dest) {
        // return false; // CHANGE/REMOVE/MOVE AS NEEDED
        // GOTO G[src] AND WALK THAT LINKED LIST LOOKING FOR A NODE (EDGE) WITH DEST
        Edge cur = G[src];
        while (cur != null) {
            if (cur.dest == dest) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    private int inDegree(int dest) // # of roads(edges) entering this city (node)
    { // THE HARDER ONE
        int inDeg = 0;
        // WALK ALL THE LISTS COUNTING THE NODE HAVING THIS DEST
        for (int i = 0; i < numNode; i++) {
            if (hasEdge(i, dest)) {
                inDeg++;
                // System.out.println(inDeg);
            }
        }
        // System.out.println(numNode);
        return inDeg;
    }

    private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
    { // THE EASIER ONE
        int outDeg = 0;
        // JUST RETURN THE LENGTH OF THIS LIST AT G[src]
        Edge cur = G[src];
        while (cur != null) {
            cur = cur.next;
            outDeg++;

        }
        // System.out.println(outDeg);
        return outDeg;
    }

    private int degree(int node) // indegree + outdegree of this node #
    {
        return (inDegree(node) + outDegree(node));
    }

    // PUBLIC METHODS. THIS CODE COPIED FROM THE GRAPH2D LAB AND USED AS IS. SINCE
    // IT IS NOT
    // DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3
    // DEGREE FUNCTIONS

    public int maxOutDegree() {
        // int maxOutDegree = outDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
        // LOOP OVER ALL NODES CALLING THE OUTDEG OF THAT NODE- RMEMBER THE LARGEST
        // OUTDEG
        // return maxOutDegree;

        int total = outDegree(0);

        for (int i = 1; i < numNode; i++) {
            if (outDegree(i) > total) {
                total = outDegree(i);
            }

        }
        return total;
    }

    public int maxInDegree() {
        int total = inDegree(0);
        for (int i = 1; i < numNode; i++) {
            // System.out.println(inDegree(i));
            if (inDegree(i) > total) {
                total = inDegree(i);
            }

        }
        return total;
    }

    public int minOutDegree() {
        int total = outDegree(0);
        for (int i = 1; i < numNode; i++) {
            if (outDegree(i) < total) {
                total = outDegree(i);
            }

        }
        return total;
    }

    public int minInDegree() {
        int total = inDegree(0);
        for (int i = 1; i < numNode; i++) {
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
            // System.out.println(numNode);
            if (degree(i) < total) {
                total = degree(i);
            }

        }
        return total;
    }

    public void removeEdge(int src, int dest) throws Exception {
        // ITS AN OLD FASHIONED FIND & REMOVE NODE ON A 1 WAY LINKED LIST
        // IF THE LIST AT G[src] IS NULL -OR- SRC OR DEST OUT OF BOUNDS
        // THROW AND CATCH AN EXCEPTION - SEE OUTPUT

        try {
            if (G[src] == null || src < 0 || dest < 0 || src > numNode - 1 || dest > numNode - 1) {
                throw new Exception("valid input");

            }

            if (G[src].next == null) {
                G[src] = null;
            } else {
                Edge cur = G[src];

                while (cur.next.next != null) {
                    cur = cur.next;
                }
                cur.next = null;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.printf("java.lang.Exception: Non Existent Edge Exception: removeEdge(%d, %d)", src,
                    dest);
        }

        // USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE
        // WALK A CURR UP TO THE PRED OF THE DEADNODE
        // REMOVE THE NODE (IF ITS THERE)

        // ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)

    } // E N D R E M O V E D G E

    // TOSTRING
    public String toString() {
        String the2String = "";

        // SEE OUTPUT
        for (int i = 0; i < numNode; i++) {
            the2String += i + ":" + " ";

            Edge cur = G[i];
            if (cur == null) {
                the2String += " ";
                the2String += "\n";
            } else {
                while (cur != null) {
                    the2String += "->" + " " + "[" + cur.dest + "," + cur.weight + "]" + " ";

                    // System.out.println(cur.dest + " " + cur.weight);
                    cur = cur.next;
                }
                the2String += "\n";

            }

        }

        return the2String;
    } // END TOSTRING
} // E N D G R A P H L L C L A S S

// - - - - Y O U M U S T F I L L I N T H E E D G E (think 'Node') C L A S S - -
// - -
// NOTHING PUBLIC. LET IF DEFAULT TO PACKAGE/PRIVATE LIKE WE DID IN OTHER LL
// ASSIGNMENTS
// SO THAT YOU DONT HAVE TO CALL SETTERS AND GETTERS IN YOUR GRAPHLL CODE.

// ADD THIS CODE INTO YOUR Graph.java FILE JUST LIKE YOU DID IN THE BSTs
// ** of course you need to convert those comments to working code

class Edge {
    int dest, weight;
    Edge next;

    Edge(int dest, int weight, Edge next) {
        this.dest = dest;
        this.weight = weight;
        this.next = next;
    }
}
