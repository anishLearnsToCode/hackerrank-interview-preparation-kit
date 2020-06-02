// https://www.hackerrank.com/challenges/triple-sum/problem

package search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TripleSum {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int a = SCANNER.nextInt();
        int b = SCANNER.nextInt();
        int c = SCANNER.nextInt();
        int[] first = getUniqueArray(a);
        int[] second = getUniqueArray(b);
        int[] third = getUniqueArray(c);
        System.out.println(possibleTriplets(first, second, third));
    }

    private static long possibleTriplets(int[] first, int[] second, int[] third) {
        sortAll(first, second, third);
        int[] bGreaterThanC = greaterThan(second, third);
        long[] sum = sumFromRight(bGreaterThanC);
        long count = 0;
        for (int index = first.length - 1, pointer = second.length - 1 ; index >= 0 ; index--) {
            while (pointer != -1 && first[index] <= second[pointer]) {
                pointer--;
            }
            count += pointer == second.length - 1 ? 0 : sum[pointer + 1];
        }

        return count;
    }

    private static int[] greaterThan(int[] first, int[] second) {
        int[] result = new int[first.length];
        for (int index = first.length - 1, pointer = second.length - 1 ; index >= 0 && pointer >= 0 ; index-- ) {
            if (first[index] >= second[pointer]) {
                result[index] = pointer + 1;
                continue;
            }

            while (pointer >= 0 && first[index] < second[pointer]) {
                pointer--;
            }

            result[index] =  pointer + 1;
        }

        return result;
    }

    private static long[] sumFromRight(int[] array) {
        long[] result = new long[array.length];
        result[result.length - 1] = array[array.length - 1];
        for (int index = array.length - 2 ; index >= 0 ; index--) {
            result[index] = result[index + 1] + array[index];
        }
        return result;
    }

    private static void sortAll(int[] first, int[] second, int[] third) {
        Arrays.sort(first);
        Arrays.sort(second);
        Arrays.sort(third);
    }

    private static int[] getUniqueArray(int length) {
        Set<Integer> set = new HashSet<>();
        for (int index = 0 ; index < length ; index++) {
            set.add(SCANNER.nextInt());
        }
        return toArray(set);
    }

    private static int[] toArray(Set<Integer> set) {
        int[] array = new int[set.size()];
        int index = 0;
        for (int number : set) {
            array[index++] = number;
        }
        return array;
    }
}
