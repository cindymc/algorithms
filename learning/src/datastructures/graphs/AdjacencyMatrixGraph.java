package datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Works well when graph is well-connected.  Also implements weighted graph.
 * Overhead of V^2 space is worth it when the number of connections is large.
 *
 * Space complexity: O(V^2)
 * Time (is edge present): O(1)
 * Iterate over edges: O(V)
 */
public class AdjacencyMatrixGraph extends AbstractGraph implements WeightedGraph
{
    private int[][] adjacencyMatrix;

    public AdjacencyMatrixGraph(int numVertices, GraphType graphType)
    {
        super(numVertices, graphType);

        adjacencyMatrix = new int[numVertices][numVertices];

        // Init matrix to all zeros
        for (int i=0; i< numVertices; i++)
        {
            for (int j=0; j<numVertices; j++)
            {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    @Override
    public void addEdge(int v1, int v2)
    {
        addEdge(v1, v2, 1);
    }

    @Override
    public void addEdge(int v1, int v2, int weight) {
        if (v1 >= numVertices || v2 >= numVertices || v1 < 0 || v2 < 0) {
            throw new  IllegalArgumentException("Vertex number is not valid");
        }
        adjacencyMatrix[v1][v2] = weight;
        if(graphType == GraphType.UNDIRECTED) {
            adjacencyMatrix[v2][v1] = weight;
        }
    }

    @Override
    public int getWeightedEdge(int v1, int v2) {
        return adjacencyMatrix[v1][v2];
    }

    @Override
    public int getIndegree(int v){
        if (v < 0 ||  v >= numVertices) {
            throw new  IllegalArgumentException("Vertex number is not valid");
        }
        int indegree = 0;
        for (int i = 0; i < getNumVertices(); i++) {
            if (adjacencyMatrix[i][v] != 0) {
                indegree++;
            }
        }
        return indegree;
    }

    @Override
    public List<Integer> getAdjacentVertices(int v)
    {
        // Ensure valid
        if (v >= numVertices || v < 0 )
        {
            throw new IllegalArgumentException("Vertex number is not valid");
        }

        List<Integer> aList = new ArrayList<>();
        for (int i=0;i<numVertices;i++)
        {
            if (adjacencyMatrix[v][i] != 0)
            {
                aList.add(i);
            }
        }

        // Always sort return list
        Collections.sort(aList);
        return aList;
    }
}
