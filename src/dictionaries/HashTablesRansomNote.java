package dictionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HashTablesRansomNote {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int magazineWords = scanner.nextInt();
        int ransomWords = scanner.nextInt();
        List<String> magazine = getWords(magazineWords);
        List<String> ransomNote = getWords(ransomWords);
        System.out.println(canUseMagazine(magazine, ransomNote) ? "Yes" : "No");
    }

    private static List<String> getWords(int length) {
        List<String> words = new ArrayList<>();
        while (length-- > 0) {
            words.add(scanner.next());
        }
        return words;
    }

    private static boolean canUseMagazine(List<String> magazine, List<String> ransom) {
        Map<String, Integer> frequency = getFrequency(magazine);
        for (String word : ransom) {
            if (frequency.containsKey(word) && frequency.get(word) > 0) {
                frequency.put(word, frequency.get(word) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    private static Map<String, Integer> getFrequency(List<String> words) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }
}
