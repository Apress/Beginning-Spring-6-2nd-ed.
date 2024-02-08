package com.bsg6.chapter04;

public interface Normalizer {
    default String transform(String input) {
        return input.trim();
    }
}
