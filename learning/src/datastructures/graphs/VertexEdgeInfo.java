package datastructures.graphs;

/**
 * A class which holds the vertex id and the weight of the edge that leads to that vertex
 * from its neighbor.
 *
 * Created by cindymc on 7/8/16.
 */
public class VertexEdgeInfo
{
    private Integer vertexId;
    private Integer numEdges;
    private Integer distance;

    public VertexEdgeInfo(int vertexId, int distance, int edges)
    {
        this.vertexId = vertexId;
        this.numEdges = edges;
        this.distance = distance;
    }

    public Integer getDistance() {
        return distance;
    }
    public Integer getVertexId() { return vertexId; }
    public Integer getNumEdges() { return numEdges; }
}
