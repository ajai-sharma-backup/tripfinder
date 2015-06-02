package graph;

import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Ajai Sharma
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int add() {
        ArrayList<ArrayList<Integer>> sp =
            new ArrayList<ArrayList<Integer>>();
        sp.add(new ArrayList<Integer>());
        sp.add(new ArrayList<Integer>());
        int i = smallestOpenIndex();
        adjacencyLists().put(i, sp);
        return i;
    }

}
