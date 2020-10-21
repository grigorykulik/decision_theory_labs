public class Element {
    private double value;
    private double demand;
    private double order;
    private String row;
    private String column;

    public Element () {}

    /** Конструктор копий */
    public Element(Element e){
        this.row=e.row;
        this.column=e.column;
        this.value=e.value;
    }


    /** Геттеры и сеттеры */
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

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public double getOrder() {
        return order;
    }

    public void setOrder(double order) {
        this.order = order;
    }
}