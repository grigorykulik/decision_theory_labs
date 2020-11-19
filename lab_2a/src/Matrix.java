// Автоматически создаваемая матрица доходов
public class Matrix {
    final int rows=5;
    final int columns=5;
    double order=100;

    public Element[][] mtrx=new Element[rows][columns];

    public Matrix() {

        //Инициализируем элементы матрицы
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++) {
                mtrx[i][j]=new Element();
            }
        }

        //Устанавливаем размер спроса и заказа, соответствующие каждому элементу
        for (int i=0; i<5; i++) {
            double demand=100;

            for (int j=0; j<5; j++) {
                mtrx[i][j].setDemand(demand);
                mtrx[i][j].setOrder(order);
                demand+=50;
            }
            order+=50;
        }

        //Заполняем матрицу доходов по алгоритму, показанному на занятии
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (mtrx[i][j].getDemand() == mtrx[i][j].getOrder()) {
                    double profit=mtrx[i][j].getDemand()*49-25*mtrx[i][j].getOrder();
                    mtrx[i][j].setValue(profit);
                }

                else if (mtrx[i][j].getDemand() < mtrx[i][j].getOrder()) {
                    double diff=mtrx[i][j].getOrder()-mtrx[i][j].getDemand();
                    double profit=mtrx[i][j].getDemand()*49-25*mtrx[i][j].getOrder()+15*diff;
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