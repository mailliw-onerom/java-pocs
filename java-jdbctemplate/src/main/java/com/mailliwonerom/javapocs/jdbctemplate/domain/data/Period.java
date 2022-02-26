package com.mailliwonerom.javapocs.jdbctemplate.domain.data;

public enum Period {
    MORNING("Morning"),
    AFTERNOON("Afternoon"),
    NOCTURNAL("Nocturnal");

    private String period;

    Period(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }
}
