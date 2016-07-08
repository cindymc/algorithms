package datastructures.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by cindymc on 7/7/16.
 */
public class GraphTraversal
{
    enum Traversal {DEPTH, BREADTH}

    // Ensure that all nodes are traversed, even for an unconnected graph
    public static void traverse(Graph g,Traversal traversal)
    {
        int numVertices = g.getNumVertices();

        // Init visited array
        int [] visited = new int[numVertices];

        for (int i=0; i<numVertices; i++)
        {
            if (traversal == Traversal.DEPTH)
            {
                depthFirstTraversal(g, visited, i);
            }
            else
            {
                breadthFirstTraversal(g, visited, i);
            }
        }
    }

    public static List<Integer> shortestPath(Graph graph, int source, int destination)
    {

    }


    /**
     * Breadth-first
     */
    private static void breadthFirstTraversal(Graph graph, int[] visited, int currentVertex)
    {
        // Note that LinkedList implements Queue!
        Queue<Integer> queue = new LinkedList<>();
        queue.add(currentVertex);

        while (!queue.isEmpty())
        {
            int vertex = queue.remove();    // 'remove' instead of 'poll' because we've checked for non-empty queue

            // If we've already seen this node before, continue
            if (visited[vertex] == 1) {
                continue;
            }
            System.err.println(vertex + " -> ");
            visited[vertex] = 1;

            List<Integer> list = graph.getAdjacentVertices(currentVertex);
            for (int v : list)
            {
                if (visited[v] != 1)
                {
                    queue.add(v);      // 'add' instead of 'offer' because we didn't limit the size of the linked list
                }
            }
        }
    }

    /**
     * Depth-first
     */
    private static void depthFirstTraversal(Graph graph, int[] visited, int currentVertex)
    {
        if (visited[currentVertex] == 1)
        {
            return;
        }
        visited[currentVertex] = 1;

        // Now recurse over all adjacent vertices
        List<Integer> list = graph.getAdjacentVertices(currentVertex);
        for (int vertex : list)
        {
            depthFirstTraversal(graph, visited, vertex);
        }

        // Process the node
        System.err.println(currentVertex + " -> ");
    }
}
