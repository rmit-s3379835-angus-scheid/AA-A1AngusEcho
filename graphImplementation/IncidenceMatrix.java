package graphImplementation;

import java.io.PrintWriter;
import java.util.*;

/**
 * Incident matrix implementation for the graphImpl.AssociationGraph interface.
 * <p>
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class IncidenceMatrix extends AbstractAssocGraph {

	private List<String> vertexesList;
	private Set<String> vertexesSet;
	private int edgesNum;
	private int[][] incMatrix;
	private int incSize = incMatrix[0].length;
	private int size = incMatrix.length;

	/**
	 * Constructs empty graph.
	 */

	// create an incidence matrix
	public IncidenceMatrix() {
		vertexesList = new ArrayList<>();
		vertexesSet = new HashSet<>();
		edgesNum = 0;
		incMatrix = new int[vertexesList.size() + 1][edgesNum + 1];
	} // end of IncidentMatrix()

	// expand the row
	private void expandMatrixRow() {
		if (vertexesList.size() > 0.5 * size) {
			size = size * 2;
			int[][] newIncMatrix = new int[size][incSize];
			int i = 0;
			while (i < vertexesList.size()) {
				i++;
				newIncMatrix[i] = Arrays.copyOf(incMatrix[i], incSize);
			}
			this.incMatrix = newIncMatrix;
		}
	}// end of expandMatrixRow()

	// expand the column
	private void expandMatrixCol() {
		if (edgesNum > 0.5 * incSize) {
			incSize *= 2;
			int[][] newIncMatrix = new int[size][incSize];
			int i = 0;
			while (i < vertexesList.size()) {
				i++;
				newIncMatrix[i] = Arrays.copyOf(incMatrix[i], incSize);
			}
			this.incMatrix = newIncMatrix;
		}
	}// end of expandMatrixCol()

	// resize the incidence matrix
	private void shrinkMatrixSize() {
		incSize = (int) (0.5 * incSize);
		if (edgesNum < 0.5 * incSize) {

			int[][] newIncMatrix = new int[size][incSize];
			int i = 0;
			boolean smaller = i < size;
			while (smaller) {
				i++;
				newIncMatrix[i] = Arrays.copyOf(incMatrix[i], edgesNum);
			}
			this.incMatrix = newIncMatrix;
		}
		int vSize = vertexesList.size();
		if (vSize < 0.5 * size) {
			size = (int) (0.5 * size);
			int[][] newIncMatrix = new int[size][edgesNum];
			int i = 0;
			while (i < vertexesList.size()) {
				i++;
				newIncMatrix[i] = Arrays.copyOf(incMatrix[i], edgesNum);
			}
			this.incMatrix = newIncMatrix;
		}

	}// end of shrinkMatrixSize()

	// check connected edge
	private int isIncidence(int srcLabelIndex, int tarLabelIndex) {
		int connected = -1;
		int i = 0;
		int srcWeightJ = incMatrix[srcLabelIndex][i];
		int tarWeightJ = incMatrix[tarLabelIndex][i];
		while (i < edgesNum) {
			i++;

			boolean src = srcWeightJ != 0;
			boolean tar = tarWeightJ != 0;
			if (src && tar && (srcWeightJ + tarWeightJ == 0)) {
				connected = i;
				break;
			}
		}
		return connected;
	}// end of isIncidence()

	// add vertex(row)
	public void addVertex(String vertLabel) {
		if (!vertexesSet.contains(vertLabel)) {
			vertexesList.add(vertLabel);
			vertexesSet.add(vertLabel);
			expandMatrixRow();
		}
	} // end of addVertex()

	// add edge
	public void addEdge(String srcLabel, String tarLabel, int weight) {
		boolean equal = srcLabel.equals(tarLabel);
		boolean vsContainSrc = vertexesSet.contains(srcLabel);
		boolean vsContainTar = vertexesSet.contains(tarLabel);
		int srcLabelIndex = vertexesList.indexOf(srcLabel);
		int tarLabelIndex = vertexesList.indexOf(tarLabel);

		if (!equal && vsContainSrc && vsContainTar) {
			if (isIncidence(srcLabelIndex, tarLabelIndex) == -1) {
				edgesNum++;
				expandMatrixCol();
				incMatrix[srcLabelIndex][edgesNum - 1] = weight;
				incMatrix[tarLabelIndex][edgesNum - 1] = -1 * weight;
			}
		} else {
			System.out.println(srcLabel + "," + tarLabel + "," + weight);
			throw new IllegalArgumentException("Vertex does not exist.");
		}
	} // end of addEdge()

	// get the weight of edge
	public int getEdgeWeight(String srcLabel, String tarLabel) {
		boolean equal = srcLabel.equals(tarLabel);
		boolean vsContainSrc = vertexesSet.contains(srcLabel);
		boolean vsContainTar = vertexesSet.contains(tarLabel);
		int srcLabelIndex = vertexesList.indexOf(srcLabel);
		int tarLabelIndex = vertexesList.indexOf(tarLabel);
		boolean col = (isIncidence(srcLabelIndex, tarLabelIndex)) > 0;

		if (!equal && vsContainSrc && vsContainTar) {
			if (col)
				return incMatrix[srcLabelIndex][isIncidence(srcLabelIndex, tarLabelIndex)];
		}
		return EDGE_NOT_EXIST;
	} // end of existEdge()

	// update the weight of edge
	public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
		int srcLabelIndex = vertexesList.indexOf(srcLabel);
		int tarLabelIndex = vertexesList.indexOf(tarLabel);
		int colInd = isIncidence(srcLabelIndex, tarLabelIndex);
		boolean equal = srcLabel.equals(tarLabel);
		boolean vsContainSrc = vertexesSet.contains(srcLabel);
		boolean vsContainTar = vertexesSet.contains(tarLabel);

		if (!equal && vsContainSrc && vsContainTar) {
			if (weight == 0) {
				removeEdge(srcLabel, tarLabel);
			} else {
				incMatrix[srcLabelIndex][colInd] = weight;
				incMatrix[tarLabelIndex][colInd] = -1 * weight;
			}
		}
	} // end of updateWeightEdge()

	// remove a vertex
	public void removeVertex(String vertLabel) {
		int removeAtIndex = vertexesList.indexOf(vertLabel);
		boolean vlContains = vertexesList.contains(vertLabel);

		if (vlContains) {
			int j = 0;
			boolean enSmaller = j < edgesNum;
			while (enSmaller) {
				boolean not = (incMatrix[removeAtIndex][j] != 0);
				if (not) {
					int i = 0;
					boolean vlSmaller = i < vertexesList.size();
					while (vlSmaller) {
						i++;
						System.arraycopy(incMatrix[i], j + 1, incMatrix[i], j, edgesNum - j - 1);
					}
					edgesNum--;
				} else {
					j++;
				}
			}

			System.arraycopy(incMatrix, removeAtIndex + 1, incMatrix, removeAtIndex,
					vertexesList.size() - removeAtIndex - 1);
			vertexesList.remove(removeAtIndex);
			vertexesSet.remove(vertLabel);
			shrinkMatrixSize();
		}
	} // end of removeVertex()

	// remove edge
	public void removeEdge(String srcLabel, String tarLabel) {
		boolean vsContainSrc = vertexesSet.contains(srcLabel);
		boolean vsContainTar = vertexesSet.contains(tarLabel);
		int srcLabelIndex = vertexesList.indexOf(srcLabel);
		int tarLabelIndex = vertexesList.indexOf(tarLabel);
		int colIndex = isIncidence(srcLabelIndex, tarLabelIndex);

		if (vsContainSrc && vsContainTar) {
			int i = 0;
			boolean vlSmaller = i < vertexesList.size();
			while (vlSmaller) {
				i++;
				System.arraycopy(incMatrix[i], colIndex + 1, incMatrix[i], colIndex, edgesNum - colIndex - 1);
			}
			edgesNum--;
		}
	}

	// print the vertices
	// PrintWriter
	public void printVertices(PrintWriter os) {
		Iterator<String> it = vertexesList.iterator();
		String vertice;
		boolean hasNext = it.hasNext();

		if (hasNext) {
			vertice = it.next();
			os.print(vertice);
		}
		while (hasNext) {
			vertice = it.next();
			os.print(" " + vertice);
		}
		os.println();
//        os.flush(); os.close();
	} // end of printVertices()

	// print the edges
	public void printEdges(PrintWriter os) {
		int i = 0;
		boolean eSmaller = i < edgesNum;
		while (eSmaller) {
			i++;
			int weight = 0;
			String from = "";
			String to = "";

			int j = 0;
			boolean vlSmaller = j < vertexesList.size();
			while (vlSmaller) {
				j++;

				int tmpWeight = incMatrix[j][i];
				boolean twLarger = tmpWeight > 0;
				boolean twSmaller = tmpWeight < 0;

				if (twLarger) {
					from = vertexesList.get(j);
					weight = tmpWeight;
				} else if (twSmaller) {
					to = vertexesList.get(j);
				}
			}
			boolean wLarget = weight > 0;

			if (wLarget)
				os.println(from + " " + to + " " + weight);
		}
		os.println();
//        os.flush(); os.close();
	} // end of printEdges()

	// check existence of vertex
	public boolean containsVertex(String vertexLabel) {

		return this.vertexesSet.contains(vertexLabel);

	}

	// NearestNeighbours In
	public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
		List<MyPair> neighbours = new ArrayList<>();
		boolean vlContains = vertexesList.contains(vertLabel);
		int vertInd = vertexesList.indexOf(vertLabel);

		if (vlContains) {
			int j = 0;
			boolean enSmaller = j < edgesNum;
			int inWeight = incMatrix[vertInd][j];

			while (enSmaller) {
				boolean outerIf = inWeight < 0;

				if (outerIf) {
					int i = 0;
					while (i < vertexesList.size()) {
						i++;
						boolean innerIf = incMatrix[i][j] > 0;

						if (innerIf) {
							neighbours.add(new MyPair(vertexesList.get(i), incMatrix[i][j]));
						}
					}
				}
				j++;
			}
		}

		boolean equal = k == -1;
		boolean neigh = k > neighbours.size();

		if (equal || neigh) {
			return neighbours;
		} else {
			Collections.sort(neighbours);
			return neighbours.subList(0, k);
		}
	} // end of inNearestNeighbours()

	// NearestNeighbours Out
	public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
		List<MyPair> neighbours = new ArrayList<>();
		boolean vlContains = vertexesList.contains(vertLabel);
		int vertInd = vertexesList.indexOf(vertLabel);

		if (vlContains) {
			int j = 0;
			boolean enSmaller = j < edgesNum;
			int outWeight = incMatrix[vertInd][j];

			while (enSmaller) {
				boolean owLarger = outWeight > 0;

				if (owLarger) {
					int i = 0;
					boolean vlSmaller = i < vertexesList.size();

					while (vlSmaller) {
						i++;
						boolean negative = incMatrix[i][j] < 0;

						if (negative) {
							neighbours.add(new MyPair(vertexesList.get(i), outWeight));
						}
					}
				}
				j++;
			}
		}

		Collections.sort(neighbours);
		boolean equal = k == -1;
		boolean neigh = k > neighbours.size();

		if (equal || neigh) {
			return neighbours;
		} else {
			return neighbours.subList(0, k);
		}
	} // end of outNearestNeighbours()

} // end of class graphImplementation.IncidenceMatrix
