package ru.gil.bottest.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gil.bottest.controller.TelegramBot;

@Component
@Slf4j
public class MessageUtils {

    private final TelegramBot telegramBot;

    public MessageUtils(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendAnswerMessage(Update update, String text) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId());
        response.setText(text);
        try {
            telegramBot.execute(response);
        } catch (TelegramApiException e) {
            log.error("Error send message", e);
        }
    }

}
