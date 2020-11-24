public class Matrix {
    final int rows=7;
    final int columns=7;
    double order=0;

    public Element[][] mtrx=new Element[rows][columns];

    public Matrix() {

        //Инициализируем элементы матрицы
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++) {
                mtrx[i][j]=new Element();
            }
        }

    }
}