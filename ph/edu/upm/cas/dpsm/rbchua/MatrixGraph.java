package ph.edu.upm.cas.dpsm.rbchua;

public class MatrixGraph extends AbstractGraph {
    /**
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */

    private double[][] matrix;

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        matrix = new double[numV][numV];

        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                matrix[i][j] = Double.POSITIVE_INFINITY; // No edge
            }
        }
    }

    @Override
    public void insert(Edge edge) {
        matrix[edge.getSource()][edge.getDest()] = edge.getWeight();

        if (!isDirected()) {
            matrix[edge.getDest()][edge.getSource()] = edge.getWeight();
        }

    }

    @Override
    public boolean isEdge(int source, int dest) {
        return matrix[source][dest] != Double.POSITIVE_INFINITY;
    }


    @Override
    public Edge getEdge(int source, int dest) {
        if (!isEdge(source, dest)) {
            return new Edge(source, dest, Double.POSITIVE_INFINITY);
        }
        return new Edge(source, dest, matrix[source][dest]);
    }

    @Override
    public String toString() {
        StringBuilder adjacencyMatrix = new StringBuilder();

        for(int sourceIndex = 0; sourceIndex < getNumV() ; sourceIndex++) {
            for(int destIndex = 0; destIndex < getNumV() ; destIndex++) {
                if (!isEdge(sourceIndex, destIndex)) {
                    adjacencyMatrix.append("0").append(" ");
                }
                else {
                    adjacencyMatrix.append( (int) matrix[sourceIndex][destIndex]).append(" ");
                }

            }
            adjacencyMatrix.append("\n");
        }

        return adjacencyMatrix.toString();
    }


}
