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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.gil.bottest.command.CommandStorage;
import ru.gil.bottest.configuration.BotConfiguration;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private String PREFIX = "/";

    private final BotConfiguration botConfiguration;

    private final CommandStorage storage;

    public TelegramBot(BotConfiguration botConfiguration, CommandStorage storage) {
        super(botConfiguration.getToken());
        this.botConfiguration = botConfiguration;
        this.storage = storage;
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
        SendMessage message;
        System.out.println(storage.getStorage());
        if (update.hasMessage() && update.getMessage().hasText() &&
                update.getMessage().getText().startsWith(PREFIX)) {
            String key = update.getMessage().getText().split("\\s+")[0].substring(1);
            System.out.println(key);
            message = storage.getStorage().get(key).execute(update);
        } else {
            message = storage.getStorage().get("notSupported").execute(update);
        }
        sendAnswerMessage(message);


        //        if (update != null) {
//            if ("/info".equals(update.getMessage().getText())) {
//                SendMessage mess = new SendMessage();
//                mess.setChatId(update.getMessage().getChatId());
//                mess.setText("Выберете этап");
//                mess.setReplyMarkup(sendInlineKeyBoard());
//                sendAnswerMessage(mess);
//
//            } else if ("/start".equals(update.getMessage().getText())) {
//                SendMessage message = messageUtils.generateSendMessageWithText(update,
//                        "Привет " + update.getMessage().getChat().getFirstName());
//                sendAnswerMessage(message);
//                log.info("Приветствие");
//            }
//        }
    }


    private void createMenu() {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "Приветсвие бота"));
        commandList.add(new BotCommand("/info", "Информация о приюте"));
        commandList.add(
                new BotCommand("/app", "Просмотр, усыновление питомцев, отчеты"));
        try {
            execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error command ", e);
        }
    }

    public void sendAnswerMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error send message", e);
        }
    }


}
