package com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public final class ResponseWrapper {
    private final Fault fault;

    private ResponseWrapper(Fault fault) {
        this.fault = fault;
    }

    public static class Fault {
        private Detail detail;
        private String faultMessage;

        public Fault() {}

        public Fault setMessage(String faultMessage) {
            this.faultMessage = faultMessage;
            return this;
        }

        public Fault orDefault(String faultMessage) {
            if(Objects.isNull(this.faultMessage)) this.faultMessage = faultMessage;
            return this;
        }

        public Fault setDetail(String errorCode) {
            this.detail = new Detail(errorCode);
            return this;
        }

        public String getFaultMessage() {
            return faultMessage;
        }

        public Detail getDetail() {
            return detail;
        }

        public ResponseWrapper build() {
            return new ResponseWrapper(this);
        }

        private class Detail {
            private String errorCode;

            public Detail(String errorCode) {
                this.errorCode = errorCode;
            }

            public String getErrorCode() {
                return errorCode;
            }
        }
    }

    public Fault getFault() {
        return fault;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
