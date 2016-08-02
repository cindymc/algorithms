package datastructures.graphs.comprehension;

import datastructures.graphs.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cindymc on 8/2/16.
 */
public class MyMatrixGraph implements Graph
{
    int numVertices;
    GraphType graphType;   // Directed vs undirected

    // Rows will be the vertices; cols will be the edge weights to the respective vertex
    int [][] matrix;

    public MyMatrixGraph(int numVertices, GraphType graphType)
    {
        this.graphType = graphType;
        this.numVertices = numVertices;

        for (int i=0; i<numVertices; i++)
        {
            for (int j=0; j<numVertices; j++)
            {
                matrix[i][j] = 0;
            }
        }
    }

    @Override
    public GraphType typeOfGraph() {
        return graphType;
    }

    @Override
    public void addEdge(int v1, int v2)
    {
        addEdge(v1,v2,1);
    }

    @Override
    public void addEdge(int v1, int v2, int weight)
    {
        // Check that v1 and v2 are within bounds, then populate matrix
        matrix[v1][v2] = weight;
        if (graphType.equals(GraphType.UNDIRECTED))
        {
            matrix[v2][v1] = weight;
        }

    }

    @Override
    public int getWeightedEdge(int v1, int v2) {
        return matrix[v1][v2];
    }

    @Override
    public List<Integer> getAdjacentVertices(int v)
    {
        List<Integer> adj = new ArrayList<>();
        for (int i = 0; i<numVertices; i++)
        {
            if (matrix[v][i] != 0)
            {
                adj.add(i);
            }
        }
        return adj;
    }

    @Override
    public int getNumVertices()
    {
         return numVertices;
    }

    @Override
    public int getIndegree(int v)
    {
        // Verify valid vertex id.

        int c = 0;

        // In-degree means there are other vertices connecting to this.  Go down the columns of the matrix for this vertex
        for (int i=0; i<numVertices; i++)
        {
            if (matrix[i][v] != 0)
            {
                ++c;
            }
        }
        return c;
    }
}
