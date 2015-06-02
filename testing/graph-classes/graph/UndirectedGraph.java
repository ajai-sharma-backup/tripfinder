package graph;

import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author Ajai Sharma
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int add() {
        ArrayList<Integer> s = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> sp =
            new ArrayList<ArrayList<Integer>>();
        sp.add(s);
        sp.add(s);
        int i = smallestOpenIndex();
        adjacencyLists().put(i, sp);
        return i;
    }
}
