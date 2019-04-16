import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import graphImplementation.MyPair;
import graphImplementation.*;
//import AdjList.linkedList;

//import AdjList.linkedList;

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
	public Node[] heads = new Node[8];
	public int LLCount = 0;
	
	//Map to monitor linkedlist to vert locations.
	//Map<String, linkedList> vertListMap = new HashMap<String, linkedList>();
	
	Map<String, linkedList> vertListMap = new HashMap<>();
	
	
    /**
	 * Constructs empty graph.
	 */
    public AdjList() {
    	 // Implement me!
    	System.out.println("*Adj List Initialised*");
    	verts = new String[8];
    	
    	System.out.println("/AdjList \n \n");
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
    				vertListMap.values().toString();
    				break;
    		}
    	}
    	
    	//Just for debugging.
    	for(int i = 0; i < verts.length; i++)
    	{
    		System.out.println(verts[i]);
    	}
    	
    	System.out.println("/end of addVertex() \n \n");
    	
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
    		
    		if(verts[i] != null)
    		{
    		
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
    				//currentList.head = newNode;
    				
    				currentList.setHeadNode(newNode);
    				heads[LLCount] = newNode;
    				LLCount += 1;
    				currentList.size += 1;
    				System.out.println("Head printout: " + currentList.head.getWeight());
    			}
    			else
    			{
    				//Iterate here.
    				System.out.println("Adding to later in the list");
    				System.out.println("currentNode.next status: " + currentNode.next);
    				
    				
    				
    				//This entire part is being skipped! We need to do something!
    				
    				if (currentNode.next == null)
    				{
    					System.out.println("current.next == null");
    					currentNode.next = newNode;
    					//System.out.println("vertListMap.size A: " + vertListMap.size());
    					currentList.size++;
    				}
    				else
    				{
	    				while (currentNode.next != null)
	    				{
	    					System.out.println("Iterating to later in the LL");
	    					System.out.println("Iterating through LL: " + currentNode.srcLabel + ", " + currentNode.tarLabel + ", " + currentNode.weight);
	    					currentNode = currentNode.next;
	    					
	    					if (currentNode.next == null)
	        				{
	        					System.out.println("current.next == null");
	        					currentNode.next = newNode;
	        					//System.out.println("vertListMap.size A: " + vertListMap.size());
	        					currentList.size++;
	        					break;
	        				}
	    				}
    				}
    				
    			System.out.println("vertListMap.size B: " + vertListMap.size());
    			/*
    			for (vertListMap<String, Object> entry : vertListMap.entrySet())
    			{
    				System.out.println(entry.getKey() + ":" + entry.getValue().toString());
    			}*/
    			
    			break;
    				
    			}
    		}
    		}
    	}
    	
    	//The code doesn't go here?
    	
    	
    	vertListMap.values().toString();
    	
    	System.out.println("vertListMap.size: " + vertListMap.size());
    	
    	
    	System.out.println("End of addEdge debug: ");
		
    	forEachPrintout(vertListMap);
		
		System.out.println("/AddEdge \n \n");
        // Implement me!
    } // end of addEdge()


    public int getEdgeWeight(String srcLabel, String tarLabel) {
		    // Implement me!
	    	/*for(int i = 0; i < verts.length; i++)
	    	{
	    		System.out.println("Verts iteration");
	    		
	    		if(verts[i].equals(srcLabel))
	    		{
	    			//Go through the LL now.
	    			
	    			
	    			
	    			while (currentNode.next != null)
    				{
    					//System.out.println("Iterating to later in the LL");
    					//System.out.println("Iterating through LL: " + currentNode.srcLabel + ", " + currentNode.tarLabel + ", " + currentNode.weight);
    					//currentNode = currentNode.next;
    					//currentList.size += 1;
    				}
	    		}
	    	
	    	}*/
    		//for

		    // update return value
		    return EDGE_NOT_EXIST;
    } // end of existEdge()


    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
    	//It's simple dude! Just search for it!
    	for(int i = 0; i < verts.length; i++)
    	{
    		System.out.println("Verts iteration");
    		
    		if(verts[i].equals(srcLabel))
    		{
    			//Use a function to find the LL, which I will have to depend on the map for.
    			
    		}
    	
    	}
        // Implement me!
    } // end of updateWeightEdge()


    public void removeVertex(String vertLabel) {
        // Implement me!
    } // end of removeVertex()


    public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        
    	System.out.println("inNearestNeighbours()");
    	
    	List<MyPair> neighbours = new ArrayList<MyPair>();

        //An array for returning the "closest" verts. The verts go in here, and get sorted.
        //String[] kArray = new String[k];
        
        //Iterate through the whole thing, and "put" every 
        
        //LET THE RESTRUCTURING, BEGINNING!
    	int listIterator = 0;
        int headsIterator = 0;
    	//int listIterator = 0;
    	Node currentNode = null;
    	int neighboursCount = 0;
    	
    	//This just prints out the memory addresses of the linked lists, will work on getting the properties soon.
    	for (Entry<String, linkedList> entry: vertListMap.entrySet())
    	{
    		System.out.println("for (Entry<String, linkedList> entry: vertListMap.entrySet())");
    		
    		if (entry != null)
    		{
	    		
    			//This is just getting the linked list objects (not the heads)
    			//System.out.println("Key (vert) : " + entry.getKey() + "Value (LL MA): " + entry.getValue().toString() + " Head MA: " + entry.getValue().getHeadNode() );
	    		System.out.println("Key (vert) : " + entry.getKey() + ", Value (LL MA): " + entry.getValue().toString() + " List size: " + entry.getValue().getSize() +  ", Head MA: " + heads[headsIterator] );
	    		//currentNode = entry.getValue().getHeadNode();
	    		
	    		
	    		//Now searching the heads of the linked lists.
	    		
	    		if (heads[headsIterator] != null)
	    		{
	    			System.out.println("if (heads[headsIterator] != null)");
	    			System.out.println("Head's weight: " + heads[headsIterator].getWeight());
	    			currentNode = heads[headsIterator];
	    		}
	    		
	    		
	    		//System.out.println("Head's weight: " + 
	    		
	    		
	    		if (heads[headsIterator] != null)
	    		{
	    			System.out.println("if (heads[headsIterator] != null)");
	    			System.out.println("List iteration soon!");
		    		if(heads[headsIterator].next != null)
		    		{
		    			System.out.println("if(heads[headsIterator].next != null)");
		    			System.out.println("List iteration now!");
		    			
		    			//Iterating the nodes. The properties we want will be here.
		    			
		    			if (currentNode.next != null)
		    			{
		    				System.out.println("if (currentNode.next != null)");
		    			
			    			do 
			    			{
			    				
			    				if(neighboursCount == (k -1) )
			    				{
			    					break;
			    				}
			    				
			    				System.out.println(" do - while (currentNode.next != null && neighboursCount < k)");
			    				//System.out.println("neighboursCount: " + neighboursCount);
				    			System.out.println("List " + heads[headsIterator] + " iteration: " + listIterator );
				    			System.out.println("Inner node info:");
				    			System.out.println("Node number: " + listIterator + " srcValue: " +  currentNode.getSrcLabel() + " tarLabel: " + currentNode.getTarLabel() + " Weight: " + currentNode.getWeight());
				    			
				    			
				    			//Add the desired node to the arrayList. The vert value is "gotten" from the LL, weight from the node.
				    			//while (neighboursCount < k)
				    			//{
				    				if ( currentNode != null && vertLabel.equals(currentNode.getTarLabel()))
						    			{
				    						System.out.println("if ( currentNode != null &&  vertLabel.equals(currentNode.getTarLabel()))");
						    				neighbours.add( new MyPair(entry.getValue().getVert(),currentNode.getWeight()) );
						    				System.out.println("Successfully added to neighbours!");
						    				//neighboursCount++;
						    				
						    				/*
						    				if (currentNode == null)
						    				{
						    					System.out.println("	if (currentNode == null)");
						    					break;
						    				}*/
						    				
						    				//currentNode = currentNode.next;
						    			}
				    				
				    			//}
				    			
				    			System.out.println("***Neighbours print should happen here:");
				    			//Arraylist print for debug purposes:
				    			for (int i = 0; i < neighbours.size(); i++)
				    			{
				    				System.out.println("neighbours print: " + i + " Vert Vaue: " + neighbours.get(i).getKey() + " Weight value: " + neighbours.get(i).getValue());
				    			}
			    			
			    			
			    			//System.out.println("neighbours print: " +   );
			    			
				    		System.out.println("currentNode.next's weight: " + currentNode.next.getWeight());
				    			
				    		currentNode = currentNode.next;
			    			
			    			listIterator++;
			    			
			    			
			    			
			    			/*
			    			if(currentNode == heads[headsIterator])
			    			{
			    				currentNode = currentNode.next;
			    			}*/
			    			neighboursCount++;
			    			
		    			} while (currentNode.getNext() != null);// || neighboursCount < k))
		    			System.out.println("////while (currentNode.next != null || neighboursCount < k)");
		    			
		    			System.out.println("After the While loop");
		    			
		    			System.out.println("Node number: " + listIterator + " srcValue: " +  currentNode.getSrcLabel() + "tarLabel: " + currentNode.getTarLabel() + " Weight: " + currentNode.getWeight());
		    			neighbours.add( new MyPair(entry.getValue().getVert(),currentNode.getWeight()) );
		    			
		    			//Remove unwanted verts.
		    			/*
		    			for (int i = 0; i <= neighbours.size(); i++)
		    			{
		    				neighbours.removeIf(neighbours.get(i).getKey() != currentNode.getTarLabel());
		    			}*/
		    			//neighbours.removeIf(i -> (i.getKey() != .getTarLabel()));
		    			
		    			
		    			
		    			//System.out.println("Current node after printing all previous: number: " + listIterator + " src: " + currentNode.getSrcLabel() + " tar: " + currentNode.getTarLabel() + " weight: " + currentNode.getWeight() );
		    			
		    		}
		    		else
		    		{
		    			/*
		    			for (int i = 0; i <= neighbours.size(); i++)
		    			{
		    				if (neighbours.get(i).getKey() != heads[headsIterator].getSrcLabel())
		    				{
		    					neighbours.remove(i);
		    				}
		    			}*/
		    			
		    			break;
		    			//System.out.println("heads Array MA: " + heads[headsIterator] + " head getValue() MA: " + entry.getValue().getHeadNode());
		    			//System.out.println("Head's next: " + heads[headsIterator].next);
		    		}
	    		}
		    		
	    			
	    			
	    			//This is probably not correct, pay attention to this.
	    			
	    			
	    			((ArrayList<MyPair>) neighbours).trimToSize();
		    		
		    		
		    		
	    		headsIterator += 1;
	    		}	
    		}
    		
    		//Detect if there's more entries in the list for the vert we're on.
    		
    		
    		/*
    		if(heads[headsIterator + 1] != null)
    		{
    			headsIterator++;
    		}
    		*/
    	}
        
        
    			
    			
    			
    			
    			
    	//neighbours.sort(MyPair.getValue());
    	Collections.sort(neighbours, new weightSort());
    			
    			
    	System.out.println("Sorted print:");
    	
    	for (int i = 0; i <neighbours.size(); i++)
    	{
    		System.out.println(neighbours.get(i));
    	}
    	
    			
    			
        // Implement me!

    	System.out.println("//inNearestNeighbours() \n \n");
    
        return neighbours;
    } // end of inNearestNeighbours()

    public class weightSort implements Comparator<MyPair>
    {
    	public int compare(MyPair a, MyPair b)
    	{
    		return a.getValue() - b.getValue();
    	}
    }

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        return neighbours;
    } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os)
    {
    	System.out.println("printVertices()");
    	//Actual printwriting functionality can come later.
    	for(int i = 0; i < verts.length; i++)
    	{
    		System.out.print(verts[i] + "    ");
    	}
    	
    	
    	
    	
    	
        // Implement me!
    } // end of printVertices()


    public void printEdges(PrintWriter os)
    {
    	
        // Implement me!
    } // end of printEdges()

    
    public class linkedList
    {
    	public Node head;
    	public int size;
    	
    	//Just for something different, I'm adding a vert string property to the linked list class, hopefully this will make things clearer.
    	protected String vert;
    	

    	public linkedList(String vert)
    	{
    		//this.head = head;
    		this.vert = vert;
    		this.size = 0;
    	}
    	
    	public Node getHeadNode()
    	{
    		return this.head;
    	}
    	
    	public void setHeadNode(Node head)
    	{
    		this.head = head;
    	}
    	
    	public int getSize()
    	{
    		return this.size;
    	}
    	
    	public String getVert()
    	{
    		return this.vert;
    	}
    	
    	//Size has to be set by setters! Dummy!
    }
    
    public void forEachPrintout(Map<String, linkedList> vertListMap)
    {
    	System.out.println("forEachPrintout()");
    	
    	//<String, linkedList>
    	//vertListMap.forEach((key, value) -> System.out.println("vertListMap printout: " + key + ":" + value.getSize() + " Head's edge weight: " + value.getHeadNode().getWeight() + " Head's srcVert: " + value.getHeadNode().getSrcLabel() + " Head's tarVert: " + value.getHeadNode().getTarLabel()));
    	//vertListMap.forEach((key, value) -> System.out.println("vertListMap printout: " + key + ":" + value.getSize() + " Head's edge weight: " + value.getHeadNode().getWeight() + " Head's srcVert: " + value.getHeadNode().getSrcLabel() + " Head's tarVert: " + value.getHeadNode().getTarLabel()));
    	int headsIterator = 0;
    	//int listIterator = 0;
    	Node currentNode = null;
    	
    	//This just prints out the memory addresses of the linked lists, will work on getting the properties soon.
    	for (Entry<String, linkedList> entry: vertListMap.entrySet())
    	{
    		int listIterator = 0;
    		if (entry != null)
    		{
	    		//System.out.println("Key (vert) : " + entry.getKey() + "Value (LL MA): " + entry.getValue().toString() + " Head MA: " + entry.getValue().getHeadNode() );
	    		System.out.println("Key (vert) : " + entry.getKey() + ", Value (LL MA): " + entry.getValue().toString() + " List size: " + entry.getValue().getSize() +  ", Head MA: " + heads[headsIterator] );
	    		//currentNode = entry.getValue().getHeadNode();
	    		
	    		
	    		
	    		if (heads[headsIterator] != null)
	    		{
	    			System.out.println("Head's weight: " + heads[headsIterator].getWeight());
	    			currentNode = heads[headsIterator];
	    		}
	    		
	    		
	    		//System.out.println("Head's weight: " + 
	    		
	    		
	    		if (heads[headsIterator] != null)
	    		{
	    			System.out.println("List iteration soon!");
		    		if(heads[headsIterator].next != null)
		    		{
		    			System.out.println("List iteration now!");
		    			
		    			if (currentNode.next != null)
		    			{
		    			
		    			while (currentNode.next != null)
		    			{
			    			System.out.println("List " + heads[headsIterator] + " iteration: " + listIterator );
			    			System.out.println("Inner node info:");
			    			System.out.println("Node number: " + listIterator + " srcValue: " +  currentNode.getSrcLabel() + "tarLabel: " + currentNode.getTarLabel() + " Weight: " + currentNode.getWeight());
			    			currentNode = currentNode.next;
			    			listIterator++;
		    			}
		    			
		    			System.out.println("Current node after printing all previous: number: " + listIterator + " src: " + currentNode.getSrcLabel() + " tar: " + currentNode.getTarLabel() + " weight: " + currentNode.getWeight() + " next: " + currentNode.getNext() );
		    			
		    		}
		    		else
		    		{
		    			System.out.println("heads Array MA: " + heads[headsIterator] + " head getValue() MA: " + entry.getValue().getHeadNode());
		    			System.out.println("Head's next: " + heads[headsIterator].next);
		    		}
	    		}
	    		headsIterator += 1;
	    		}	
    		}
    		
    		//Detect if there's more entries in the list for the vert we're on.
    		
    		
    		/*
    		if(heads[headsIterator + 1] != null)
    		{
    			headsIterator++;
    		}
    		*/
    	}
    	System.out.println("/forEachPrintout() \n \n");
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
    	
    	public int getWeight()
    	{
    		return this.weight;
    	}
    	
    	public String getSrcLabel()
    	{
    		return this.srcLabel;
    	}
    	
    	public String getTarLabel()
    	{
    		return this.tarLabel;
    	}
    	
    	public Node getNext()
    	{
    		return this.next;
    	}
    }

	@Override
	public void removeEdge(String srcLabel, String tarLabel) {
		// TODO Auto-generated method stub
		
	}
} // end of class AdjList
