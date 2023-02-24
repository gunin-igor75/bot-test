package ru.gil.bottest.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gil.bottest.controller.TelegramBot;

@Component
@Slf4j
public class MessageUtils {

    public SendMessage generationSendMessage(Update update, String text) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId());
        response.setText(text);
        return response;
    }

    public SendMessage generationSendMessage(Update update, InlineKeyboardMarkup markup, String text) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId());
        response.setText(text);
        response.setReplyMarkup(markup);
        return response;
    }
}
