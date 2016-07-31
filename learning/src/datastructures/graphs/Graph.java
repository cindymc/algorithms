package datastructures.graphs;

import java.util.List;

/**
 */
public interface Graph
{
    // Directed vs not
    enum GraphType {DIRECTED, UNDIRECTED}

    GraphType typeOfGraph();

    // Add an edge, optionallly weighted
    void addEdge(int v1, int v2);
    void addEdge(int v1, int v2, int weight);

    // Get edge weight between these two vertices
    int getWeightedEdge(int v1, int v2);

    // Find all vertices adjacent to the graph
    List<Integer> getAdjacentVertices(int v);

    // Total vertices in a graph
    int getNumVertices();

    // Get in-degree of a vertex
    int getIndegree(int v);
}
