// https://www.hackerrank.com/challenges/crush/problem

package arrays;

import java.util.Scanner;

public class ArrayManipulation {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int length = SCANNER.nextInt();
        int queries = SCANNER.nextInt();
        long[] array = new long[length + 2];
        while (queries-- > 0) {
            int startIndex = SCANNER.nextInt();
            int endIndex = SCANNER.nextInt();
            int value = SCANNER.nextInt();
            array[startIndex] += value;
            array[endIndex + 1] -= value;
        }
        System.out.println(maxSum(array));
    }

    private static long maxSum(long[] array) {
        long result = 0, sum = 0;
        for (long number : array) {
            sum += number;
            result = Math.max(result, sum);
        }
        return result;
    }
}
