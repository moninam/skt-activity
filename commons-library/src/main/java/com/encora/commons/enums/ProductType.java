package com.encora.commons.enums;

public enum ProductType {
    SOUVENIR("Souvenir"),
    FOOD("Food"),
    WHITE_GOOD("White Good"),
    COMPUTER("Computer"),
    CELL_PHONE("Cellphone");
    private String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
