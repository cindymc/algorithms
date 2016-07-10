package datastructures.graphs;

import java.util.*;

/**
 * Distance table for weighted graph, where each row is a vertex, and its columns are 'distance' (from a given source) and 'last vertex'.
 * The 'last vertex' is the one right before the destination node as we traverse backwards to the source.  So for
 * A -> B -> D, the 'last vertex' for D is B and the 'distance' from A is 2
 * The table is built as we explore the graph using BFS.
 *
 * Once we build the distance table, we can calculate the shortest path by putting 'last vertex' on the stack as
 * we traverse backwards from the priority queue, then pop the queue.  So for A to D, the shortest path is push D, push B, push A;
 * pop A, pop B, pop D => A -> B -> D
 *
 * Dijkstra's algorithm is a 'greedy' algorithm, where the priority queue holds the shortest distance to the next vertex.
 * This works well when we have only positive weights for the edges. We can choose the optimal path for each step.
 *
 * If we have negative edges, we use Belman-Ford, which is not greedy.
 */
public class WeightedDistanceTable
{
    public static Map<Integer,DistanceInfo> build(WeightedGraph graph, int source)
    {
        Map<Integer,DistanceInfo> distanceTable = new HashMap<>();

        // Note PriorityQueue ctor is taking a ctor of Comparator, and we implement the compare() method.
        // This queue returns nodes in the order of the shortest distance from the source.
        // A PriorityQueue is unbounded.  It is NOT synchronized.

        // A Comparator returns a negative integer, zero, or a positive integer as the first argument is less than,
        // equal to, or greater than the second
        PriorityQueue<VertexInfo> queue = new PriorityQueue<>(new Comparator<VertexInfo>(){
            @Override
            public int compare(VertexInfo v1, VertexInfo v2){
                // Wow, yes we need to cast to Integer so comparator will work
               return ((Integer) v1.getDistance()).compareTo(v2.getDistance());
            }
        });

        // Initialize an entry in the distance table for every vertex in the graph
        for (int j=0; j<graph.getNumVertices(); j++)
        {
            distanceTable.put(j, new DistanceInfo());
        }

        // Init distance to source and the last vertex to source
        distanceTable.get(source).setDistance(0);
        distanceTable.get(source).setLastVertex(source);

        // Add source to priority queue.
        VertexInfo sourceVertexInfo = new VertexInfo(source,0);
        queue.add(sourceVertexInfo);

        // Add VertexInfo to map
        Map<Integer,VertexInfo> vertexInfoMap = new HashMap<>();
        vertexInfoMap.put(source, sourceVertexInfo);

        // Explore the graph using BFS
        while (! queue.isEmpty())
        {
            // 'poll' retrieves and removes the head of the queue, returning null if empty
            VertexInfo vertexInfo = queue.poll();
            int currentVertex = vertexInfo.getVertexId();

            for (Integer neighbor : graph.getAdjacentVertices(currentVertex))
            {
                // Get the distance, accounting for the weighted edge
                int distance = distanceTable.get(currentVertex).getDistance() +
                        graph.getWeightedEdge(currentVertex, neighbor);

                DistanceInfo info = distanceTable.get(neighbor);

                // If we find a new shortest path to the neighbor, update the info
                if (info.getDistance() < distance)
                {
                    info.setDistance(distance);
                    info.setLastVertex(currentVertex);

                    // We've found a new short path to the neighbor, so remove the old
                    // node from the priority queue
                    VertexInfo neighborInfo = vertexInfoMap.get(neighbor);

                    // Note that 'remove' doesn't throw an exception; it returns true if found
                    if (neighborInfo != null)
                    {
                        queue.remove(neighborInfo);
                    }

                    // Add neighbor back with updated distance
                    neighborInfo = new VertexInfo(neighbor, distance);
                    queue.add(neighborInfo);
                    vertexInfoMap.put(neighbor, neighborInfo);
                }
            }
        }

        return distanceTable;
    }
}
