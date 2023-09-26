package com.mecorp.enums;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    public final String name;

    Role(String name) {
        this.name = name;
    }
}
