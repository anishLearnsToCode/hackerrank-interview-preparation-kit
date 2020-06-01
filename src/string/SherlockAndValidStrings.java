// https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem

package string;

import java.util.*;

public class SherlockAndValidStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        System.out.println(isValid(string) ? "YES" : "NO");
    }

    private static boolean isValid(String string) {
        Map<Character, Integer> frequencies = getFrequencies(string);
        Map<Integer, Integer> histogram = getFrequencies(frequencies.values());
        Integer max = histogram.keySet().stream().max(Integer::compareTo).get();
        Integer min = histogram.keySet().stream().min(Integer::compareTo).get();
        int removalFrequency = getRemovalFrequency(histogram);
        int counter = 0;
        for (Map.Entry<Integer, Integer> entry : histogram.entrySet()) {
            if (entry.getValue() > 1) {
                counter++;
            }
        }
        return counter < 2 && histogram.size() <= 2 && (max - min <= 1 || removalFrequency == 1);
    }

    private static int getRemovalFrequency(Map<Integer, Integer> frequencies) {
        int minOccurrence = frequencies.values().stream().min(Integer::compareTo).get();
        for (int frequency : frequencies.keySet()) {
            if (frequencies.get(frequency) == minOccurrence) {
                return frequency;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static Map<Integer, Integer> getFrequencies(Collection<Integer> collection) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int number : collection) {
            frequencies.put(number, frequencies.getOrDefault(number, 0) + 1);
        }
        return frequencies;
    }

    private static Map<Character, Integer> getFrequencies(String string) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int index = 0 ; index < string.length() ; index++) {
            char character = string.charAt(index);
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }
        return frequencies;
    }
}
