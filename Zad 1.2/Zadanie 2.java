import java.util.Scanner;

public class Solution {
    public static void main(String[] args)
    {
        //Przygotowanie i podstawowe warunki
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        if (size < 1)
        {
            throw new RuntimeException("Wpisano nieprawidłowy wymiar");
        }
        if (size == 1) 
        {
            int x = scanner.nextInt();
            System.out.println(x);
            return;
        }
        //Tworzenie
        int[][] matrix = new int[size][size];
        int[][] rotatedMatrix = new int[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                matrix[i][j] = scanner.nextInt();
            }
        }
        //Rotacja
        for (int i = 0; i < size; i++)
        {
            for (int j = (size - 1); j >= 0; j--)
            {
                rotatedMatrix[i][j] = matrix[size - 1 - j][i];
            }
        }
        //Wypisanie
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(rotatedMatrix[i][j]);
                if (j < size - 1){
                    System.out.print(" "); 
                }
            }
            System.out.print("\n");
        }
    }
}