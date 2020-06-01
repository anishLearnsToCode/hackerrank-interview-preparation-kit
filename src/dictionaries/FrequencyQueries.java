// https://www.hackerrank.com/challenges/frequency-queries/problem

package dictionaries;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencyQueries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> elements = new HashMap<>();
        Map<Integer, Integer> frequencies = new HashMap<>();

        int queries = scanner.nextInt();
        while (queries-- > 0) {
            int operation = scanner.nextInt();
            int element = scanner.nextInt();
            int occurrences = elements.getOrDefault(element, 0);
            int frequency = frequencies.getOrDefault(occurrences, 0);
            if (operation == 1) {
                elements.put(element, occurrences + 1);
                frequencies.put(occurrences, frequency == 0 ? 0 : frequency - 1);
                frequencies.put(occurrences + 1, frequencies.getOrDefault(occurrences + 1, 0) + 1);
            } else if (operation == 2) {
                elements.put(element, occurrences == 0 ? 0 : occurrences - 1);
                frequencies.put(occurrences, frequency == 0 ? 0 : frequency - 1);
                frequencies.put(occurrences - 1, frequencies.getOrDefault(occurrences - 1, 0) + 1);
            } else {
                System.out.println(frequencies.getOrDefault(element, 0) > 0 ? 1 : 0);
            }
        }
    }
}
