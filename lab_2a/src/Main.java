public class Main {
    public static void main(String[] args) {
        //Создаем матрицы доходов за первый и второй год
        MatrixYearOne mx=new MatrixYearOne();
        MatrixYearTwo mx2=new MatrixYearTwo();

        //Создаем объект, который будет рассчитывать оптимальное решение
        //и выводить результат
        DecisionMaker dm=new DecisionMaker(mx, mx2);

        //Печатаем матрицу доходов за первый год
        dm.printMatrix(mx, "Доходы, год 1");
        System.out.println();

        //Печатаем матрицу доходов за второй год
        dm.printMatrix(mx2, "Доходы, год 2");

        //Получаем оптимальное решение и распечатываем результаты
        dm.getBL();
    }
}
