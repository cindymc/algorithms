package datastructures.graphs.comprehension;

import datastructures.graphs.Graph;

import java.util.*;

/**
 * Created by cindymc on 8/2/16.
 */
public class TopologicalSort
{
    public void sort(Graph graph)
    {
        // Data structures to hold
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer,Integer> indegreeMap = new HashMap<>();

        // Initialize indegreeMap, but hold onto the node(s) that have 0 indegree
        for (int i=0; i<graph.getNumVertices(); i++)
        {
            int inDegree = graph.getIndegree(i);
            indegreeMap.put(i, inDegree);

            if (inDegree==0)
            {
                queue.add(i);
            }
        }

        while (!queue.isEmpty())
        {
            // Start with vertex having inDegree of zero.  This means it has no dependencies
            int v = queue.poll();

            // Need a data structure to hold the results.
            List<Integer> rv = new ArrayList<>();
            rv.add(v);

            // Get its neighbors
            List<Integer> neighbors = graph.getAdjacentVertices(v);

            // For each of these, reduce the indegree by 1
            for (Integer neighbor : neighbors)
            {
                int reduced =  indegreeMap.get(neighbor) - 1;

                // TODO: this step kinda confuses me.  If we're dealing with object references, we should be able to do
                // operations in place (just update it)
                indegreeMap.remove(neighbor);
                indegreeMap.put(neighbor, reduced);
                if (reduced == 0)
                {
                    queue.add(neighbor);
                }
            }

            // TODO: this might only work if all vertices are connected
            if (rv.size() != graph.getNumVertices()) {
                throw new RuntimeException("The Graph had a cycle!");
            }
        }


    }
}
