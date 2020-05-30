// https://www.hackerrank.com/challenges/minimum-swaps-2/problem

package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumSwaps2 {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        int length = SCANNER.nextInt();
        int[] array = getArray(length);
        System.out.println(minimumSwaps(array));
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = SCANNER.nextInt();
        }
        return array;
    }

    private static int minimumSwaps(int[] array) {
        Map<Integer, Integer> elementIndices = getIndicesMap(array);
        int swaps = 0;
        for (int number = 1 ; number <= array.length ; number++) {
            int index = elementIndices.get(number);

            if (number == index + 1) {
                continue;
            }

            swaps++;
            array[index] = array[number - 1];
            array[number - 1] = number;
            elementIndices.put(number, number - 1);
            elementIndices.put(array[index], index);
        }
        return swaps;
    }

    private static Map<Integer, Integer> getIndicesMap(int[] array) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int index = 0 ; index < array.length ; index++) {
            result.put(array[index], index);
        }
        return result;
    }
}
