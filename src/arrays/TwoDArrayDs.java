// https://www.hackerrank.com/challenges/2d-array/problem

package arrays;

import java.util.Scanner;

public class TwoDArrayDs {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] grid = getGrid();
        System.out.println(maximumHourGlassSum(grid));
    }

    private static int[][] getGrid() {
        int[][] grid = new int[6][6];
        for (int row = 0 ; row < grid.length ; row++) {
            for (int column = 0 ; column < grid[row].length ; column++) {
                grid[row][column] = SCANNER.nextInt();
            }
        }
        return grid;
    }

    private static int maximumHourGlassSum(int[][] grid) {
        int maximumHourglassSum = Integer.MIN_VALUE;
        for (int row = 0 ; row < grid.length - 2 ; row++) {
            for (int column = 0 ; column < grid[row].length - 2 ; column++) {
                maximumHourglassSum = Math.max(maximumHourglassSum, hourGlassSum(grid, row, column));
            }
        }
        return maximumHourglassSum;
    }

    private static int hourGlassSum(int[][] grid, int row, int column) {
        int sum = 0;
        for (int index = column ; index < column + 3 ; index++) {
            sum += grid[row][index] + grid[row + 2][index];
        }
        return sum + grid[row + 1][column + 1];
    }
}
