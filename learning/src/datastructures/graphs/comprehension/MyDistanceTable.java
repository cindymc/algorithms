package datastructures.graphs.comprehension;

import datastructures.graphs.DistanceInfo;
import datastructures.graphs.Graph;
import datastructures.graphs.VertexInfo;

import java.util.*;

/**
 * Three main data structures:
 *
 * 1. Map<Integer, DistanceInfo> distanceTable, from which we can construct the shortest path
 * 2. PriorityQueue<VertexInfo> queue, so we can keep track of the shortest path as we traverse from source
 * 3. Map<Integer, VertexInfo> vertexInfoMap, so we can keep track of the total distance from source
 *
 * Created by cindymc on 8/2/16.
 */
public class MyDistanceTable
{
    public Map<Integer, DistanceInfo> build(Graph graph, int sourceId, int destId) {
        // Init the return.  A DistanceInfo holds the distance from the source, and the ID of the last vertex in the
        // traversal from the source to this node.
        Map<Integer, DistanceInfo> distanceTable = new HashMap<>();
        for (int i = 0; i < graph.getNumVertices(); i++) {
            distanceTable.put(i, new DistanceInfo());
        }

        // PriorityQueue with a comparator to hold path as we progress
        Queue<VertexInfo> queue = new PriorityQueue<>(new MyComparator());

        // Start with source node

        // Create a DistanceInfo and add to map
        distanceTable.put(sourceId, new DistanceInfo(sourceId, 0, 0));

        // Create a VertexInfo and add to queue
        VertexInfo currentVertex = new VertexInfo(sourceId, 0, 0);
        queue.add(currentVertex);

        // Create a Map of VertexInfo to hold the total distance from source of each vertex
        Map<Integer, VertexInfo> vertexInfoMap = new HashMap<>();
        vertexInfoMap.put(sourceId, currentVertex);

        // Start at source, find nearest neighbor, and build up map as we compare distances
        while (!queue.isEmpty())
        {
            // Pull head of queue
            VertexInfo v = queue.poll();
            int currentId = v.getVertexId();

            // Get its neighbors and loop over, building a DistanceInfo for each
            List<Integer> neighbors = graph.getAdjacentVertices(currentId);
            for (Integer neighbor : neighbors)
            {
                // Get its DistanceInfo.  This will initially be an empty one
                DistanceInfo ndi = distanceTable.get(neighbor);
                int nDistance = ndi.getDistance();

                // Calculate distance from current vertex and add to total distance to current vertex
                int distance = v.getDistance() + graph.getWeightedEdge(currentId, neighbor);

                // Calculate number of edges to get here
                int numEdges = distanceTable.get(currentVertex).getNumEdges() + 1;

                // If it's less than the total distance associated with current DistanceInfo for this node,
                // then update it
                if (distance < nDistance)
                {
                    // Update the distanceInfo from the current vertex to this vertex
                    ndi.setInfo(currentId, distance, numEdges);

                    // Get the VertexInfo from map and update it.  Remove from queue if we need to
                    VertexInfo nvi = vertexInfoMap.get(neighbor);
                    if (nvi != null)
                    {
                        queue.remove(nvi);
                    }

                    // Update and add back into queue
                    nvi = new VertexInfo(neighbor, distance, numEdges);
                    queue.add(nvi);

                    // Update the VertexInfoMap
                    vertexInfoMap.put(neighbor, nvi);
                }
            }
        }
        return distanceTable;
    }

    class MyComparator implements Comparator<VertexInfo>
    {
        @Override
        public int compare(VertexInfo v1, VertexInfo v2)
        {
            return (v1.getDistance()).compareTo(v2.getDistance());
        }
    }

    class MinEdgesComparator implements Comparator<VertexInfo>
    {
        @Override
        public int compare(VertexInfo v1, VertexInfo v2)
        {
            // If distances are same, return one with minimal edges
            if (v1.getDistance().equals(v2.getDistance()))
            {
                return v1.getNumEdges().compareTo(v2.getNumEdges());
            }
            // Otherwise, return shorter distance
            return (v1.getDistance()).compareTo(v2.getDistance());
        }
    }
}
