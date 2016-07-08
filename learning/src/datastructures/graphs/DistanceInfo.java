package datastructures.graphs;

/**
 * A class which holds the distance info for any vertex.
 *
 * Created by cindymc on 7/8/16.
 */
public class DistanceInfo
{
    private int distance = -1;
    private int lastVertex = -1;

    public int getDistance() {
        return distance;
    }

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
