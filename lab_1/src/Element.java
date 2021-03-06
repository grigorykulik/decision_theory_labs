/**
 * Данный класс представляет собой элемент матрицы.
 * Объект класса хранит значение и индексы (строка и столбец).
 * Нумерация столбцов и строк начинается с 0.
 */

public class Element {
    private double value;
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
}