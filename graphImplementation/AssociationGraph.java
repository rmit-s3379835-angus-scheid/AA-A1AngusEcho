package graphImplementation;

import java.io.PrintWriter;
import java.util.List;

/**
 * Association graph interface used to implement the communication graphs.
 * <p>
 * Note, you should not need to modify this.
 *
 * @author Jeffrey Chan, 2019.
 */
public interface AssociationGraph {

    /**
     * Adds a vertex to the graph.  If the vertex already exists in the graph, no changes are made.
     *
     * @param vertLabel Vertex to add.
     */
    void addVertex(String vertLabel);


    /**
     * Adds an edge to the graph.  If the edge already exists in the graph, no changes are made.  If one of the vertices doesn't exist, a warning to System.err should be issued
     * and no edge or vertices should be added.
     *
     * @param srcLabel Source vertex of edge to add.
     * @param tarLabel Target vertex of edge to add.
     * @param weight   Integer weight to add between edges.
     */
    void addEdge(String srcLabel, String tarLabel, int weight);


    /**
     * Get the edge weight.
     *
     * @param srcLabel Source vertex of edge to check.
     * @param tarLabel Target vertex of edge to check.
     * @returns int Value of edge weight.  If edge doesn't exist, return -1;
     */
    int getEdgeWeight(String srcLabel, String tarLabel);


    /**
     * Update the weight of an edge in the graph.  If the edge doesn't exists in the graph, no changes are made, but a warning to System.err should be issued.  If one of the
     * vertices doesn't exist, a warning to System.err should be issued and no edge or vertices should be added.
     *
     * @param srcLabel Source vertex of edge to update weight of.
     * @param tarLabel Target vertex of edge to update weight of.
     * @param weight   Weight to update edge to.  If weight = 0, delete the edge.
     */
    void updateWeightEdge(String srcLabel, String tarLabel, int weight);


    /**
     * Removes a vertex from the graph.  If the vertex doesn't exists in the graph, no changes are made, but a warning to System.err should be issued.
     *
     * @param vertLabel Vertex to remove.
     */
    void removeVertex(String vertLabel);

    void removeEdge(String srcLabel, String tarLabel);


    /**
     * Returns the set of in-neighbours for a vertex.  If vertex doesn't exist, then a warning to System.err should be issued.
     *
     * @param vertLabel Vertex to find the in-neighbourhood for.
     * @returns The set of in-neighbours of vertex 'vertLabel' and their associated edge weights.
     */
    List<MyPair> inNearestNeighbours(int k, String vertLabel);


    /**
     * Returns the set of out-neighbours for a vertex.  If vertex doesn't exist, then a warning to System.err should be issued.
     *
     * @param vertLabel Vertex to find the out-neighbourhood for.
     * @returns The set of out-neighbours of vertex 'vertLabel' and their associated edge weights.
     */
    List<MyPair> outNearestNeighbours(int k, String vertLabel);


    /**
     * Prints the list of vertices to PrintWriter 'os'.
     *
     * @param os PrinterWriter to print to.
     */
    void printVertices(PrintWriter os);


    /**
     * Prints the list of edges to PrintWriter 'os'.
     *
     * @param os PrinterWriter to print to.
     */
    void printEdges(PrintWriter os);


} // end of interface graphImpl.AssociationGraph
