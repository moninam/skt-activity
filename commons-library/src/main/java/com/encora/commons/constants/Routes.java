package com.encora.commons.constants;

public enum Routes {
    ADD_ROUTE("/product/add"),
    ERROR_ROUTE("/product/error");
    private String name;

    Routes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
