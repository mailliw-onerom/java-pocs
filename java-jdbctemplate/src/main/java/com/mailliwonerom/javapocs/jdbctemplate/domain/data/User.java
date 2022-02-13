package com.mailliwonerom.javapocs.jdbctemplate.domain.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class User implements Serializable {

    private String individualTaxpayer;
    private String name;
    private String course;
    private String period;

    public User() {}

    public User(String individualTaxpayer, String name, String course, String period) {
        this.individualTaxpayer = individualTaxpayer;
        this.name = name;
        this.course = course;
        this.period = period;
    }

    public String getIndividualTaxpayer() {
        return individualTaxpayer;
    }

    public void setIndividualTaxpayer(String individualTaxpayer) {
        this.individualTaxpayer = individualTaxpayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
