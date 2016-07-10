package datastructures.graphs;

/**
 * A class which holds the distance (from a given source node) info for any vertex.
 *
 * Created by cindymc on 7/8/16.
 */
public class DistanceInfo
{
    //private int distance = -1;   // For unweighted graph
    protected Integer distance = -1;    // For weighted graph, because weights can be positive or negative
    protected Integer lastVertex = Integer.MAX_VALUE;

    public void setInfo(int distance, int lastVertex)
    {
        this.distance = distance;
        this.lastVertex = lastVertex;
    }

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
