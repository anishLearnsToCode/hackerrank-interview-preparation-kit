// https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem

package search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashTableIceCreamParlour {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            int money = scanner.nextInt();
            int length = scanner.nextInt();
            int[] array = getArray(length);
            printIndices(array, money);
        }
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }

    private static void printIndices(int[] array, int money) {
        Map<Integer, Integer> indicesMap = getIndicesMap(array);
        for (int index = 0 ; index < array.length ; index++) {
            int element = array[index];
            int difference = money - element;
            if (indicesMap.containsKey(difference) && indicesMap.get(difference) != index + 1) {
                System.out.println(index + 1 + " " + indicesMap.get(difference));
                return;
            }
        }
    }

    private static Map<Integer, Integer> getIndicesMap(int[] array) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int index = 0 ; index < array.length ; index++) {
            result.put(array[index], index + 1);
        }
        return result;
    }
}
