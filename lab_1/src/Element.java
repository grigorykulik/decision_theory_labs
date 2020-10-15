/**
 * This class represents elements of a matrix.
 * It stores element's value and indices.
 */

public class Element {
    private int value;
    private String row;
    private String column;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRow() {
        return row;
    }

    public void setRow(int i) {
        this.row = "X"+i;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(int j) {
        this.column = "F"+j;
    }
}