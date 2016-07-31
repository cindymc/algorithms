package datastructures.graphs.drivers;

import datastructures.graphs.AdjacencyMatrixGraph;
import datastructures.graphs.Graph;

/**
 * An adjacency matrix graph is built by a 2D matrix. Vertices are numbered starting from 0.
 *
 * Simply populate the matrix according to: whether the edge is weighted, and whether the edge
 * is directed.
 *
 * Created by cindymc on 7/31/16.
 */
public class GraphBuilder 
{
    public static Graph buildGraph()
    {
        Graph graph = new AdjacencyMatrixGraph(8, Graph.GraphType.DIRECTED);
        graph.addEdge(2, 7, 4);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 4, 2);
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 1, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(3, 6, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(7, 5, 4);

        return graph;
    }

}
