import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//Object of this class is a decision matrix that the program reads from the given file

public class Matrix {
    final int rows=4;
    final int columns=5;
    ArrayList<String> xS=new ArrayList<String>();
    ArrayList<String> fS=new ArrayList<String>();

    public int[][] mtrx=new int[rows][columns];

    public Matrix(String filename) throws FileNotFoundException {
        //Read the matrix from the file
        try {
        Scanner sc=new Scanner(new BufferedReader(new FileReader(filename)));
            while (sc.hasNextLine()) {
                for (int i = 0; i < mtrx.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        mtrx[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }

            for (int i=0; i<this.rows; i++) {
                xS.add("X"+i);
            }

            for (int j=0; j<this.columns; j++) {
                fS.add("F"+j);
            }
        }

       catch (FileNotFoundException e) {
            System.out.println("File not found. Check that the file exists.");
            System.exit(0);
       }
    }

    public Matrix(Matrix m) {
        this.mtrx=m.mtrx;
    }
}
