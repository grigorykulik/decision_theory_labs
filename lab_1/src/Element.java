/**
 * This class represents elements of a matrix.
 * It stores element's value and indices.
 */

public class Element {
    private double value;
    private String row;
    private String column;

    public Element () {}

    public Element(Element e){
        this.row=e.row;
        this.column=e.column;
        this.value=e.value;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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