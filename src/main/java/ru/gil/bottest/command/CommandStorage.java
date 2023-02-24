package ru.gil.bottest.command;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static ru.gil.bottest.command.CommandBot.*;

@Component
public class CommandStorage {

    private final StartCommand startCommand;
    private final InfoCommand infoCommand;
    private final AppCommand appCommand;

    private final NotSupportedCommand notSupportedCommand;

    private final Map<String, Command> storage;

    public CommandStorage(StartCommand startCommand, InfoCommand infoCommand, AppCommand appCommand, NotSupportedCommand notSupportedCommand) {
        this.startCommand = startCommand;
        this.infoCommand = infoCommand;
        this.appCommand = appCommand;
        this.notSupportedCommand = notSupportedCommand;
        this.storage = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        storage.put(START.getValue(),startCommand);
        storage.put(INFO.getValue(), infoCommand);
        storage.put(APP.getValue(), appCommand);
        storage.put(NO.getValue(),notSupportedCommand);
    }
}
