package arrays;

import java.util.Scanner;

public class LeftRotation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int rotations = scanner.nextInt() % length;
        int[] array = getArray(length);
        array = rotateArray(array, rotations);
        print(array);
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }

    private static void print(int[] array) {
        for (int number : array) {
            System.out.print(number + " ");
        }
    }

    private static int[] rotateArray(int[] array, int rotations) {
        if (rotations == 0) {
            return array;
        }

        int[] result = new int[array.length];
        if (array.length - rotations >= 0)
            System.arraycopy(array, rotations, result, 0, array.length - rotations);
        if (rotations >= 0) System.arraycopy(array, 0, result, -rotations + array.length, rotations);
        return result;
    }
}
