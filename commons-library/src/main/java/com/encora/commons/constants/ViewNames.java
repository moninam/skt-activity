package com.encora.commons.constants;

public enum ViewNames {
    PRODUCT_ATTR("product"),
    PRODUCT_LIST("list-products"),
    ERROR_VIEW("error"),
    ADD_PRODUCT("new-product"),
    WELCOME("welcome"),

    SAVED_PRODUCT_ATTR("savedProduct"),

    ERROR_ATTR("errors"),

    ERROR_HAPPENED("errorOccurred"),

    SUCCESS_ACTION_ATTR("successAction");

    private String name;

    ViewNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
