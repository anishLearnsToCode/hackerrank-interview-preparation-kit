// https://www.hackerrank.com/challenges/count-triplets-1/problem

package dictionaries;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountTriplets {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int r = scanner.nextInt();
        int[] array = getArray(length);
        System.out.println(numberOfTriplets(array, r));
    }

    private static long numberOfTriplets(int[] array, int r) {
        Map<Integer, Long> frequencies = new HashMap<>();
        Map<Integer, Long> pairs = new HashMap<>();
        long result = 0;
        for (int index = array.length - 1 ; index >= 0 ; index--) {
            int number = array[index];
            result += pairs.getOrDefault(number * r, 0L);
            pairs.put(number, pairs.getOrDefault(number, 0L) + frequencies.getOrDefault(number * r, 0L));
            frequencies.put(number, frequencies.getOrDefault(number, 0L) + 1);
        }
        return result;
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }
}
