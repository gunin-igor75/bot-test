package ru.gil.bottest.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandBot {
    void execute(Update update);
}
