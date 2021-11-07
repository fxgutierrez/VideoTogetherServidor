package com.francescxavier.net;

public enum PetitionType {
    LOGIN("login"),
    JOINSALA("joinSala"),
    CREATESALA("createSala"),
    INVALID("invalid"),
    INVALIDUSER("invalidUser");

    private String value;


    PetitionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
