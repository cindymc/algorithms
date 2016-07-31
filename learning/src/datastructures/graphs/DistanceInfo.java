package datastructures.graphs;

/**
 * A class which holds the distance (from a given source node) info for any vertex.
 *
 * (How far away from the source node are we, and what's the last node on the path from the source before
 * reaching this node?)
 *
 * Created by cindymc on 7/8/16.
 */
public class DistanceInfo
{
    protected Integer distance = -1;    // For weighted graph, because weights can be positive or negative
    protected Integer lastVertex = Integer.MAX_VALUE;

    // Number of edges traversed from source to this node
    private Integer numEdges = Integer.MAX_VALUE;

    public void setInfo(int lastVertex, int distance, int numEdges)
    {
        this.lastVertex = lastVertex;
        this.distance = distance;
        this.numEdges = numEdges;
    }

    public Integer getNumEdges() { return numEdges; }
    public int getDistance() { return distance; }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public int getLastVertex() {
        return lastVertex;
    }
    public void setLastVertex(int lastVertex) {
        this.lastVertex = lastVertex;
    }
}
