import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DecisionMaker {
    private final Matrix m;
    private ArrayList<Integer> minimumsColumn=new ArrayList<Integer>();

    public DecisionMaker(Matrix m) {
        this.m=m;
    }

    public Matrix getM() {
        return this.m;
    }

    /**
     * Iterate through each row of the matrix. Find the minimum in each row.
     * Create an array whose elements are minimums obtained in the previous step.
     * Find the maximum value in this array. This value
     * is the decision based on the minimax criterion.
     * **/
    public int getMiniMax() {

        ArrayList<String> solutionRows=new ArrayList<String>();
        ArrayList<String> solutionColumns=new ArrayList<String>();

        for (int i=0; i<m.rows; i++) {
            minimumsColumn.add(getMinimumInARow(i));
        }

        Integer max=minimumsColumn
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        int numberOfSolutions=0;
        for (int i=0; i<m.rows; i++) {
            for (int j=0; j<m.columns; j++) {
                if (m.mtrx[i][j]==max) {
                    numberOfSolutions++;
                    solutionRows.add(m.xS.get(i));
                    solutionColumns.add(m.fS.get(j));
                }
            }
        }

        System.out.println("Decisions based on the Minimax criterion");

        for (int i=0; i<solutionRows.size(); i++) {
            System.out.println(solutionRows.get(i)+", "+solutionColumns.get(i));
        }

        return max;
    }

    /** find the minimum in the given line **/
    public Integer getMinimumInARow(int k) {
        ArrayList<Integer> aux=new ArrayList<>();

        for (int j=0; j<m.columns; j++) {
            aux.add(m.mtrx[k][j]);
        }

        Integer min=aux
                .stream()
                .mapToInt(v -> v)
                .min().orElseThrow(NoSuchElementException::new);

        return min;
    }

    public int getSavage() throws FileNotFoundException {
        Matrix auxMatrixMutable=new Matrix("/home/greg/input.txt");
        final Matrix auxMatrixImmutable=new Matrix("/home/greg/input.txt");

        ArrayList<Integer> maximumsColumn=new ArrayList<>();
        ArrayList<String> solutionRows=new ArrayList<String>();
        ArrayList<String> solutionColumns=new ArrayList<String>();

        int k=0;
        for (int j=0; j<auxMatrixImmutable.columns; j++) {
            for (int i=0; i<auxMatrixImmutable.rows; i++) {
                auxMatrixMutable.mtrx[i][j]=this.getMaximumInAColumn(k, auxMatrixImmutable)-auxMatrixImmutable.mtrx[i][j];
            }
            k++;
        }

        for (int j=0; j<auxMatrixImmutable.columns; j++) {
            maximumsColumn.add(getMaximumInAColumn(j, auxMatrixImmutable));
        }

        Integer min=maximumsColumn
                .stream()
                .mapToInt(v -> v)
                .min().orElseThrow(NoSuchElementException::new);

        int numberOfSolutions=0;
        for (int i=0; i<m.rows; i++) {
            for (int j=0; j<m.columns; j++) {
                if (auxMatrixMutable.mtrx[i][j]==min) {
                    numberOfSolutions++;
                    solutionRows.add(m.xS.get(i));
                    solutionColumns.add(m.fS.get(j));
                }
            }
        }

        System.out.println("Decisions based on the Savage criterion");

        for (int i=0; i<solutionRows.size(); i++) {
            System.out.println(solutionRows.get(i)+", "+solutionColumns.get(i));
        }

        return min;
    }

    // find the maximum in the given column
    public Integer getMaximumInAColumn(int k, Matrix matrix) {
        ArrayList<Integer> aux=new ArrayList<>();

        for (int i=0; i<matrix.rows; i++) {
            aux.add(matrix.mtrx[i][k]);
        }

        Integer max=aux
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        return max;
    }

}
