// Автоматически создаваемая матрица доходов
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

        //Устанавливаем размер спроса и заказа, соответствующие каждому элементу
        for (int i=0; i<7; i++) {
            double demand=0;

            for (int j=0; j<7; j++) {
                mtrx[i][j].setDemand(demand);
                mtrx[i][j].setOrder(order);
                demand+=50;
            }
            order+=50;
        }

        //Заполняем матрицу доходов по алгоритму, показанному на занятии
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                if (mtrx[i][j].getDemand() == mtrx[i][j].getOrder()) {
                    double profit=mtrx[i][j].getDemand()*25-15*mtrx[i][j].getOrder();
                    mtrx[i][j].setValue(profit);
                }

                else if (mtrx[i][j].getDemand() < mtrx[i][j].getOrder()) {
                    double profit=mtrx[i][j].getDemand()*25-15*mtrx[i][j].getOrder();
                    mtrx[i][j].setValue(profit);
                }

                else {
                    double profit=mtrx[i][j-1].getValue();
                    mtrx[i][j].setValue(profit);
                }
            }
        }

    }
}