package com.zao.counter.lib;

public interface WordCounter {
    void addWords(String... words);
    int getWordCount(String word);
}
