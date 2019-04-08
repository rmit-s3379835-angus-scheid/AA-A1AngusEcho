import java.io.*;
import java.util.*;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import java.lang.String;

/**
 * Framework to test the association graph implementations.
 *
 * There should be no need to change this for task A.  If you need to make changes for task B, please make a copy, then modify the copy for task B.
 *
 * @author Jeffrey Chan, 2019.
 */
public class GraphEval
{
	/** Name of class, used in error messages. */
	protected static final String progName = "GraphEval";

	/** Standard outstream. */
	protected static final PrintStream outStream = System.out;

	/**
	 * Print help/usage message.
	 */
	public static void usage(String progName) {
		System.err.println(progName + ": <implementation> [-f <filename to load graph>] [filename to print vertices] [filename to print edges] [filename to print neighbours]");
		System.err.println("<implementation> = <adjlist | incmat>");
		System.err.println("If all three optional output filenames are specified, then non-interative mode will be used and respective output is written to those files.  Otherwise interative mode is assumed and output is written to System.out.");
		System.exit(1);
	} // end of usage


	/**
	 * Process the operation commands coming from inReader, and updates the graph according to the operations.
	 *
	 * @param inReader Input reader where the operation commands are coming from.
	 * @param graph The graph which the operations are executed on.
	 * @param verticesOutWriter Where to output the results of printing the set of vertices.
	 * @param edgesOutWriter Where to output the results of printing the set of edges.
	 * @param neighbourOutWriter Where to output the results of finding the set of neighbours.
	 * @param miscOutWriter Where to output the results of other methods that has output and not covered by the other three output files.
	 *
	 * @throws IOException If there is an exception to do with I/O.
	 */
	public static void processOperations(BufferedReader inReader, AssociationGraph graph,
			PrintWriter verticesOutWriter, PrintWriter edgesOutWriter, PrintWriter neighbourOutWriter, PrintWriter miscOutWriter)
		throws IOException
	{
		String line;
		int lineNum = 1;
		boolean bQuit = false;

		// continue reading in commands until we either receive the quit signal or there are no more input commands
		while (!bQuit && (line = inReader.readLine()) != null) {
			String[] tokens = line.split(" ");

			// check if there is at least an operation command
			if (tokens.length < 1) {
				System.err.println(lineNum + ": not enough tokens.");
				lineNum++;
				continue;
			}

			String command = tokens[0];

			try {
				// determine which operation to execute
				switch (command.toUpperCase()) {
					// add vertex
					case "AV":
						if (tokens.length == 2) {
							graph.addVertex(tokens[1]);
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}
						break;
	                // add edge
					case "AE":
						if (tokens.length == 4) {
							int weight = Integer.parseInt(tokens[3]);
							if (weight < 0) {
								System.err.println(lineNum + ": edge weight must be non-negative.");
							}
							else {
								graph.addEdge(tokens[1], tokens[2], weight);
							}
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}
						break;
					// get edge weight
					case "W":
						if (tokens.length == 3) {
							int answer = graph.getEdgeWeight(tokens[1], tokens[2]);
							miscOutWriter.println(answer);
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}
						break;
					// update weight of edge
					case "U":
						if (tokens.length == 4) {
							int weight = Integer.parseInt(tokens[3]);
							if (weight < 0) {
								System.err.println(lineNum + ": edge weight must be non-negative.");
							}
							else {
								graph.updateWeightEdge(tokens[1], tokens[2], weight);
							}
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}
						break;
					// remove vertex
					case "RV":
						if (tokens.length == 2) {
							graph.removeVertex(tokens[1]);
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}
						break;
					// k-nearest out-neighbourhood
					case "ON":
						if (tokens.length == 3) {
							List<MyPair> neighbours = graph.outNearestNeighbours(Integer.parseInt(tokens[1]), tokens[2]);
							StringBuffer buf = new StringBuffer();
							for (MyPair neigh : neighbours) {
								buf.append(" (" + neigh.getKey() + "," + neigh.getValue() + ")");
							}

							neighbourOutWriter.println(tokens[2] + buf.toString());
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}

						break;
					// k-nearest in-neighbourhood
					case "IN":
						if (tokens.length == 3) {
							List<MyPair> neighbours = graph.inNearestNeighbours(Integer.parseInt(tokens[1]), tokens[2]);
							StringBuffer buf = new StringBuffer();
							for (MyPair neigh : neighbours) {
								buf.append(" (" + neigh.getKey() + "," + neigh.getValue() + ")");
							}

							neighbourOutWriter.println(tokens[2] + buf.toString());
						}
						else {
							System.err.println(lineNum + ": incorrect number of tokens.");
						}

						break;
					// print vertices
					case "PV":
						graph.printVertices(verticesOutWriter);
						break;
	                // print edges
					case "PE":
						graph.printEdges(edgesOutWriter);
						break;
					// quit
					case "Q":
						bQuit = true;
						break;
					default:
						System.err.println(lineNum + ": Unknown command.");
				} // end of switch()
			}
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}

			lineNum++;
		}

	} // end of processOperations()



	/**
	 * Main method.  Determines which implementation to test and processes command line arguments.
	 */
	public static void main(String[] args) {

		// parse command line options
		OptionParser parser = new OptionParser("f:");
		OptionSet options = parser.parse(args);

		String inputFilename = null;
		// -f <inputFilename> specifies the file that contains edge list information to construct the initial graph with.
		if (options.has("f")) {
			if (options.hasArgument("f")) {
				inputFilename = (String) options.valueOf("f");
			}
			else {
				System.err.println("Missing filename argument for -f option.");
				usage(progName);
			}
		}

		// non option arguments
		List<?> tempArgs = options.nonOptionArguments();
		List<String> remainArgs = new ArrayList<String>();
		for (Object object : tempArgs) {
			remainArgs.add((String) object);
		}

		// check number of non-option command line arguments
		if (remainArgs.size() > 5 || remainArgs.size() < 1) {
			System.err.println("Incorrect number of arguments.");
			usage(progName);
		}

		// parse non-option arguments
		String implementationType = remainArgs.get(0);

		String verticesOutFilename = null;
		String edgesOutFilename = null;
		String neighbourOutFilename = null;
		String miscOutFilename = null;

		// output files
		if (remainArgs.size() == 5) {
			verticesOutFilename = remainArgs.get(1);
			edgesOutFilename = remainArgs.get(2);
			neighbourOutFilename = remainArgs.get(3);
			miscOutFilename = remainArgs.get(4);
		}
		else {
			System.out.println("Interative mode.");
		}


		// determine which implementation to test
		AssociationGraph graph = null;
		switch(implementationType) {
			case "adjlist":
				graph = new AdjList();
				break;
			case "incmat":
				graph = new IncidenceMatrix();
				break;
			default:
				System.err.println("Unknown implmementation type.");
				usage(progName);
		}


		// if file specified, then load file
		if (inputFilename != null) {

			try {
				BufferedReader reader = new BufferedReader(new FileReader(inputFilename));

		    	String line;
		    	String delimiter = ",";
		    	String[] tokens;
		    	String srcLabel, tarLabel;
				int weight;

		    	while ((line = reader.readLine()) != null) {
		    		tokens = line.split(delimiter);
		    		srcLabel = tokens[0];
		    		tarLabel = tokens[1];
					weight = Integer.parseInt(tokens[2]);
		    		graph.addVertex(srcLabel);
		    		graph.addVertex(tarLabel);
		    		graph.addEdge(srcLabel, tarLabel, weight);
		    	}
			}
			catch (FileNotFoundException ex) {
				System.err.println("File " + args[1] + " not found.");
			}
			catch(IOException ex) {
				System.err.println("Cannot open file " + args[1]);
			}
		}

		// construct in and output streams/writers/readers, then process each operation.
		try {
			BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));

			// vertices output location
			PrintWriter verticesOutWriter = new PrintWriter(System.out, true);
			if (verticesOutFilename != null) {
				verticesOutWriter = new PrintWriter(new FileWriter(verticesOutFilename), true);
			}

			// edgs output location
			PrintWriter edgesOutWriter = new PrintWriter(System.out, true);
			if (edgesOutFilename != null) {
				edgesOutWriter = new PrintWriter(new FileWriter(edgesOutFilename), true);
			}

			// neighbourhood output location
			PrintWriter neighbourOutWriter = new PrintWriter(System.out, true);
			if (neighbourOutFilename != null) {
				neighbourOutWriter = new PrintWriter(new FileWriter(neighbourOutFilename), true);
			}

			// miscellaneous output location
			PrintWriter miscOutWriter = new PrintWriter(System.out, true);
			if (miscOutFilename != null) {
				miscOutWriter = new PrintWriter(new FileWriter(miscOutFilename), true);
			}


			// process the operations
			processOperations(inReader, graph, verticesOutWriter, edgesOutWriter, neighbourOutWriter, miscOutWriter);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	} // end of main()

} // end of class GraphEvak
