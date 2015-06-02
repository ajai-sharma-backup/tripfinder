package graph;

import java.util.HashMap;

/* See restrictions in Graph.java. */

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *
 *  @author Ajai Sharma
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _weights = new HashMap<Integer, Double>();
        _predecessors = new HashMap<Integer, Integer>();
        for (int v : _G.vertices()) {
            setWeight(v, Double.POSITIVE_INFINITY);
            setPredecessor(v, 0);
        }
        setWeight(source, 0);
    }

    @Override
    public double getWeight(int v) {
        if (!_weights.containsKey(v)) {
            return Double.POSITIVE_INFINITY;
        }
        return _weights.get(v);
    }

    @Override
    protected void setWeight(int v, double w) {
        _weights.put(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (!_predecessors.containsKey(v)) {
            return 0;
        }
        return _predecessors.get(v);
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessors.put(v, u);
    }

    /** HashMap containing vertex weights. */
    private HashMap<Integer, Double> _weights;
    /** HashMap containing vertex predecessors. */
    private HashMap<Integer, Integer> _predecessors;

}
