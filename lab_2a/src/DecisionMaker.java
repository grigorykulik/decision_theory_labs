import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DecisionMaker {

    private Matrix m;

    public DecisionMaker(Matrix m) {
        this.m=m;
    }

    public Matrix getM() {
        return this.m;
    }

    //Метод, который выводит оригинальную матрицу
    public void printMatrix() {
        System.out.println("Исходная матрица");
        for (int i=0; i<this.getM().rows; i++) {
            System.out.print("Заказ: ");
            System.out.printf("%5.0f", this.getM().mtrx[i][0].getOrder());
            System.out.print("|");

            for (int j=0; j<this.getM().columns; j++) {
                System.out.printf("%5.0f", this.getM().mtrx[i][j].getValue());
            }
            System.out.println();
        }
    }

    //Перегружаем предыдущий метод
    public void printMatrix(Matrix m) {
        for (int i=0; i<7; i++) {
            System.out.print("Заказ: ");
            System.out.printf("%5.0f", m.mtrx[i][0].getOrder());
            System.out.print("|");
            for (int j=0; j<7; j++) {
                System.out.printf("%5.0f", m.mtrx[i][j].getValue());
            }
            System.out.println();
        }
    }

    //Метод, который выполняет всю вычислительную работу и выводит результаты
    public void getBL () {
        //Создаем новую матрицу, все элементы которой будем умножать на соответствующие коэффициенты
        Matrix auxMatrixMutable=new Matrix();

        //Массив для хранения математических ожиданий по каждой строке
        ArrayList<Element> mathExpColumn=new ArrayList<>();

        //Массив для хранения вероятностей
        ArrayList<Double> quotients=new ArrayList<>();
        quotients.add(0.35);
        quotients.add(0.21);
        quotients.add(0.19);
        quotients.add(0.09);
        quotients.add(0.09);
        quotients.add(0.06);
        quotients.add(0.01);

        //Умножаем все элементы матрицы на соответствующие вероятности
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                double newValue=m.mtrx[i][j].getValue()*
                        quotients.get(j);
                auxMatrixMutable.mtrx[i][j].setValue(newValue);
            }
        }

        System.out.println();
        System.out.println("Матрица решений с примененными коэффициентами");
        printMatrix(auxMatrixMutable);

        //Вызываем метод, который находит математическое ожидание, для каждой строки
        for (int i=0; i<5; i++) {
            mathExpColumn.add(getMathExpInRow(i, auxMatrixMutable));
        }

        //Находим максимальный элемент в массиве математических ожиданий по строкам
        Element maxMathExp = mathExpColumn
                .stream()
                .max(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        System.out.println();
        System.out.println("Математические ожидания:");

        for(Element e : mathExpColumn) {
            System.out.println("Размер заказа: "+e.getOrder() + ", математическое ожидание: "
            +e.getValue());
        }

        System.out.println();
        System.out.println("Максимальное математическое ожидание (оптимальный ожидаемый доход):" +
                maxMathExp.getValue() + " тыс. долл. США");
        System.out.println("Ему соответствует заказ в: " +
                maxMathExp.getOrder() + " автомобилей");
    }

    //Метод для нахождения математических ожиданий по строкам
    public Element getMathExpInRow(int k, Matrix matrix) {
        ArrayList<Element> aux = new ArrayList<>();

        for (int j = 0; j < matrix.rows; j++) {
            aux.add(matrix.mtrx[k][j]);
        }

        Double me = aux
                .stream()
                .map(x->x.getValue())
                .collect(Collectors.summingDouble(Double::doubleValue));

        Element mathExp=new Element();
        mathExp.setValue(me);
        mathExp.setOrder(matrix.mtrx[k][0].getOrder());

        return mathExp;
    }
}
