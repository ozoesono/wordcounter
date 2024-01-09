package com.zao.counter.lib;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Making a service to simplify usage in Springboot app
 */
@Service
public class WordCounterImpl implements WordCounter {
    private final Map<String, Integer> wordCounts = new ConcurrentHashMap<>();
    private final Translator translator;

    public WordCounterImpl(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void addWords(String... words) {
        for (String word : words) {
            if (!word.isEmpty() && word.matches("[a-zA-Z]+")) {
                String translatedWord = translator.translate(word);
                wordCounts.merge(translatedWord.toLowerCase(), 1, Integer::sum);
            }
        }
    }

    @Override
    public int getWordCount(String word) {
        String translatedWord = translator.translate(word);
        return wordCounts.getOrDefault(translatedWord.toLowerCase(), 0);
    }
}
