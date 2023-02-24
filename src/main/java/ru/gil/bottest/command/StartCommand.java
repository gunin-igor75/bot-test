package ru.gil.bottest.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gil.bottest.utils.MessageUtils;

public class StartCommand implements CommandBot{

    private final MessageUtils messageUtils;

    public StartCommand(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public void execute(Update update) {
        String name = update.getMessage().getChat().getFirstName();
        messageUtils.sendAnswerMessage(update,
                "Привет " + name);
    }
}
