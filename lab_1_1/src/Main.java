public class Main {
    public static void main(String[] args) {
        //Создаем новую матрицу
        Matrix mx=new Matrix();

        //Создаем объект, который будет рассчитывать оптимальное решение
        //и выводить результат
        DecisionMaker dm=new DecisionMaker(mx);

        //Печатаем исходную матрицу
        dm.printMatrix();

        //Получаем оптимальное решение и распечатываем результаты
        dm.getBL();
    }
}
