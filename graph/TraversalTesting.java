package graph;

import org.junit.Test;
import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

/** Unit tests for the Traversal class.
 *
 *  @author Ajai Sharma
 */
public class TraversalTesting {
    private class AddToArrayListDepthFirst extends DepthFirstTraversal {
        private ArrayList<Integer> _seen;
        AddToArrayListDepthFirst(Graph g) {
            super(g);
            _seen = new ArrayList<Integer>();
        }
        protected boolean visit(int v) {
            _seen.add(v);
            return super.visit(v);
        }
        ArrayList<Integer> report() {
            return _seen;
        }
        void clearSeen() {
            _seen.clear();
        }
    }
    private class AddToArrayListDepthFirstPost extends DepthFirstTraversal {
        private ArrayList<Integer> _seen;
        AddToArrayListDepthFirstPost(Graph g) {
            super(g);
            _seen = new ArrayList<Integer>();
        }
        protected boolean postVisit(int v) {
            _seen.add(v);
            return super.postVisit(v);
        }
        ArrayList<Integer> report() {
            return _seen;
        }
        void clearSeen() {
            _seen.clear();
        }
    }
    private class AddToArrayListBreadthFirst extends BreadthFirstTraversal {
        private ArrayList<Integer> _seen;
        AddToArrayListBreadthFirst(Graph g) {
            super(g);
            _seen = new ArrayList<Integer>();
        }
        protected boolean visit(int v) {
            _seen.add(v);
            return super.visit(v);
        }
        ArrayList<Integer> report() {
            return _seen;
        }
        void clearSeen() {
            _seen.clear();
        }
    }

    boolean checkListEquality(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) {
                return false;
            }
        }
        return true;
    }

    boolean checkListContainment(List<List<Integer>> poss, List<Integer> foo) {
        for (List<Integer> bar : poss) {
            if (checkListEquality(foo, bar)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void traversalTests() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(5, 4);
        g.add(5, 3);
        g.add(4, 1);
        g.add(3, 2);
        g.add(1, 5);


        ArrayList<List<Integer>> dfsPossibilites;
        dfsPossibilites = new ArrayList<List<Integer>>();
        dfsPossibilites.add(asList(5, 4, 1, 3, 2));
        dfsPossibilites.add(asList(5, 3, 2, 4, 1));

        ArrayList<List<Integer>> dfsPostPossibilites;
        dfsPostPossibilites = new ArrayList<List<Integer>>();
        dfsPostPossibilites.add(asList(1, 4, 2, 3, 5));
        dfsPostPossibilites.add(asList(2, 3, 1, 4, 5));

        ArrayList<List<Integer>> bfsPossibilites;
        bfsPossibilites = new ArrayList<List<Integer>>();
        bfsPossibilites.add(asList(5, 4, 3, 1, 2));
        bfsPossibilites.add(asList(5, 3, 4, 1, 2));
        bfsPossibilites.add(asList(5, 4, 3, 2, 1));
        bfsPossibilites.add(asList(5, 3, 4, 2, 1));

        AddToArrayListBreadthFirst bfs = new AddToArrayListBreadthFirst(g);
        bfs.traverse(5);
        assert (checkListContainment(bfsPossibilites, bfs.report()));
        AddToArrayListDepthFirst dfs = new AddToArrayListDepthFirst(g);
        dfs.traverse(5);
        assert (checkListContainment(dfsPossibilites, dfs.report()));
        AddToArrayListDepthFirstPost dfsp = new AddToArrayListDepthFirstPost(g);
        dfsp.traverse(5);
        assertEquals(5, dfsp.report().size());
        assert (checkListContainment(dfsPostPossibilites, dfsp.report()));
    }
}
