import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Matrix mx=new Matrix("/home/greg/input.txt");
        System.out.println(Arrays.deepToString(mx.mtrx));

        DecisionMaker dm=new DecisionMaker(mx);
        int decisionMinimax=dm.getMiniMax();

        System.out.println("Decision based on the minimax criterion "+decisionMinimax);
    }
}
