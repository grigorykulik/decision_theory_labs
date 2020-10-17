import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Matrix mx=new Matrix("/home/greg/input.txt");
        DecisionMaker dm=new DecisionMaker(mx);

        dm.printMatrix();
        dm.getMiniMax();
        dm.getSavage();
        dm.getHurwicz();
    }
}
