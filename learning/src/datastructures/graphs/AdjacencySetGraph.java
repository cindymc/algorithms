package datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * A sparse graph with few connections between nodes.
 *
 * Space complexity: O(E+V)
 * Time (is edge present): Lg(Degree of V)
 * Iterate over edges: Degree of V
 */
public class AdjacencySetGraph extends AbstractGraph
{
    private List<Node> vertexList = new ArrayList<>();

    public AdjacencySetGraph(int numVertices, GraphType graphType)
    {
        super(numVertices, graphType);
        for (int i=0; i<numVertices; i++)
        {
            vertexList.add(new Node(i));
        }
    }

    @Override
    public void addEdge(int v1, int v2)
    {
        if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0)
        {
            throw new IllegalArgumentException("Vertex number is not valid");
        }

        vertexList.get(v1).addEdge(v2);
        if (graphType == GraphType.UNDIRECTED)
        {
            vertexList.get(v2).addEdge(v1);
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

        return vertexList.get(v).getAdjacentVertices();
    }
}
