package com.mailliwonerom.javapocs.net.web.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Headers {
    private final List<Header> HEADERS;

    private Headers(Builder builder) {
        HEADERS = builder.headers;
    }

    public static class Builder {
        private List<Header> headers;

        public Builder() {
            headers = new ArrayList<>(0);
        }

        public Builder add(String key, String value) {
            try {
                if (headers.size() == 0) {
                    headers.add(new Header(key, List.of(value)));
                    return this;
                }
                for (int index = 0; index < headers.size(); index++) {
                    if (headers.get(index).getKey().equalsIgnoreCase(key)) {
                        headers.get(index).add(key, value);
                        return this;
                    }
                }
                headers.add(new Header(key, List.of(value)));
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
            return this;
        }

        public Builder addAll(Map<String, List<String>> headerMap) {
            if(!(headerMap.size() > 0)) {
                throw new IllegalArgumentException("Invalid headers " +
                    "size");
            }
            try {
                if (headers.size() == 0) {
                    for(String key : headerMap.keySet()) {
                        headers.add(new Header(key, headerMap.get(key)));
                    }
                    return this;
                }
                for(int index = 0; index < headerMap.size(); index++) {
                    for (String key : headerMap.keySet()) {
                        if (headers.get(index).getKey().equalsIgnoreCase(key)) {
                            headers.get(index).add(key, headerMap.get(key));
                        } else {
                            headers.add(new Header(key, headerMap.get(key)));
                        }
                    }
                }
            } catch(IllegalArgumentException e) {
                e.getMessage();
            }
            return this;
        }

        public Headers build() {
            return new Headers(this);
        }
    }

    public String[] get() {
        return entries(HEADERS);
    }

    public void remove(String key) {
        for(int index = 0; index < HEADERS.size(); index++) {
            if(HEADERS.get(index).getKey().equalsIgnoreCase(key)) {
                HEADERS.remove(index);
            }
        }
    }

    public void removeAll() {
        HEADERS.clear();
    }

    protected String[] entries(List<Header> headers) {
        List<String> headerItems = new ArrayList<>(0);
        for(int index = 0; index < headers.size(); index++) {
            for(Header header : headers) {
                for(int indexItem = 0; indexItem < header.getValue().size();
                    indexItem++) {
                    headerItems.add(header.getKey());
                    headerItems.add(header.getValue().get(indexItem));
                }
            }
        }
        return headerItems.toArray(new String[headerItems.size()]);
    }
}