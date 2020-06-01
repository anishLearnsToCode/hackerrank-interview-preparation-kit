// https://www.hackerrank.com/challenges/ctci-making-anagrams/problem

package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringsMakingAnagrams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.next();
        String second = scanner.next();
        System.out.println(deletionsRequiredForAnagram(first, second));
    }

    private static int deletionsRequiredForAnagram(String first, String second) {
        Map<Character, Integer> frequenciesFirst = getFrequencies(first);
        Map<Character, Integer> frequenciesSecond = getFrequencies(second);
        int deletions = 0;
        for (Map.Entry<Character, Integer> entry : frequenciesFirst.entrySet()) {
            deletions += Math.abs(entry.getValue() - frequenciesSecond.getOrDefault(entry.getKey(), 0));
        }
        for (Map.Entry<Character, Integer> entry : frequenciesSecond.entrySet()) {
            if (!frequenciesFirst.containsKey(entry.getKey())) {
                deletions += entry.getValue();
            }
        }

        return deletions;
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
