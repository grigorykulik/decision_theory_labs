import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DecisionMaker {
    private Matrix m;
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
        for (int i=0; i<m.rows; i++) {
            minimumsColumn.add(getMinimumInALine(i));
        }

        Integer max=minimumsColumn
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        return max;
    }

    /** find the minimum in the given line **/
    public Integer getMinimumInALine(int k) {
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
}
