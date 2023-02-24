package ru.gil.bottest.command;

import lombok.Getter;

@Getter
public enum CommandBot {
    START("/start"),
    INFO("/info"),
    APP("/app"),
    REGISTER("/register"),
    PETS("/pets"),
    REPORT("/report"),

    NO("/not supported");

    private final String value;

    CommandBot(String value) {
        this.value = value;
    }
}
