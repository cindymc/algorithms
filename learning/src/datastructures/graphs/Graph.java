package datastructures.graphs;

import java.util.List;

/**
 * Created by cindymc on 7/7/16.
 */
public interface Graph
{
    enum GraphType {DIRECTED, UNDIRECTED}

    void addEdge(int v1, int v2);

    List<Integer> getAdjacentVertices(int v);

    int getNumVertices();
}
