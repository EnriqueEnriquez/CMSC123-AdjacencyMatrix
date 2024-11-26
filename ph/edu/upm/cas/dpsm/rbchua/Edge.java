package ph.edu.upm.cas.dpsm.rbchua;

/**
 * Stores of the edge of class. 
 * 
 * Code skeleton from "Data Structures: Abstraction and Design Using Java, 2nd Edition"
 * by Elliot B. Koffman and Paul A.T. Wolfgang 
 * 
 * @author Richard Bryann Chua
 *
 */
public class Edge {
	private int dest;
	private int source;
	private double weight;
	
	/**
	 * Constructs an Edge from source to dest. 
	 * and weight is set to 1.0.
	 * 
	 * @param source source vertex
	 * @param dest destination vertex
	 */
	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
		weight = 1.0;
	}
	
	/**
	 * Constructs an Edge from source to dest
	 * with the given weight.
	 * 
	 * @param source source vertex
	 * @param dest destination vertex
	 * @param weight weight of this edge
	 */
	public Edge(int source, int dest, double weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	
	/**
	 * Compares two edges for equality. Edges are equal if their
	 * source and destination vertices are the same.
	 * The weight is not considered
	 * 
	 * @param o Other edge which is to be checked if it is equal to this edge
	 * @return true if this edge is the same as the other edge
	 */
	public boolean equals(Object o) {
		if (o instanceof Edge) {
			return (source == ((Edge) o).source) && (dest == ((Edge) o).dest);
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns the destination vertex
	 * @return destination vertex
	 */
	public int getDest() {
		return dest;
	}
	
	/**
	 * Returns the source vertex
	 * @return source vertex
	 */
	public int getSource() {
		return source;
	}
	
	/**
	 * Returns the weight of this edge
	 * @return weight of this edge
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns a string representation of the edge in the form
	 * (source, dest)	
	 * @return String representation of this edge
	 */
	public String toString() {
		return "(" + source + ", " + dest + ", w = " + weight + ")";
	}
}
