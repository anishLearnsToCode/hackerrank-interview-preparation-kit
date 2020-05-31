import java.util.Scanner;

public class StdIn {
    private static final Scanner SCANNER = new Scanner(System.in);

    static int[] getIntArray() {
        int length = getInt();
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = getInt();
        }
        return array;
    }

    static int getInt() {
        return SCANNER.nextInt();
    }
}
