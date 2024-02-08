package com.bsg6.chapter03;

public interface Normalizer {
    default String transform(String input) {
        return input.trim();
    }
}
