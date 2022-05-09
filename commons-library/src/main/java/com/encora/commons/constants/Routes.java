package com.encora.commons.constants;

public enum Routes {
    ADD_ROUTE("/product/add");
    private String name;

    Routes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
