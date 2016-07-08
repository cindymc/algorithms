package datastructures.graphs;

import java.util.*;

/**
 * A Graph Node for Adjacency Set
 * Created by cindymc on 7/7/16.
 */
public class Node
{
    private int vertexNumber;
    private Set<Integer> adjacencySet = new HashSet<>();

    public Node(int vertexNumber)
    {
        this.vertexNumber = vertexNumber;
    }

    public int getVertexNumber()
    {
        return vertexNumber;
    }

    public void addEdge(int vertexNumber)
    {
        adjacencySet.add(vertexNumber);
    }

    public List<Integer> getAdjacentVertices()
    {
        List<Integer> sortedList = new ArrayList<>(adjacencySet);
        Collections.sort(sortedList);
        return sortedList;
    }
}
