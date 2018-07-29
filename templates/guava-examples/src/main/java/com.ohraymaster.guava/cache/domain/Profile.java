package com.ohraymaster.guava.cache.domain;

/**
 * Created by ray on 18-7-29.
 */
public class Profile {
    private String key;

    public Profile(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
