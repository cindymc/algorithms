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
public class DistanceTable
{

    /**
     * We build a distance table from the source vertex TO EVERY NODE IN THE GRAPH
    */
    public static Map<Integer,DistanceInfo> build(Graph graph, int source, boolean fewestEdges)
    {
        // This is what we'll ultimately return
        Map<Integer,DistanceInfo> distanceTable = new HashMap<>();

        // Initialize an entry in the distance table for every vertex in the graph
        for (int j=0; j<graph.getNumVertices(); j++)
        {
            distanceTable.put(j, new DistanceInfo());
        }

        // Comparator depends on whether we care about fewest edges
        Comparator<VertexInfo> comparator = (fewestEdges ? new MinEdgesComparator() : new DijkstraComparator());

        // For a weighted graph, we need to account for edge weights, and a way to compare shortest path to next
        // vertex.  We'll use a PriorityQueue to hold the next vertex rankings according to shortest distance.
        // If there's a tie, we use the one with fewest edges.
        PriorityQueue<VertexInfo> queue = new PriorityQueue<>(comparator);

        // Init distance to source and the last vertex to source
        distanceTable.get(source).setDistance(0);
        distanceTable.get(source).setLastVertex(source);

        // Add source to priority queue.
        VertexInfo sourceVertexInfo = new VertexInfo(source, 0 /*weight of edge from vertex*/, 0/*num edges*/);
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
                // Distance info about current vertex
                DistanceInfo currentInfo = distanceTable.get(currentVertex);
                DistanceInfo neighborInfo = distanceTable.get(neighbor);

                // Total distance includes weighted edge
                int distance = currentInfo.getDistance() +  graph.getWeightedEdge(currentVertex, neighbor);

                // Distance to this neighbor
                int neighborDistance = neighborInfo.getDistance();

                int numEdges = 0;
                if (fewestEdges)
                {
                     numEdges = distanceTable.get(currentVertex).getNumEdges() + 1;
                }

                // If we find a new shortest path to the neighbor, update the info
                if ( distance < neighborDistance ||
                        (distance==neighborDistance && (numEdges < neighborInfo.getNumEdges())))
                {
                    // We've found a new short path to the neighbor, so remove the old node from the priority queue
                    // (if it existed)
                    neighborInfo.setInfo(currentVertex, neighborDistance, numEdges);

                    VertexInfo neighborVertexInfo = vertexInfoMap.get(neighbor);
                    if (neighborVertexInfo != null)
                    {
                        queue.remove(neighborVertexInfo);
                    }

                    // Update distance and add back to queue.  We'll start here next iteration.
                    neighborVertexInfo = new VertexInfo(neighbor, distance, numEdges);
                    queue.add(neighborVertexInfo);
                    vertexInfoMap.put(neighbor, neighborVertexInfo);
                }
            }
        }

        return distanceTable;
    }

    static class DijkstraComparator implements Comparator<VertexInfo>
    {
        @Override
        public int compare(VertexInfo v1, VertexInfo v2) {
            return ( v1.getDistance()).compareTo(v2.getDistance());
        }
    }

    static class MinEdgesComparator implements Comparator<VertexInfo>
    {
        @Override
        public int compare(VertexInfo v1, VertexInfo v2)
        {
            if (v1.getDistance().compareTo(v2.getDistance()) != 0)
            {
                return v1.getDistance().compareTo(v2.getDistance());
            }
            // Otherwise, if distance is same, go with fewest edges
            return v1.getNumEdges().compareTo(v2.getNumEdges());
        }
    }
}
