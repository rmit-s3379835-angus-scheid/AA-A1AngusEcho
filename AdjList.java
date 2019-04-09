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
	
	//Map to monitor linkedlist to vert locations.
	Map vertListMap = new HashMap();
	
	
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
    				linkedList newLL = new linkedList(vertLabel);
    				//Also putting both the vertLabel and linked list in the map, for extra organisation.
    				vertListMap.put(vertLabel, newLL);
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
    	
    	System.out.println("starting addEdge");
    	
    	//Constrcut a new node if the input is valid.
    	if (srcLabel != null && tarLabel != null && weight != 0)
    	{
    		Node newNode =  new Node(weight, tarLabel, srcLabel);
    	}
    	
    	System.out.println("Input Valid!");
    	
    	//Iterate through the array to find the target vert
    	for(int i = 0; i < verts.length; i++)
    	{
    		System.out.println("Verts iteration");
    		
    		if(verts[i].equals(srcLabel))
    		{
    			System.out.println("Source found! :" + srcLabel);
    			//Make a new node here.
    			Node newNode = new Node(weight, tarLabel, srcLabel);
    			//Node 
    			//Node currentNode;
    			
    			//I need to find the list, and the end!
    			linkedList currentList = (linkedList) vertListMap.get(srcLabel);
    			
    			Node currentNode = currentList.head;
    			
    			//Detect if the head is empty first.
    			if (currentList.head == null)
    			{
    				System.out.println("Head is empty");
    				currentList.head = newNode;
    				currentList.size += 1;
    			}
    			else
    			{
    				//Iterate here.
    				System.out.println("Adding to later in the list");
    				System.out.println("currentNode.next status: " + currentNode.next);
    				
    				//if (currentNode.
    				
    				
    				
    				while (currentNode.next != null)
    				{
    					System.out.println("Iterating to later in the LL");
    					System.out.println("Iterating through LL: " + currentNode.srcLabel + ", " + currentNode.tarLabel + ", " + currentNode.weight);
    					currentNode = currentNode.next;
    					currentList.size += 1;
    				}
    				
    				if (currentNode.next == null)
    				{
    					System.out.println("current.next == null");
    					currentNode.next = newNode;
    					
    				}
    				
    			}
    			
    			return;
    				
    		}
    	}
    	
    	//Iterate through the map now.
    	
    	
    	
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

    
    public class linkedList
    {
    	protected Node head;
    	protected int size;
    	
    	//Just for something different, I'm adding a vert string property to the linked list class, hopefully this will make things clearer.
    	protected String vert;
    	

    	public linkedList(String vert)
    	{
    		this.head = head;
    		this.vert = vert;
    		this.size = 0;
    	}
    }

    protected class Node {
    	
    	protected int weight;
    	
    	//These refer to verts.
    	protected String srcLabel;
    	protected String tarLabel;
    	
    	//The next edge on the vert. 
    	protected Node next;

    	
    	public Node(int weight, String srcLabel, String tarLabel) {
    		this.weight = weight;
    		this.srcLabel = srcLabel;
    		this.tarLabel = tarLabel;
    	}
    	
    	
    	
    	
    }
} // end of class AdjList
