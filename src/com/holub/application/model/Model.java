package com.holub.application.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private static final Model instance = new Model();
    private final Map<String, Object> model;

    private Model(){
        this.model = new HashMap<>();
    }

    public static Model getInstance() {
        return instance;
    }

    public void addAttribute(String key, Object value) {
        model.put(key, value);
    }

    public Object getAttribute(String key) {
        return model.get(key);
    }

    public boolean containsAttribute(String key) {
        return model.containsKey(key);
    }

    public void removeAttribute(String key) {
        model.remove(key);
    }

}
