import javax.xml.transform.Result;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

public class DecisionMaker {
    private final Matrix m;
    private ArrayList<Element> minimumsColumn=new ArrayList<Element>();

    public DecisionMaker(Matrix m) {
        this.m=m;
    }

    public Matrix getM() {
        return this.m;
    }

    /**
     * Iterate through each row of the matrix. Find the minimum in each row.
     * Create an array of obtained minimums.
     * Find the maximum value(s) in this array. This value
     * is the decision based on the minimax criterion.
     * **/
    public void getMiniMax() {

        for (int i=0; i<m.rows; i++) {
            minimumsColumn.add(getMinimumInARow(i));
        }

        //Find the maximum among minimums
        Element max=minimumsColumn
                .stream()
                .max(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        //Compare values in minimumsColumn with the obtained maximum and collect them into a list
        List<Element> result=minimumsColumn
                .stream()
                .filter(s->s.getValue()==max.getValue())
                .collect(toList());

        System.out.println("Decisions based on the Minimax criterion");

        for (int i = 0; i<result.size(); i++) {
            System.out.println(result.get(i).getRow()+result.get(i).getColumn());
        }
    }

    /** find the minimum in the given row **/
    public Element getMinimumInARow(int k) {
        ArrayList<Element> aux=new ArrayList<Element>();

        for (int j=0; j<m.columns; j++) {
            aux.add(m.mtrx[k][j]);
        }

        Element min=aux
                .stream()
                .min(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        return min;
    }

        public void getSavage() throws FileNotFoundException {

            /**
             * Create two matrices.
             * auxMatrixImmutable is used as a reference matrix. It will not mutate.
             * auxMatrixMutable is passed as an argument. It is going to mutate when we subtract maximums from
             * elements in corresponding columns
             **/

            Matrix auxMatrixMutable=new Matrix("/home/greg/input.txt");
            Matrix auxMatrixImmutable=new Matrix("/home/greg/input.txt");

            ArrayList<Element> maximumsColumn=new ArrayList<Element>();


            //Find the maximum value in each column and subtract each element in this colum from
            //this maximum value. Store the obtained values in auxMatrixMutable
            for (int j=0; j<auxMatrixImmutable.columns; j++) {
                for (int i = 0; i < auxMatrixImmutable.rows; i++) {
                    int newValue = this.getMaximumInAColumn(j, auxMatrixImmutable).getValue()
                            - auxMatrixImmutable.mtrx[i][j].getValue();
                    auxMatrixMutable.mtrx[i][j].setValue(newValue);
                }
            }

            //Find the maximum value in each column in the mutated matrix auxMatrixMutable
            //Add all these maximums to the ArrayList
            for (int j=0; j<auxMatrixImmutable.columns; j++) {
                maximumsColumn.add(getMaximumInAColumn(j, auxMatrixMutable));
            }

            //Get the minimum value from the ArrayList that contains maximums
            Element min=maximumsColumn
                .stream()
                .min(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

            //Compare the minimum value with other elements in the maximums ArrayList
            //Collect them into a list as there can be multiple solutions
            List<Element> result=maximumsColumn
                    .stream()
                    .filter(s->s.getValue()==min.getValue())
                    .collect(toList());

            System.out.println("Decisions based on the Savage criterion");
                for (int i = 0; i<result.size(); i++) {
                    System.out.println(result.get(i).getRow()+result.get(i).getColumn()+" "+result.get(i).getValue());
            }
    }

    // find the maximum in the given column
    public Element getMaximumInAColumn(int k, Matrix matrix) {
        ArrayList<Element> aux = new ArrayList<Element>();

        for (int i = 0; i < matrix.rows; i++) {
            aux.add(matrix.mtrx[i][k]);
        }

        Element max = aux
                .stream()
                .max(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        return max;
    }
}
