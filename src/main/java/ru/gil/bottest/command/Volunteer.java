package ru.gil.bottest.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gil.bottest.utils.MessageUtils;

@Component
public class Volunteer implements Command{

    private final MessageUtils messageUtils;

    public Volunteer(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public SendMessage execute(Update update) {
        return messageUtils.sendMessageOwnerBot(
                "Хозяин ответь на вопросы в чате"
        );
    }
}
