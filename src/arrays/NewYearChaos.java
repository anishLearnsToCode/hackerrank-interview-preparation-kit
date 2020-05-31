package arrays;// https://www.hackerrank.com/challenges/new-year-chaos/problem

import java.util.Scanner;

public class NewYearChaos {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] array = getIntArray();
        minimumBribes(array);
    }

    static int[] getIntArray() {
        int length = getInt();
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = getInt();
        }
        return array;
    }

    static int getInt() {
        return scanner.nextInt();
    }

    static void minimumBribes(int[] array) {
        int ans = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = Math.max(0, array[i] - 2); j < i; j++)
                if (array[j] > array[i]) ans++;
        }

        System.out.println(ans);
    }
}
