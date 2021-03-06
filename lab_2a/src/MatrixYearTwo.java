public class MatrixYearTwo extends Matrix {
    public MatrixYearTwo() {
        super();

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
                if (mtrx[i][j].getDemand() < mtrx[i][j].getOrder()) {
                    double profit=mtrx[i][j].getDemand()*10+(mtrx[i][j].getOrder()-
                            mtrx[i][j].getDemand()*1);
                    mtrx[i][j].setValue(profit);
                }

                else if (mtrx[i][j].getDemand() >= mtrx[i][j].getOrder()) {
                    double profit=mtrx[i][j].getOrder()*10;
                    mtrx[i][j].setValue(profit);
                }
            }
        }
    }
}
