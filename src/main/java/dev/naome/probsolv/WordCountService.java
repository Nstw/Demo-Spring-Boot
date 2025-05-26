package dev.naome.probsolv;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class WordCountService {
    public Map<String, Integer> phrase(String input) {
        Map<String, Integer> wordCount = new HashMap<>();

        String normalized = input.toLowerCase();

        Pattern pattern = Pattern.compile("[a-z0-9]+(?:'[a-z0-9]+)*");
        Matcher matcher = pattern.matcher(normalized);

        while (matcher.find()) {
            String word = matcher.group();
            wordCount.merge(word, 1, Integer::sum);
        }

        return wordCount;
    }
}
