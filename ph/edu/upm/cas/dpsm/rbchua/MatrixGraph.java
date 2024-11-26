package ph.edu.upm.cas.dpsm.rbchua;

import java.util.*;

public class MatrixGraph extends AbstractGraph {
    private double[][] matrix;
    private HashMap<String, Integer> labelToIndex; // Maps node labels to internal indices
    private List<String> sortedLabels;            // Keeps node labels in sorted order
    private int currentIndex;                     // Tracks the next available internal index

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        matrix = new double[numV][numV];
        labelToIndex = new HashMap<>();
        sortedLabels = new ArrayList<>();
        currentIndex = 0;

        // Initialize the matrix with no edges
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                matrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Maps a node label to an internal index.
     * If the label is new, assigns it the next available index and updates sortedLabels.
     */
    private int getOrCreateIndex(String label) {
        if (!labelToIndex.containsKey(label)) {
            labelToIndex.put(label, currentIndex);
            sortedLabels.add(label);
            currentIndex++;

            // Ensure sortedLabels remains sorted
            sortedLabels.sort(Comparator.naturalOrder());
        }
        return labelToIndex.get(label);
    }

    @Override
    public void insert(Edge edge) {
        String sourceLabel = String.valueOf(edge.getSource());
        String destLabel = String.valueOf(edge.getDest());

        int sourceIndex = getOrCreateIndex(sourceLabel);
        int destIndex = getOrCreateIndex(destLabel);

        matrix[sourceIndex][destIndex] = edge.getWeight();

        if (!isDirected()) {
            matrix[destIndex][sourceIndex] = edge.getWeight();
        }
    }

    @Override
    public boolean isEdge(int source, int dest) {
        String sourceLabel = String.valueOf(source);
        String destLabel = String.valueOf(dest);

        int sourceIndex = labelToIndex.get(sourceLabel);
        int destIndex = labelToIndex.get(destLabel);

        return matrix[sourceIndex][destIndex] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        String sourceLabel = String.valueOf(source);
        String destLabel = String.valueOf(dest);

        int sourceIndex = labelToIndex.get(sourceLabel);
        int destIndex = labelToIndex.get(destLabel);

        if (!isEdge(source, dest)) {
            return new Edge(source, dest, Double.POSITIVE_INFINITY);
        }
        return new Edge(source, dest, matrix[sourceIndex][destIndex]);
    }

    @Override
    public String toString() {
        StringBuilder adjacencyMatrix = new StringBuilder();

        // Print header row
        adjacencyMatrix.append("    ");
        for (String label : sortedLabels) {
            adjacencyMatrix.append(label).append(" ");
        }
        adjacencyMatrix.append("\n");

        // Print rows
        for (String sourceLabel : sortedLabels) {
            adjacencyMatrix.append(sourceLabel).append(" ");
            int sourceIndex = labelToIndex.get(sourceLabel);

            for (String destLabel : sortedLabels) {
                int destIndex = labelToIndex.get(destLabel);

                if (matrix[sourceIndex][destIndex] == Double.POSITIVE_INFINITY) {
                    adjacencyMatrix.append("0 ");
                } else {
                    adjacencyMatrix.append((int) matrix[sourceIndex][destIndex]).append(" ");
                }
            }
            adjacencyMatrix.append("\n");
        }

        return adjacencyMatrix.toString();
    }
}
