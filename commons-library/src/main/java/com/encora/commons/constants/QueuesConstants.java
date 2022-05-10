package com.encora.commons.constants;

public enum QueuesConstants {
    EXCHANGE("product"),
    MESSAGE_QUEUE("product.queue"),
    REPLY_QUEUE("product.reply");

    private String name;

    QueuesConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
