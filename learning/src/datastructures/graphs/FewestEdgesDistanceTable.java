package datastructures.graphs;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
public class FewestEdgesDistanceTable
{
    public static Map<Integer,DistanceEdgeInfo> build(WeightedGraph graph, int source)
    {
        Map<Integer,DistanceEdgeInfo> distanceTable = new HashMap<>();

        // Note PriorityQueue ctor is taking a ctor of Comparator, and we implement the compare() method.
        // This queue returns nodes in the order of the shortest distance from the source.
        // A PriorityQueue is unbounded.  It is NOT synchronized.

        // A Comparator returns a negative integer, zero, or a positive integer as the first argument is less than,
        // equal to, or greater than the second
        Comparator<VertexEdgeInfo> comparator =
                new Comparator<VertexEdgeInfo> () {
                    @Override
                    public int compare(VertexEdgeInfo v1, VertexEdgeInfo v2)
                    {
                        if (v1.getDistance().compareTo(v2.getDistance()) != 0)
                        {
                            return v1.getDistance().compareTo(v2.getDistance());
                        }
                        // Otherwise, if distance is same, go with fewest edges
                        return v1.getNumEdges().compareTo(v2.getNumEdges());
                    }
                };
        PriorityQueue<VertexEdgeInfo> queue = new PriorityQueue<>(comparator);

        // Initialize an entry in the distance table for every vertex in the graph
        for (int j=0; j<graph.getNumVertices(); j++)
        {
            distanceTable.put(j, new DistanceEdgeInfo());
        }

        // Init distance to source and the last vertex to source
        distanceTable.get(source).setInfo(source, 0 /* distance */, 0 /* numEdges */);

        // Add source to priority queue.
        VertexEdgeInfo sourceVertexInfo = new VertexEdgeInfo(source,0,0);
        queue.add(sourceVertexInfo);

        // Add VertexInfo to map
        Map<Integer,VertexEdgeInfo> vertexInfoMap = new HashMap<>();
        vertexInfoMap.put(source, sourceVertexInfo);

        // Explore the graph using BFS
        while (! queue.isEmpty())
        {
            // 'poll' retrieves and removes the head of the queue, returning null if empty
            VertexEdgeInfo currentVertexInfo = queue.poll();

            for (Integer neighbor : graph.getAdjacentVertices(currentVertexInfo.getVertexId()))
            {
                // Current vertex; get info from table
                int vertexId = currentVertexInfo.getVertexId();
                DistanceEdgeInfo edgeInfo = distanceTable.get(vertexId);

                // Calculate total distance, which includes weighted edge
                int distance = edgeInfo.getDistance() + graph.getWeightedEdge(vertexId, neighbor);

                // Calculate distance to neighbor
                int neighborDistance = distanceTable.get(neighbor).getDistance();
                int edges = distanceTable.get(vertexId).getNumEdges() + 1;

                // If we find a new shortest path to the neighbor, update the info
                if (neighborDistance > distance || ( neighborDistance == distance) &&
                        (distanceTable.get(neighbor).getNumEdges() > edges))
                {
                    // Update distance table with neighbor information
                    distanceTable.get(neighbor).setInfo(vertexId, distance, edges);

                    VertexEdgeInfo neighborVertexInfo = vertexInfoMap.get(neighbor);

                    // Note that 'remove' doesn't throw an exception; it returns true if found
                    if (neighborVertexInfo != null)
                    {
                        queue.remove(neighborVertexInfo);
                    }

                    // Add neighbor back with updated distance
                    neighborVertexInfo = new VertexEdgeInfo(neighbor, distance, edges);
                    queue.add(neighborVertexInfo);
                    vertexInfoMap.put(neighbor, neighborVertexInfo);
                }
            }
        }

        return distanceTable;
    }
}
