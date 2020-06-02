// https://www.hackerrank.com/challenges/pairs/problem

package search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pairs {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int k = scanner.nextInt();
        long[] array = getArray(length);
        System.out.println(pairs(array, k));
    }

    private static long pairs(long[] array, int k) {
        Map<Long, Integer> frequencies = getFrequencies(array);
        long count = 0;
        for (long element : array) {
            long difference = element - k;
            count += frequencies.getOrDefault(difference, 0);
        }
        return count;
    }

    private static Map<Long, Integer> getFrequencies(long[] array) {
        Map<Long, Integer> frequencies = new HashMap<>();
        for (long number : array) {
            frequencies.put(number, frequencies.getOrDefault(number, 0) + 1);
        }
        return frequencies;
    }

    private static long[] getArray(int length) {
        long[] array = new long[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextLong();
        }
        return array;
    }
}
