package com.mailliwonerom.javapocs.net.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, String> HEADERS;

    private Headers(Builder builder) {
        HEADERS = builder.getHeaders();
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.HEADERS);
    }

    public static class Builder {
        private final Map<String, String> HEADERS;

        public Builder() {
            HEADERS = new HashMap<>();
        }

        public Builder addHeader(String key, String value) {
            HEADERS.put(key, value);
            return this;
        }

        public Builder removeHeader(String key) {
            HEADERS.remove(key);
            return this;
        }

        public Builder addAll(Map<String, String> headerMap) {
            for(String key : headerMap.keySet()) {
                HEADERS.put(key, headerMap.get(key));
            }
            return this;
        }

        public Builder removeAll() {
            if(!HEADERS.isEmpty()) {
                for(String key : HEADERS.keySet()) {
                    HEADERS.remove(key);
                }
            }
            return this;
        }

        public Map<String, String> getHeaders() {
            return Collections.unmodifiableMap(HEADERS);
        }

        public Headers build() {
            return new Headers(this);
        }
    }
}