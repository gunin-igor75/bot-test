package ru.gil.bottest.command;

import lombok.Getter;

@Getter
public enum CommandBot {
    START("startCommand"),
    INFO("infoCommand"),
    APP("appCommand"),
//    REGISTER("registerCommand"),
//    PETS("petsCommand"),
//    REPORT("reportCommand"),

    NO("notSupportedCommand");

    private final String value;

    CommandBot(String value) {
        this.value = value;
    }
}
