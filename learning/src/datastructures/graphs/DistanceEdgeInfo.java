package datastructures.graphs;

/**
 * A class which holds the distance (from a given source node) info for any vertex,
 * and the number of edges it took to get there.
 *
 * Created by cindymc on 7/8/16.
 */
public class DistanceEdgeInfo extends DistanceInfo
{
    //private int distance = -1;   // For unweighted graph
    private Integer numEdges = Integer.MAX_VALUE;

    public Integer getNumEdges() { return numEdges; }

    public void setInfo(int lastVertex, int distance, int numEdges)
    {
        super.setInfo(lastVertex, distance);
        this.numEdges = numEdges;
    }
}
