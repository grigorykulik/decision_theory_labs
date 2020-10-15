import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//Object of this class is the matrix that the program reads from the given file
public class Matrix {
    final int rows=4;
    final int columns=5;

    public Element[][] mtrx=new Element[rows][columns];

    public Matrix(String filename) throws FileNotFoundException {

        //Initialize elements in mtrx
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++) {
                mtrx[i][j]=new Element();
            }
        }

        //Read the matrix from the file.
        try {
        Scanner sc=new Scanner(new BufferedReader(new FileReader(filename)));
            while (sc.hasNextLine()) {

                //Set value for each element and its indices (row and column)
                for (int i = 0; i < mtrx.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        mtrx[i][j].setValue(Integer.parseInt(line[j]));
                        mtrx[i][j].setRow(i);
                        mtrx[i][j].setColumn(j);
                    }
                }
            }
        }
       catch (FileNotFoundException e) {
            System.out.println("File not found. Check that the file exists.");
            System.exit(0);
       }
    }
}