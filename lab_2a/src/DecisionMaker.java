import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DecisionMaker {

    private MatrixYearOne m;
    private MatrixYearTwo m2;

    public DecisionMaker(MatrixYearOne m, MatrixYearTwo m2) {
        this.m=m;
        this.m2=m2;
    }

    public Matrix getM() {
        return this.m;
    }

    //Метод, печатающий матрицу. Принимает на вход матрицу и сообщение
    public void printMatrix(Matrix m, String message) {
        System.out.println(message);

        for (int i=0; i<7; i++) {
            System.out.print("Заказ: ");
            System.out.printf("%10.1f", m.mtrx[i][0].getOrder());
            System.out.print("|");
            for (int j=0; j<7; j++) {
                System.out.printf("%10.1f", m.mtrx[i][j].getValue());
            }
            System.out.println();
        }
    }

    //Метод, который выполняет всю вычислительную работу и выводит результаты
    public void getBL () {
        //Создаем новую матрицу, все элементы которой будем умножать на соответствующие коэффициенты
        Matrix auxMatrixMutableYearOne=new Matrix();
        Matrix auxMatrixMutableYearTwo=new Matrix();

        //Массив для хранения математических ожиданий по каждой строке
        ArrayList<Element> mathExpColumn=new ArrayList<>();

        //Массив для хранения вероятностей
        ArrayList<Double> quotientsYearOne=new ArrayList<>();
        quotientsYearOne.add(0.35);
        quotientsYearOne.add(0.21);
        quotientsYearOne.add(0.19);
        quotientsYearOne.add(0.09);
        quotientsYearOne.add(0.09);
        quotientsYearOne.add(0.06);
        quotientsYearOne.add(0.01);

        ArrayList<Double> quotientsYearTwo=new ArrayList<>();
        quotientsYearTwo.add(0.01);
        quotientsYearTwo.add(0.02);
        quotientsYearTwo.add(0.2);
        quotientsYearTwo.add(0.2);
        quotientsYearTwo.add(0.07);
        quotientsYearTwo.add(0.15);
        quotientsYearTwo.add(0.35);

        //Умножаем все элементы матрицы за 1-й b 2-й год на соответствующие вероятности
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                double newValue=m.mtrx[i][j].getValue()*
                        quotientsYearOne.get(j);
                auxMatrixMutableYearOne.mtrx[i][j].setValue(newValue);

                double newValue2=m2.mtrx[i][j].getValue()*
                        quotientsYearTwo.get(j);
                auxMatrixMutableYearTwo.mtrx[i][j].setValue(newValue2);
            }
        }

        System.out.println();
        printMatrix(auxMatrixMutableYearOne, "Матрица доходов за 1-й год с примененными коэффициентами");
        System.out.println();
        printMatrix(auxMatrixMutableYearTwo, "Матрица доходов за 2-й год с примененными коэффициентами");

        //Вызываем метод, который находит математическое ожидание, для каждой строки
        for (int i=0; i<5; i++) {
            mathExpColumn.add(getMathExpInRow(i, auxMatrixMutableYearOne));
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
