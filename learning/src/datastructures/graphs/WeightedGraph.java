package datastructures.graphs;

/**
 * Created by cindy on 7/9/16.
 */
public interface WeightedGraph extends Graph
{
    void addEdge(int v1, int v2, int weight);
    int getWeightedEdge(int v1, int v2);
    public int getIndegree(int v);
}
