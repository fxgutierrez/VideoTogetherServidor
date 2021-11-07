package com.francescxavier.net;

import com.francescxavier.utils.NetUtils;

public enum ResponseType {
    OK("ok"),
    NO("no"),
    FAIL("fail"),
    ALREADYLOGED("alreadyLoged"),
    UNKNOWN("");

    private String value;


    ResponseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
