package ru.gil.bottest.command;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.gil.bottest.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;

import static ru.gil.bottest.command.CommandBot.*;

@Component
public class CommandStorage {

    private final MessageUtils messageUtils;
    private final Map<String, Command> storage;

    public CommandStorage(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
        this.storage = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        storage.put(START.name(), new StartCommand(messageUtils));
        storage.put(INFO.name(), new InfoCommand(messageUtils));
        storage.put(APP.name(), new ApplicationCommand(messageUtils));
        storage.put(NO.name(), new NotSupportedCommand(messageUtils));
    }
}
