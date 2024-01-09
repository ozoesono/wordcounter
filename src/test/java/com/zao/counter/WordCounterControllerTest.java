package com.zao.counter;

import com.zao.counter.api.WordCounterController;
import com.zao.counter.lib.WordCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest(WordCounterController.class)
class WordCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordCounter wordCounter;

    @Test
    void whenPostRequestToAddWords_thenWordsAreAdded() throws Exception {
        mockMvc.perform(post("/api/v1/wordcounter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"flower\", \"flor\", \"blume\"]"))
                .andExpect(status().isOk());

        verify(wordCounter, times(1)).addWords("flower", "flor", "blume");
    }

    @Test
    void whenGetRequestToCountWord_thenCorrectCountReturned() throws Exception {
        given(wordCounter.getWordCount("flower")).willReturn(3);

        mockMvc.perform(get("/api/v1/wordcounter?word=flower"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }
}
