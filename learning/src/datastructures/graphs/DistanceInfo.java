package datastructures.graphs;

/**
 * A class which holds the distance (from a given source node) info for any vertex.
 *
 * The result of building a DistanceTable is a Map of <VertexId, DistanceInfo>.  When calculating shortest path, we
 * start from the destination node, find it in that Map, push it onto the stack.  To find the next node in the path,
 * we GET THE LAST VERTEX from that node, and push it onto the stack.  We do this until the next node in the path is
 * the source.
 *
 * The DistanceTable is initialized with empty DistanceInfo for every vertex in the graph.  As we build up the
 * DistanceTable, we pop nodes off the PriorityQueue<VertexInfo>, get their ID, and
 *
 * (How far away from the source node are we, and what's the last node on the path from the source before
 * reaching this node?)
 *
 * Created by cindymc on 7/8/16.
 */
public class DistanceInfo
{
    protected Integer distance = Integer.MAX_VALUE;
    protected Integer lastVertex = -1;      // last vertex before this one while traversing from the source node

    // Number of edges traversed from source to this node
    private Integer numEdges = 0;


    public DistanceInfo(){}
    public DistanceInfo(int lastVertex, int distance, int numEdges)
    {
        setInfo(lastVertex, distance, numEdges);
    }


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
