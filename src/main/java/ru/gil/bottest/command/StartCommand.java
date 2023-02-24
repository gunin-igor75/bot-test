package ru.gil.bottest.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gil.bottest.utils.MessageUtils;
@Component
public class StartCommand implements Command {

    private final CommandBot commandBot = CommandBot.START;

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
