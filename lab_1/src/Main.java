import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Matrix mx=new Matrix("/home/greg/input.txt");
        DecisionMaker dm=new DecisionMaker(mx);

        /** Печатаем исходную матрицу */
        dm.printMatrix();

        /** Получаем оптимальное решение согласно минимаксному критерию */
        dm.getMiniMax();

        /**Получаем оптимальное решение согласно критерию Сэвиджа */
        dm.getSavage();

        /** Получаем оптимальное решение согласно критерию Гурвица */
        dm.getHurwicz();
    }
}
