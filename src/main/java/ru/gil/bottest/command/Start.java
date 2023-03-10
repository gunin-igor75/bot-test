package ru.gil.bottest.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gil.bottest.utils.MessageUtils;


/**
 * Данный класс формрует сообщения исодя из выбора start
 */
@Component
public class Start implements Command{

    private final MessageUtils messageUtils;

    public Start(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute(Update update) {
        String userName = update.getMessage().getChat().getUserName();
        return messageUtils.generationSendMessage(update, "Здравствуйте " + userName +
                " Вас приветствует бот приюта!");
    }
}
