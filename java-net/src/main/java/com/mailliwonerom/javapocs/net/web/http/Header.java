package com.mailliwonerom.javapocs.net.web.http;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private String key;
    private List<String> value;

    public Header() {
        this.value = new ArrayList<>();
    }

    public Header(String key, List<String> value) {
        this.key = key;
        this.value = new ArrayList<>(value);
    }

    public String getKey() {
        return key;
    }

    public List<String> getValue() {
        return value;
    }

    public void add(String key, String value) throws IllegalArgumentException {
        if(!parse(key, value)) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.value.add(value);
    }

    public void add(String key, List<String> values) throws
        IllegalArgumentException {

        if(!parse(key, values)) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.value.addAll(values);
    }

    protected boolean parse(String... items) {
        for(String item : items) {
            if((item == null) || (item.equals(""))) {
                return false;
            }
        }
        return true;
    }

    protected boolean parse(String key, List<String> values) {
        return parse(key) &&
            parse(values.toArray(new String[values.size()]));
    }
}
