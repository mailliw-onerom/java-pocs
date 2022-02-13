package com.mailliwonerom.javapocs.jdbctemplate.domain.data;

public enum Course {
    ADS("System Analysis"),
    GAM("Ambiental Management"),
    EVT("Events"),
    ADM("Administration");

    private String course;

    Course(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}
