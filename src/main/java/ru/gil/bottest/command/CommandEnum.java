package ru.gil.bottest.command;

import lombok.Getter;

@Getter
public enum CommandEnum {
    START("/start"),
    INFO("/info"),
    APP("/app"),
    REGISTER("/register"),
    PETS("/pets"),
    REPORT("report");

    private final String value;
    CommandEnum(String value) {
        this.value = value;
    }
}
