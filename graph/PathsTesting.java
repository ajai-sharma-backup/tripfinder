package graph;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/** Unit tests for the ShortestPaths class.
 *
 *  @author Ajai Sharma
 */
public class PathsTesting {
    class VideoGraphPaths extends SimpleShortestPaths {
        public VideoGraphPaths(Graph G, int source, int dest) {
            super(G, source, dest);
            int m = G.maxVertex() + 1;
            _weights = new double[m][m];
            for (int i = 0; i < m; i++) {
                _weights[i] = new double[m];
            }
            _dist = new double[m];


            _weights[4][2] = 12.2;
            _weights[4][3] = 102.0;
            _weights[5][3] = 9.1;
            _weights[2][3] = 6.5;
            _weights[4][5] = 11.2;
            _weights[5][6] = 30.0;
            _weights[5][7] = 3.0;
            _weights[7][3] = 2000.0;

            _dist[4] = 102.0;
            _dist[2] = 4.0;
            _dist[5] = 9.1;
            _dist[6] = 40.0;
            _dist[7] = 1000.0;
        }

        @Override
        public double getWeight(int u, int v) {
            return _weights[u][v];
        }

        @Override
        protected double estimatedDistance(int v) {
            return _dist[v];
        }

        private double[][] _weights;
        private double[] _dist;
    }

    @Test
    public void testWeights() {
        Graph videoGraph = generateVideoGraph();
        VideoGraphPaths vgp =
                new VideoGraphPaths(videoGraph, 4, 3);
        vgp.setPaths();
        assertEquals(Double.POSITIVE_INFINITY, vgp.getWeight(1000), 0.01);
        assertEquals(0, vgp.getWeight(4), 0.01);
        assertEquals(18.7, vgp.getWeight(3), 0.01);
        assertEquals(4, vgp.getPredecessor(5));
        assertEquals(0, vgp.getPredecessor(6));
        assertEquals(0, vgp.getPredecessor(9));
        List<Integer> foo = vgp.pathTo();
        assertEquals(3, foo.size());
        assertEquals((Integer) 4, foo.get(0));
        assertEquals((Integer) 2, foo.get(1));
        assertEquals((Integer) 3, foo.get(2));
    }

    Graph generateVideoGraph() {
        Graph vg = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            vg.add();
        }
        vg.remove(1);
        vg.add(4, 2);
        vg.add(4, 5);
        vg.add(2, 3);
        vg.add(5, 6);
        vg.add(4, 7);
        vg.add(7, 3);
        vg.add(4, 3);
        vg.add(5, 3);
        return vg;
    }
}
