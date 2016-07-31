package datastructures.graphs;

/**
 * A class which holds the vertex id and the weight of the edge that leads to that vertex
 * from its neighbor.
 *
 * (How close is this vertex to a given node?)
 *
 * Created by cindymc on 7/8/16.
 */
public class VertexInfo
{
    private Integer numEdges;
    private int vertexId;
    private int weight;  // weight of the edge that leads to that vertex from its neighbour

    public VertexInfo(int vertexId, int weight, int edges)
    {
        this.vertexId = vertexId;
        this.numEdges = edges;
        this.weight = weight;
    }

    public Integer getNumEdges() { return numEdges; }
    public Integer getDistance() {
        return weight;
    }
    public Integer getVertexId() { return vertexId; }
}
