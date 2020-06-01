// https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem

package warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JumpingOnTheClouds {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int[] array = getArray(length);
        System.out.println(minimumJumps(array));
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }

    private static int minimumJumps(int[] array) {
        return minimumJumps(array, 0, new HashMap<>());
    }

    private static int minimumJumps(int[] array, int currentPosition, Map<Integer, Integer> memory) {
        if (currentPosition >= array.length || array[currentPosition] == 1) {
            return Integer.MAX_VALUE;
        }

        if (currentPosition == array.length - 1) {
            return 0;
        }

        if (memory.containsKey(currentPosition)) {
            return memory.get(currentPosition);
        }

        int answer = 1 + Math.min(
                minimumJumps(array, currentPosition + 1, memory),
                minimumJumps(array, currentPosition + 2, memory)
        );
        memory.put(currentPosition, answer);
        return answer;
    }
}
