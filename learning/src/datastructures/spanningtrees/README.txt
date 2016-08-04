A Spanning Tree is a subgraph that contains all the vertices, and is also a tree.  It looks like the original graph,
but all the vertices are not necessariliy connected in the same manner as in the original graph.

PRIM's
------
There are algorithms that work well for UNDIRECTED, CONNECTED graphs to find the shortest path from A to B: Prim's
Algorithm.

There are two parts to this algorithm:

1. Generate the distance table (similar to Dijkstra's) using ANY vertex as the source.
2. Use that table to get paths to ALL other vertices from that source.

Dijkstra:  distance[neighbor] = distance[source] + edgeWeight[source, neighbor]
Prim's: distance[neighbor] = edgeWeight[source,neighbor]

So for Prim's, we only care about the edge weight, not the cumulative distance from the source.

An edge is chosen to be part of the spanning tree if:

a. the vertex is the closest vertex at the current step
b. the vertex is not part of the spanning tree already.


(I think)
Dijkstra's : O(V^2)
Prim's: O(E + VlogV)
Kruskal's: O(ElogV)


KRUSKAL
-------

Works well for connected and unconnected graphs.

Main processing time involves sorting the edges by weight; this is the running time of the best sorting algorithms.

A forest is an unconnected graph.

1. Use a priority queue of edges where the weights of the edges determine the priority of the edge
2. When adding a new edge, make sure that it doesn't create a cycle in the spanning tree (that it's not already in the tree)



Prim's vs Kruskal:
------------------

In Prim's algorithm, the next edge in the MST shall be the cheapest edge in the current vertex. In Kruskal's algorithm,
we shall choose the cheapest edge, but it may not be with the current vertex.

Prim's algorithm is significantly faster in the limit when you've got a really dense graph with many more edges than
vertices. Kruskal performs better in typical situations (sparse graphs) because it uses simpler data structures.

The basic difference is in which edge you choose to add next to the spanning tree in each step.

In Prim's, you always keep a connected component, starting with a single vertex. You look at all edges from the current
component to other vertices and find the smallest among them. You then add the neighbouring vertex to the component,
increasing its size by 1. In N-1 steps, every vertex would be merged to the current one if we have a connected graph.

In Kruskal's, you do not keep one connected component but a forest. At each stage, you look at the globally smallest
edge that does not create a cycle in the current forest. Such an edge has to necessarily merge two trees in the current
forest into one. Since you start with N single-vertex trees, in N-1 steps, they would all have merged into one if the
graph was connected.