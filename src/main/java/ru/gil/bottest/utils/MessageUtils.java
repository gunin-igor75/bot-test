package ru.gil.bottest.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * *  Утилитный клас, который содержит методы по формированию
 * SenDMessage
 */
@Component
@Slf4j
public class MessageUtils {
    /**
     * Переменная CHAT_ID - chatId - хозяина бота
     */
    private final long CHAT_ID = 1998202918L;

    /**
     * Данный мметод генерирует сообщение пользователю
     *
     * @param update - входящий параметр, который содержит всю входящую
     *               информацию о взаимодействии с ботом
     * @param text   - текст сообщения который будет отправлен пользователю
     *               взаимодействующему с ботом
     * @return - возвращает SendMessage для дальнейшей отправки пользователю
     */
    public SendMessage generationSendMessage(Update update, String text) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId());
        response.setText(text);
        return response;
    }

    /**
     *
     * Данный метод генерирует сообщение пользователю с отображением
     * клавитуры
     *
     * @param update -входящий параметр, который содержит всю входящую
     *               информацию о взаимодействии с ботом
     * @param markup - встроенная клавиатура
     * @param text   - текст сообщения который будет отправлен с клавиатурой
     * @return - возвращает SendMessage для дальнейшей отправки пользователю
     *
     */

    public SendMessage generationSendMessage(Update update, InlineKeyboardMarkup markup, String text) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId());
        response.setText(text);
        response.setReplyMarkup(markup);
        return response;
    }

    /**
     * Данный метод отправляет сообщение хозяину бота
     * @return Возвращает сообщение хозяну чата
     */
    public SendMessage sendMessageCallOwner() {
        SendMessage response = new SendMessage();
        response.setChatId(CHAT_ID);
        response.setText("Хозяин помоги. Не могу решить вопрос");
        return response;
    }
}
