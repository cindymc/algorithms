package datastructures.graphs;

/**
 * A class which holds the vertex id and the weight of the edge that leads to that vertex
 * from its neighbor.
 *
 * Created by cindymc on 7/8/16.
 */
public class VertexInfo
{
    private int vertexId;
    private int distance;

    public VertexInfo(int vertexId, int distance)
    {
        this.vertexId = vertexId;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
    public int getVertexId() { return vertexId; }
}
