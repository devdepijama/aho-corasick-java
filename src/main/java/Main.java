import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public void run(String[] args) throws FileNotFoundException {
        final Scanner inputScanner = new Scanner(new FileInputStream(args[0]));

        while (true) {
            final List<String> words = new ArrayList<>();
            final int amountOfWords = inputScanner.nextInt();
            if (amountOfWords <= 0) break;

            // Read words
            for (int i = 0; i < amountOfWords; i++) words.add(inputScanner.next());

            AhoCorasick ahoCorasick = new AhoCorasick(words);

            List<String> sortedWords = words.stream()
                                            .sorted(Comparator.comparingInt(String::length))
                                            .collect(Collectors.toList());

            Map<String, Set<String>> subwordsByWords = sortedWords.stream()
                    .collect(Collectors.toMap(word -> word, word -> {
                        Set<String> result = ahoCorasick.getMatchesByWord(word).keySet();
                        result.remove(word);
                        return result;
                    }));

            Map<String, Integer> longestStreakByWord = new HashMap<>();
            for(String wordUnderAnalysis : sortedWords) {
                longestStreakByWord.put(
                    wordUnderAnalysis,
                        subwordsByWords.get(wordUnderAnalysis).stream()
                                                              .mapToInt(longestStreakByWord::get)
                                                              .max()
                                                              .orElse(0) + 1
                );
            }

            int longedStreak = longestStreakByWord.values().stream().mapToInt(value -> value).max().orElse(1);
            System.out.printf("%d%n", longedStreak);
        }
    }

    public static void main(String[] args) throws IOException {
        final Main app = new Main();
        app.run(args);
    }
}
