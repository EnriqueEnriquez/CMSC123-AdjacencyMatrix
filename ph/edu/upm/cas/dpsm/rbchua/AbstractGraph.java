package ph.edu.upm.cas.dpsm.rbchua;

import java.util.*;

/**
 * Abstract base class for graphs. A graph is set 
 * of vertices and a set of edges. Vertices are
 * represented by integers from 0 to n - 1. Edges 
 * are ordered pairs of vertices
 * 
 * From "Data Structures: Abstraction and Design Using Java, 2nd Edition"
 * by Elliot B. Koffman and Paul A.T. Wolfgang 
 *
 */
public abstract class AbstractGraph implements Graph {
	/**
	 * The number of vertices
	 */
	private int numV;
	
	/**
	 * Flag to indicate whether this is a directed graph
	 */
	private boolean directed;
	
	/**
	 * Construct a graph with the specified number of vertices
	 * and the directed flag. If the directed flag is true, 
	 * this is a directed graph.
	 * @param numV The number of vertices
	 * @param directed The directed flag
	 */
	public AbstractGraph(int numV, boolean directed) {
		this.numV = numV;
		this.directed = directed;
	}
	
	/**
	 * Return the number of vertices
	 * @return The number of vertices
	 */
	public int getNumV() {
		return numV;
	}
	
	/**
	 * Return whether this is a directed graph.
	 * @return true if this is a directed graph
	 */
	public boolean isDirected() {
		return directed;
	}
	
	/**
	 * Load the edges of graph from the data in an input file.
	 * The file should contain a series of lines, each line
	 * with two or three data values. The first is the source,
	 * the second is the destination, and the optional third 
	 * is the weight.
	 * @param scan The Scanner connected to the data file
	 */
	public void loadEdgesFromFile(Scanner scan) {		
		scan.nextLine();		// Move the file pointer to the next line which is the first edge
		while(scan.hasNextLine()) {			
			StringTokenizer tokens = new StringTokenizer(scan.nextLine());			
			int source = Integer.parseInt(tokens.nextToken());
			int dest = Integer.parseInt(tokens.nextToken());		
			boolean weighted = tokens.hasMoreTokens() ? true : false;
			Edge e = null;
			if (weighted) {
				double weight = Double.parseDouble(tokens.nextToken());
				e = new Edge(source, dest, weight);
			}
			else {
				e = new Edge(source, dest);
			}
			insert(e);
		}
	}
	
	/**
	 * Static method to create a graph and load the data from an input
	 * file. The first line of the input file should contain the number
	 * of vertices. The remaining lines should contain the edge data as 
	 * described under loadEdgesFromFile.
	 * @param scan The scanner connected to the data file
	 * @param isDirected true if this is a directed graph, 
	 * 					 false, otherwise
	 * @param type The string "Matrix" if an adjacency matrix is to be
	 * 			   created, and the string "List" if an adjacency list is
	 * 			   to be created
	 * @return Representation of the graph with data from scan.
	 * @throws IllegalArgumentException if type is neither "Matrix" nor "List"
	 */
	public static Graph createGraph(Scanner scan, boolean isDirected, String type) 
									throws IllegalArgumentException {
		int numV = scan.nextInt();
		AbstractGraph graphRepresentation = null;
		if (type.equalsIgnoreCase("Matrix")) {
			graphRepresentation = new MatrixGraph(numV, isDirected);
		}
		else if (type.equalsIgnoreCase("List")) {
			// graphRepresentation = new ListGraph(numV, isDirected);
		}
		else {
			throw new IllegalArgumentException("type should either be 'Matrix' or 'List'");
		}
		graphRepresentation.loadEdgesFromFile(scan);
		return graphRepresentation;
	}
}
