package datastructures.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Distance table for unweighted graph, where each row is a vertex, and its columns are 'distance' (from a given source) and 'last vertex'.
 * The 'last vertex' is the one right before the destination node as we traverse backwards to the source.  So for
 * A -> B -> D, the 'last vertex' for D is B and the 'distance' from A is 2
 * The table is built as we explore the graph using BFS.
 *
 * Once we build the distance table, we can calculate the shortest path by putting 'last vertex' on the stack as
 * we traverse backwards from the stack, then pop the stack.  So for A to D, the shortest path is push D, push B, push A;
 * pop A, pop B, pop D => A -> B -> D
 * Created by cindymc on 7/8/16.
 */
public class DistanceTable  // unweighted
{
    public static Map<Integer,DistanceInfo> build(Graph graph, int source)
    {
        Map<Integer,DistanceInfo> distanceTable = new HashMap<>();

        // Initialize an entry in the distance table for every vertex in the graph
        for (int j=0; j<graph.getNumVertices(); j++)
        {
            distanceTable.put(j, new DistanceInfo());
        }

        // Init distance to source and the last vertex to source
        distanceTable.get(source).setDistance(0);
        distanceTable.get(source).setLastVertex(source);

        // Add source to queue.  NOTE that LinkedList implements Queue.
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);

        // Explore the graph using BFS
        while (! queue.isEmpty())
        {
            // use 'pollFirst' rather than 'getFirst' because latter throws 'NoSuchElementException'
            int currentVertex = queue.pollFirst();

            for (int i : graph.getAdjacentVertices(currentVertex))
            {
                int currentDistance = distanceTable.get(i).getDistance();

                // If it's un-initialized, get the distance from the current vertex and add one (because this is
                // an unweighted graph, each hop will have a weight of 1, so a distance of 1)
                if (currentDistance == -1)
                {
                    currentDistance = 1 + distanceTable.get(currentVertex).getDistance();

                    // We're incrementing hops by one
                    distanceTable.get(i).setDistance(currentDistance);

                    // Point last node to the current
                    distanceTable.get(i).setLastVertex(currentVertex);

                    // Only enqueue neighbors if it has other adjacent vertices
                    if (! graph.getAdjacentVertices(i).isEmpty())
                    {
                        queue.add(i);
                    }
                }
            }
        }

        return distanceTable;
    }
}
