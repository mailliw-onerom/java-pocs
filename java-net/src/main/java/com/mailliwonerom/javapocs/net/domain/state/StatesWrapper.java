package com.mailliwonerom.javapocs.net.domain.state;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StatesWrapper {
    public List<State> states;

    public StatesWrapper() {}

    public List<State> getStates() {
        return states;
    }

    public void addState(State state) {
        if(states == null) {
            states = new ArrayList<>();
        }
        this.states.add(state);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
