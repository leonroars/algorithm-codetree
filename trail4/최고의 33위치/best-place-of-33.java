import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        int standardRow = 0;
        int standardCol = 0;
        int maxAccumSum = -Integer.MAX_VALUE;

        while(standardRow <= n - 3) {
            while(standardCol <= n - 3){
                int accumSum = 0;

                for(int turn = 0; turn < 9; turn++){
                    int currentRow = (standardRow + (turn / 3));
                    int currentCol = (standardCol + (turn % 3));
                    accumSum += grid[currentRow][currentCol];
                }
                
                maxAccumSum = Math.max(maxAccumSum, accumSum);
                standardCol++;
            }
            standardRow++;
            standardCol = 0;
        }

        System.out.print(maxAccumSum);
    }
}