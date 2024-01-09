package com.zao.counter.api;

import com.zao.counter.lib.WordCounter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wordcounter")
public class WordCounterController {

    private final WordCounter wordCounter;

    public WordCounterController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping
    public ResponseEntity<?> addWords(@RequestBody List<String> words) {
        wordCounter.addWords(words.toArray(new String[0]));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getWordCount(@RequestParam String word) {
        int count = wordCounter.getWordCount(word);
        return ResponseEntity.ok(count);
    }
}
