package ru.gil.bottest.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gil.bottest.utils.MessageUtils;
@Component
public class Start implements Command {

    private final CommandBot commandBot = CommandBot.START;

    private final MessageUtils messageUtils;

    public Start(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute(Update update) {
        String name = update.getMessage().getChat().getFirstName();
        return messageUtils.generationSendMessage(update,
                "Привет " + name);
    }
}
