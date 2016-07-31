package datastructures.graphs;

import java.util.*;

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



    // Dijkstra's algorithm
    public static void shortestPath(Graph graph, Integer source, Integer destination)
    {
        Map<Integer, DistanceInfo> distanceTable = DistanceTable.build(graph, source, false);
        shortestPath(distanceTable, source, destination);
    }

    // Weighted graph, but we want shortest path with fewest number of edges
    public static void shortestPathWithEdges(Graph graph, Integer source, Integer destination)
    {
        Map<Integer, DistanceInfo> distanceTable = DistanceTable.build(graph, source, true);
        shortestPath(distanceTable, source, destination);
    }


    /**
     * The magic in Dijkstra's algorithm is all in building the distance table: use a PriorityQueue and BFS to
     * build a Map of Vertex/DistanceInfo, where DistanceInfo holds the total distance from the source.
     * @param distanceTable
     * @param source
     * @param destination
     */
    private static void shortestPath(Map<Integer, DistanceInfo> distanceTable,
                                     int source, int destination)
    {
        // Backtrack using a stack, using the destination node
        Stack<Integer> stack = new Stack<>();
        stack.push(destination);

        // Backtrack by getting the previous node of every node and putting it on the stack.
        // Start at the destination.
        int previousVertex = distanceTable.get(destination).getLastVertex();
        while (previousVertex != -1 && previousVertex != source)
        {
            stack.push(previousVertex);
            previousVertex = distanceTable.get(previousVertex).getLastVertex();
        }

        if (previousVertex ==-1)
        {
            System.err.println("No path");
        }
        else
        {
            System.err.println("Shortest path: ");
            while (! stack.isEmpty())
            {
                System.err.println(stack.pop());
            }
        }
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
