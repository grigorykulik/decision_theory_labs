public class Main {
    public static void main(String[] args) {
        //Создаем новую матрицу
        MatrixYearOne mx=new MatrixYearOne();
        MatrixYearTwo mx2=new MatrixYearTwo();

        //Создаем объект, который будет рассчитывать оптимальное решение
        //и выводить результат
        DecisionMaker dm=new DecisionMaker(mx, mx2);

        //Печатаем исходную матрицу
        dm.printMatrix(mx, "Доходы, год 1");
        System.out.println();
        dm.printMatrix(mx2, "Доходы, год 2");

        //Получаем оптимальное решение и распечатываем результаты
        dm.getBL();
    }
}
