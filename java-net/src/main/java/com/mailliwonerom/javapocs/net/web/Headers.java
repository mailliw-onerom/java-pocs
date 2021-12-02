package com.mailliwonerom.javapocs.net.web;

import com.mailliwonerom.javapocs.net.exception.header.HeaderDoesntExistsException;
import com.mailliwonerom.javapocs.net.exception.header.HeaderKeyAlreadyExistsException;
import com.mailliwonerom.javapocs.net.exception.header.HeaderWithoutKeyValueException;

import java.util.*;

public class Headers {
    private final String[] HEADERS;

    private Headers(Builder builder) {
        HEADERS = builder.entries();
    }

    public String[] getHeaders() {
        return HEADERS;
    }

    public static class Builder {
        private Map<String, String> headers;

        public Builder() {
            headers = new HashMap<>();
        }

        public Builder addHeader(String key, String value) {
            if(headers.containsKey(key)) {
                throw new HeaderKeyAlreadyExistsException(key + " already " +
                    "exists");
            }
            headers.put(key, value);
            return this;
        }

        public Builder removeHeader(String key) {
            if(!headers.containsKey(key)) {
                throw new HeaderWithoutKeyValueException(key + "doesn't found");
            }
            headers.remove(key);
            return this;
        }

        public Builder addAll(Map<String, String> headerMap) {
            if(!(headerMap.size() > 0)) {
                throw new HeaderDoesntExistsException("Invalid headers " +
                    "size");
            }
            try {
                for (String key : headerMap.keySet()) {
                    headers.put(key, headerMap.get(key));
                }
            } catch(HeaderKeyAlreadyExistsException e) {
                throw new HeaderKeyAlreadyExistsException(
                        "Key already exists");
            }
            return this;
        }

        public Builder removeAll() {
            if(headers.isEmpty()) {
                throw new HeaderDoesntExistsException("Header is empty");
            }
            for(String key : headers.keySet()) {
                headers.remove(key);
            }
            return this;
        }

        public String[] entries() {
            List<String> entries = new ArrayList<>(0);
            for(Map.Entry<String, String> entry : headers.entrySet()) {
                entries.add(entry.getKey());
                entries.add(entry.getValue());
            }
            return entries.toArray(new String[entries.size()]);
        }

        public Headers build() {
            return new Headers(this);
        }
    }
}