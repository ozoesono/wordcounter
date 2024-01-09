package com.zao.counter.lib;

import org.springframework.stereotype.Service;

@Service
public class TranslatorImpl implements Translator {

    /**
     * This is just a mock implementation so Spring is happy
     */
    @Override
    public String translate(String flower) {
        return flower;
    }
}
