package datastructures.graphs;

/**
 * A class which holds the vertex id and the distance of the edge that leads to that vertex from its neighbor.
 * As we build up the DistanceTable, we work with a PriorityQueue of VertexInfo, ordered by the shortest distance
 * to the given neighbor.  (If we consider edges, we also compare the number of edges traversed thus far).
 *
 * A VertexInfo is created initially from the source vertex with a distance of 0.  Subsequent VertexInfo are created as
 * we find the nearest vertex to the current vertex.
 *
 * The DISTANCE is the TOTAL CUMULATIVE DISTANCE from the source
 * The NUMEDGES is the TOTAL NUMBER OF EDGES TRAVERSED thus far from the source
 *
 *
 * Created by cindymc on 7/8/16.
 */
public class VertexInfo
{
    private Integer numEdges;  // total
    private int vertexId;
    private int distance;  // total

    public VertexInfo(int vertexId, int weight, int edges)
    {
        this.vertexId = vertexId;
        this.numEdges = edges;
        this.distance = weight;
    }

    public Integer getNumEdges() { return numEdges; }
    public Integer getDistance() {
        return distance;
    }
    public Integer getVertexId() { return vertexId; }
}
