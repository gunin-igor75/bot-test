package ru.gil.bottest.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.gil.bottest.configuration.BotConfiguration;
import ru.gil.bottest.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfiguration botConfiguration;

    public TelegramBot(BotConfiguration botConfiguration) {
        super(botConfiguration.getToken());
        this.botConfiguration = botConfiguration;
    }
    @Override
    public String getBotUsername() {
        return botConfiguration.getName();
    }

    @PostConstruct
    public void init() {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        } catch (TelegramApiException e) {
            log.error("Error register bot", e);
        }
        createMenu();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update != null) {
            if ("/info".equals(update.getMessage().getText())) {
                SendMessage mess = new SendMessage();
                mess.setChatId(update.getMessage().getChatId());
                mess.setText("Выберете этап");
                mess.setReplyMarkup(sendInlineKeyBoard());
                sendAnswerMessage(mess);

            } else if ("/start".equals(update.getMessage().getText())) {
                SendMessage message = messageUtils.generateSendMessageWithText(update,
                        "Привет " + update.getMessage().getChat().getFirstName());
                sendAnswerMessage(message);
                log.info("Приветствие");
            }
        }
    }

    public ReplyKeyboardMarkup setButtons() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add("Этап №1");
        keyboardRowFirst.add("Этап №2");
        keyboardRowFirst.add("Этап №3");
        keyboard.add(keyboardRowFirst);
        markup.setKeyboard(keyboard);
        return markup;
    }

    public InlineKeyboardMarkup sendInlineKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Этап №1");
        inlineKeyboardButton1.setCallbackData("Этап №1");
        inlineKeyboardButton2.setText("Этап №2");
        inlineKeyboardButton2.setCallbackData("Этап №2");
        inlineKeyboardButton3.setText("Этап №3");
        inlineKeyboardButton3.setCallbackData("Этап №3");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private void createMenu() {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "begin work"));
        commandList.add(new BotCommand("/info", "begin menu"));
        try {
            execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error command ", e);
        }
    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error("Error send message", e);
            }
        }
    }
}
