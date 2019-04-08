import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class AdjList extends AbstractAssocGraph
{
	
	//For now, I will just hardcode the vert array to 8 indices and come back to it later.
	//java.lang.String[] verts = new String[8];
	public String[] verts;
	
    /**
	 * Constructs empty graph.
	 */
    public AdjList() {
    	 // Implement me!
    	System.out.println("*Adj List Initialised*");
    	verts = new String[8];
    } // end of AdjList()


    public void addVertex(String vertLabel) {
        // Implement me!
    	System.out.println("*Vertex adding: " + vertLabel + " *");
    	
    	//Iterate for an empty index.
    	for(int i = 0; i <= verts.length && i < (verts.length - 1); i++)
    	{
    		System.out.println("Iteration: i = " + i);
    		if (verts[i] == null)
    		{
    				verts[i] = vertLabel;
    				break;
    		}
    	}
    	
    	//Just for debugging.
    	for(int i = 0; i < verts.length; i++)
    	{
    		System.out.println(verts[i]);
    	}
    	
    	System.out.println("end of addVertex()");
    	
    } // end of addVertex()


    public void addEdge(String srcLabel, String tarLabel, int weight) {
        // Implement me!
    } // end of addEdge()


    public int getEdgeWeight(String srcLabel, String tarLabel) {
		    // Implement me!
    	
    		//for

		    // update return value
		    return EDGE_NOT_EXIST;
    } // end of existEdge()


    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
        // Implement me!
    } // end of updateWeightEdge()


    public void removeVertex(String vertLabel) {
        // Implement me!
    } // end of removeVertex()


    public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        return neighbours;
    } // end of inNearestNeighbours()


    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        return neighbours;
    } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os) {
        // Implement me!
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()

//This must be for the linked lists?
    protected class Node {
    	
    	protected int weight;
    	
    	//These refer to verts.
    	protected String srcLabel;
    	protected String tarLabel;
    	
    	//The next edge on the vert. 
    	protected Node next;

    	
    	public Node(int weight, String srcLabel, String tarLabel) {
    		weight = weight;
    		srcLabel = srcLabel;
    		tarLabel = tarLabel;
    	}
    	
    	
    	
    }
} // end of class AdjList
