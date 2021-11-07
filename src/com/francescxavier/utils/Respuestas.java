package com.francescxavier.utils;

public enum Respuestas {
    OK("1"),
    NO("2"),
    FAIL("3");

    private String value;

    Respuestas(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
