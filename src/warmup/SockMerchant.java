// https://www.hackerrank.com/challenges/sock-merchant/problem

package warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SockMerchant {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int length = scanner.nextInt();
        int[] array = getArray(length);
        System.out.println(numberOfSocks(array));
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }

    private static int numberOfSocks(int[] array) {
        Map<Integer, Integer> frequencies = getFrequencies(array);
        int socks = 0;
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            socks += entry.getValue() / 2;
        }
        return socks;
    }

    private static Map<Integer, Integer> getFrequencies(int[] array) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int number : array) {
            result.put(number, result.getOrDefault(number, 0) + 1);
        }
        return result;
    }
}
