package graph;

/* See restrictions in Graph.java. */

import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Collections;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Ajai K. Sharma 
 */
public abstract class ShortestPaths {
    /** Compares vertices based on their current weight plus their
     *  distance heuristic. Used in AStar. Note that this comparison
     *  changes based on the state of ShortestPaths. */
    private class AStarComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer v1, Integer v2) {
            return Double.compare(getWeight(v1) + estimatedDistance(v1),
                    getWeight(v2) + estimatedDistance(v2));
        }
    }

    /** PriorityQueue to serve as the fringe in A*. */
    @SuppressWarnings("serial")
    private class AStarQueue extends PriorityQueue<Integer> {
        /** Constructs an AStarQueue. */
        AStarQueue() {
            super(_G.vertexSize(), new AStarComparator());
        }

        /** Updates the queue's ordering as the weight of
         *  vertex V is changed. */
        public void reorder(int v) {
            remove(v);
            add(v);
        }
    }

    /** Traverses the graph using the A* algorithm to find
     *  shortest paths. */
    private class AStarTraversal extends Traversal {
        /** Constructs an AStar traversal going to vertex TARGET
         *  using QUEUE as fringe. */
        AStarTraversal(int target, AStarQueue queue) {
            super(_G, queue);
            _queue = queue;
            _target = target;
        }

        /** Constructs an AStar traversal going to vertex TARGET. */
        AStarTraversal(int target) {
            this(target, new AStarQueue());
        }

        @Override
        protected boolean visit(int v) {
            return v != _target;
        }

        @Override
        protected boolean processSuccessor(int u, int v) {
            if (marked(v)) {
                return false;
            }
            double k = getWeight(u, v) + getWeight(u);
            if (getWeight(v) > k) {
                setWeight(v, k);
                setPredecessor(v, u);
                _queue.reorder(v);
            }
            _queue.reorder(u);
            return true;
        }


        /** The target vertex of the search. If not set, this is
         *  zero and results in Dijikstra's algorithm. */
        private final int _target;
        /** The queue used as the fringe. Stored here to allow
         *  reordering. */
        private final AStarQueue _queue;
    }


    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        AStarTraversal traversal = new AStarTraversal(_dest);

        HashSet<Integer> foo = new HashSet<Integer>();
        for (int bar : _G.vertices()) {
            foo.add(bar);
        }
        traversal.traverse(foo);
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        int temp = v;
        while (temp != _source) {
            result.add(temp);
            temp = getPredecessor(temp);
        }
        result.add(_source);
        Collections.reverse(result);
        return result;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
}
