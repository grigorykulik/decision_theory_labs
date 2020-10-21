public class DecisionMaker {

    private Matrix m;

    public DecisionMaker(Matrix m) {
        this.m=m;
    }

    public Matrix getM() {
        return this.m;
    }

    public void printMatrix() {
        System.out.println("Original matrix");
        for (int i=0; i<this.getM().rows; i++) {
            for (int j=0; j<this.getM().columns; j++) {
                System.out.printf("%5.0f", this.getM().mtrx[i][j].getValue());
            }
            System.out.println();
        }
    }
}
