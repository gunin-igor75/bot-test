package ru.gil.bottest.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gil.bottest.utils.MessageUtils;

@Component
public class NotSupportedCommand implements Command{
    private final MessageUtils messageUtils;

    public NotSupportedCommand(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public void execute(Update update) {

    }
}