// https://www.hackerrank.com/challenges/common-child/problem

package string;

import java.util.Scanner;

public class CommonChild {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.next();
        String second = scanner.next();
        System.out.println(lengthLongestCommonSubSequence(first, second));
    }

    private static int lengthLongestCommonSubSequence(String first, String second) {
        int[][] memory = new int[first.length() + 1][second.length() + 1];
        for (int row = 1 ; row < memory.length ; row++) {
            for (int column = 1 ; column < memory[0].length ; column++) {
                memory[row][column] = max(memory[row - 1][column], memory[row][column - 1],
                        first.charAt(row - 1) == second.charAt(column - 1) ? memory[row - 1][column - 1] + 1 : 0);
            }
        }
        return memory[memory.length - 1][memory[0].length - 1];
    }

    private static int max(int... numbers) {
        int result = 0;
        for (int number : numbers) {
            result = Math.max(result, number);
        }
        return result;
    }
}
