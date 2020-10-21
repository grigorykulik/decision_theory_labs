import org.jetbrains.annotations.NotNull;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

public class DecisionMaker {
    private final Matrix m;
    private ArrayList<Element> minimumsColumn=new ArrayList<>();

    /**
     * конструктор копий
     */
    public DecisionMaker(Matrix m) {
        this.m=m;
    }

    /**
     * единственный геттер, который нужен нам в этом классе
     */
    public Matrix getM() {
        return this.m;
    }

    /**
     * печать исходной матрицы
     */
    public void printMatrix() {
        System.out.println("Original matrix");
        for (int i=0; i<this.getM().rows; i++) {
            for (int j=0; j<this.getM().columns; j++) {
                System.out.printf("%5.0f", this.getM().mtrx[i][j].getValue());
            }
            System.out.println();
        }
    }


    /**
     * Перебираем все строки матрицы. Находим минимум в каждой строке.
     * Из полученных минимумов создаем массив.
     * В этом массиве находим максимальный элемент.
     * Полученное значение будет оптимальным решением согласно минимаксному критерию.
     * **/
    public void getMiniMax() {

        for (int i=0; i<m.rows; i++) {
            minimumsColumn.add(getMinimumInARow(i));
        }

        /** Находим максимум среди минимумов */
        Element max=minimumsColumn
                .stream()
                .max(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        /**Сравниваем значения в массиве минимумов minimumsColumn с полученным ранее максимумом.
         * Полученные значения сохраняем в массив на тот случай, если таких значений несколько */
        List<Element> result=minimumsColumn
                .stream()
                .filter(s->s.getValue()==max.getValue())
                .collect(toList());

        System.out.println("Оптимальное решение согласно минимаксному критерию");

        for (int i = 0; i<result.size(); i++) {
            System.out.println(result.get(i).getRow()+result.get(i).getColumn() + " " + result.get(i).getValue());
        }
    }


    /** Метод для получения решения согласно критерию Сэвиджа */
    public void getSavage() throws FileNotFoundException {
        /**
         * Создаем две матрицы.
         * auxMatrixImmutable - эталонная матрица, которая не будет меняться.
         * auxMatrixMutable - матрица, которую будем передавать в качестве аргумента во
         * вспомогательные методы. Матрица будет мутировать, когда будем вычитать элементы
         * столбцов из соответствующих максимумов
         **/

            Matrix auxMatrixMutable=new Matrix("/home/greg/input.txt");
            Matrix auxMatrixImmutable=new Matrix("/home/greg/input.txt");

            ArrayList<Element> maximumsColumn=new ArrayList<>();


        /**
         * Находим максимум в каждом столбце и вычитаем каждый элемент этого столбца из этого максимума.
         * Все полученные элементы остаются на своих местах и записываются в массив auxMatrixMutable
         */
            for (int j=0; j<auxMatrixImmutable.columns; j++) {
                for (int i = 0; i < auxMatrixImmutable.rows; i++) {
                    double newValue = this.getMaximumInAColumn(j, auxMatrixImmutable).getValue()
                            - auxMatrixImmutable.mtrx[i][j].getValue();
                    auxMatrixMutable.mtrx[i][j].setValue(newValue);
                }
            }

            /**Находим максимум в каждом столбце новой матрицы auxMatrixMutable.
             * Сохраняем максимумы в массив.
             */
            for (int j=0; j<auxMatrixImmutable.columns; j++) {
                maximumsColumn.add(getMaximumInAColumn(j, auxMatrixMutable));
            }

        /**
         * Из массива максимумов получаем минимальный элемент.
         */
        Element min=maximumsColumn
                .stream()
                .min(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

            /**Сравниваем минимум со значениями остальных элементов в массиве максимумов.
             * Сохраняем элементы в массив на тот случай, если указанным условиям соответствуют несколько элементов.
             */
            List<Element> result=maximumsColumn
                    .stream()
                    .filter(s->s.getValue()==min.getValue())
                    .collect(toList());

            /** Выводим полученное решение: номер строки, номер столбца, полученное значение*/
            System.out.println("Оптимальное решение согласно критерию Сэвиджа");
                for (int i = 0; i<result.size(); i++) {
                    System.out.println(result.get(i).getRow()+result.get(i).getColumn()+" "+result.get(i).getValue());
            }
    }


    /**
     * Для каждой строки выполняем следующие действия: находим минимум в строке, умножаем его на данное
     * значение с, находим максимум, умножаем его на 1-c, суммируем полученные значения.
     * Выполнив эти действия для каждой строки, получаем массив взвешенных средних.
     * Находим максимальное значение среди взвешенных средних. Элемент с максимальным значением будет оптимальным
     * решением согласно критерию Гурвица.
     */
    public void getHurwicz() {
        ArrayList<Element> hwColumn = new ArrayList<>();

        for (int i = 0; i < m.rows; i++) {
            Element minimum = new Element(getMinimumInARow(i));
            Element maximum = new Element(getMaximumInARow(i));

            minimum.setValue(0.54 * getMinimumInARow(i).getValue());
            maximum.setValue(0.46 * getMaximumInARow(i).getValue());

            Element sum = new Element();

            sum.setValue(minimum.getValue() + maximum.getValue());
            sum.setRow(i);
            sum.setColumn(1);
            hwColumn.add(sum);
            }

        Element finalMax = hwColumn
                .stream()
                .max(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        List<Element> result = hwColumn
                .stream()
                .filter(s -> s.getValue() == finalMax.getValue())
                .collect(toList());

        System.out.println("Оптимальное решение согласно критерию Гурвица:");

        /** Печатаем полученное решение: номер строки и взвешенное среднее для нее. */
        for (int k = 0; k < result.size(); k++) {
            System.out.println(result.get(k).getRow() + " " + result.get(k).getValue());

        }
    }

    /** Метод для нахождения максимума в строке **/
    public Element getMaximumInARow(int k) {
        ArrayList<Element> aux=new ArrayList<>();

        for (int j=0; j<m.columns; j++) {
            aux.add(m.mtrx[k][j]);
        }

        Element max=aux
                .stream()
                .max(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        return max;
    }

    /** Метод для нахождения минимума в строке **/
    public Element getMinimumInARow(int k) {
        ArrayList<Element> aux=new ArrayList<>();

        for (int j=0; j<m.columns; j++) {
            aux.add(m.mtrx[k][j]);
        }

        Element min=aux
                .stream()
                .min(Comparator.comparing(Element::getValue))
                .orElseThrow(NoSuchElementException::new);

        return min;
    }

    /** Метод для нахождения максимума в столбце */
    public Element getMaximumInAColumn(int k, @NotNull Matrix matrix) {
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
