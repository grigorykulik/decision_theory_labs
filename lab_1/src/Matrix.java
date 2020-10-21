import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


/** Объект данного класса - это матрица, которую программа считывает из указанного файла. */
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

        /** Считываем матрицу из файла */
        try {
        Scanner sc=new Scanner(new BufferedReader(new FileReader(filename)));
            while (sc.hasNextLine()) {

                /** Для каждого элемента матрицы устанавливаем значение и индексы */
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
        /** Если файл не найден, выбрасываем исключение. */
       catch (FileNotFoundException e) {
            System.out.println("File not found. Check that the file exists.");
            System.exit(0);
       }
    }
}