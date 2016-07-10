package datastructures.graphs;

import java.util.List;

/**
 * Created by cindymc on 7/7/16.
 */
abstract class AbstractGraph implements Graph
{
    protected Graph.GraphType graphType;
    protected int numVertices = 0;

    AbstractGraph(int numVertices, GraphType graphType)
    {
        this.numVertices = numVertices;
        this.graphType = graphType;
    }

    public abstract List<Integer> getAdjacentVertices(int v);
    public int getNumVertices()
    {
        return numVertices;
    }
}
