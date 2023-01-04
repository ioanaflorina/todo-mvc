package com.todo.mvc.enums;

public enum BrowserType {    CHROME("chrome"), FIREFOX("firefox"), IE("ie");

    private String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
