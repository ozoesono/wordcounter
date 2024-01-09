package com.zao.counter;

import com.zao.counter.lib.Translator;
import com.zao.counter.lib.WordCounter;
import com.zao.counter.lib.WordCounterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCounterTest {
    private WordCounter wordCounter;
    private Translator translator;

    @BeforeEach
    void setUp() {
        translator = Mockito.mock(Translator.class);
        wordCounter = new WordCounterImpl(translator);
    }

    @Test
    void whenAddingASingleWord_thenTheCountShouldBeCorrect() {
        Mockito.when(translator.translate("Computer")).thenReturn("Computer");
        wordCounter.addWords("Computer");
        assertEquals(1, wordCounter.getWordCount("Computer"));
    }

    @Test
    void whenAddingSameWordInDifferentLanguages_thenTheCountShouldBeCorrect() {
        Mockito.when(translator.translate("Flower")).thenReturn("Flower");
        Mockito.when(translator.translate("Flor")).thenReturn("Flower");
        Mockito.when(translator.translate("Blume")).thenReturn("Flower");
        wordCounter.addWords("Flower", "Flor", "Blume");
        assertEquals(3, wordCounter.getWordCount("Flower"));
    }

    @Test
    void whenAddingWordsWithIllegalCharacters_thenTheCountShouldBeCorrect() {
        Mockito.when(translator.translate("fl0wer")).thenReturn("fl0wer");
        wordCounter.addWords("fl0wer");
        assertEquals(0, wordCounter.getWordCount("fl0wer"));
        Mockito.when(translator.translate("W000l3")).thenReturn("W000l3");
        wordCounter.addWords("W000l3");
        assertEquals(0, wordCounter.getWordCount("W000l3"));
    }

    @Test
    void whenAddingWordsWithDifferentCases_thenTheCountShouldBeCorrect() {
        Mockito.when(translator.translate("Flower")).thenReturn("flower");
        Mockito.when(translator.translate("FLOWER")).thenReturn("flower");
        Mockito.when(translator.translate("flower")).thenReturn("flower");
        wordCounter.addWords("Flower", "FLOWER");
        assertEquals(2, wordCounter.getWordCount("flower"));
    }

    @Test
    void whenAddingSameWordMultipleTimes_thenCountsShouldBeAggregated() {
        Mockito.when(translator.translate("flower")).thenReturn("flower");
        wordCounter.addWords("flower", "flower", "flower");
        assertEquals(3, wordCounter.getWordCount("flower"));
    }

    @Test
    void whenAddingEmptyWord_ThenIgnoreThem() {
        Mockito.when(translator.translate("")).thenReturn("");
        Mockito.when(translator.translate(null)).thenReturn(null);
        wordCounter.addWords("");
        assertEquals(0, wordCounter.getWordCount(""));
    }

    @Test
    void whenGettingCountOfNonexistentWord_thenZeroIsReturned() {
        Mockito.when(translator.translate("WordDoesNotExist")).thenReturn("WordDoesNotExist");
        assertEquals(0, wordCounter.getWordCount("WordDoesNotExist"));
    }

}