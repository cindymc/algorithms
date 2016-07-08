package datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Works well when graph is well-connected.
 * Overhead of V^2 space is worth it when the number of connections is large.
 *
 * Space complexity: O(V^2)
 * Time (is edge present): O(1)
 * Iterate over edges: O(V)
 */
public class AdjacencyMatrixGraph extends AbstractGraph
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
        if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0)
        {
            throw new IllegalArgumentException("Vertex number is not valid");
        }

        // Toggle directed edge to 1
        adjacencyMatrix[v1][v2] = 1;

        // If it's an undirected graph, toggle in other direction
        if (graphType == GraphType.UNDIRECTED)
        {
            adjacencyMatrix[v2][v1] = 1;
        }
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
            if (adjacencyMatrix[v][i] == 1)
            {
                aList.add(i);
            }
        }

        // Always sort return list
        Collections.sort(aList);
        return aList;
    }
}
