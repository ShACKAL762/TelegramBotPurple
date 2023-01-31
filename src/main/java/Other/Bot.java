package Other;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Bot extends TelegramBot {
    final long admin1ID = 696515725;//Аня
    final long admin2ID = 1164881439;//Рабочий
    final long admin3ID = 383974151;//Настя
    final long admin4ID = 435445422;//admin
    
    MySql mySql = new MySql();
    Photos photos = new Photos();
    List<Member> memberVars = new ArrayList<>();
    int messageId;
    String inlineMessageId;
    TextSelect textSelect;
    String menuBotSelection;
    User userData;


    @lombok.Builder
    public Bot(String botToken) {
        super(botToken);
    }
    public void run() {
        System.out.println("Start");
        this.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
    private void process(Update update) {
        Message message = update.message();
        long userChatId = 0;

        CallbackQuery callbackQuery = update.callbackQuery();



            if(message != null) {
                if(message.from() != null)
                userData = message.from();


                userChatId = message.chat().id();
                messageId = message.messageId();
            }
            else {
                if (callbackQuery != null){
                    userChatId = callbackQuery.from().id();
                    messageId = callbackQuery.message().messageId();
            }
            }
        if (update.callbackQuery() != null)
        if (!(update.callbackQuery().data().equals("/>") || update.callbackQuery().data().equals("/<"))) {
            //System.out.println("del button");
            for (int i = messageId; i > messageId - 5; i--) {
                if(userChatId == admin2ID)
                    break;
                this.execute(new DeleteMessage(userChatId, i));
            }
        }
        if (message != null)
            if (message.contact() !=null){
                //System.out.println("del ne con");
                for (int i = messageId; i > messageId - 5; i--) {
                    if(userChatId == admin2ID)
                        break;
                    this.execute(new DeleteMessage(userChatId, i));
                }
            }
        else if(message.text() != null)
            if (!(message.text().equals("/>")) || !(message.text().equals("/<"))) {
                //System.out.println("del<>");
                for (int i = messageId; i > messageId - 5; i--) {
                    if(userChatId == admin2ID)
                        break;
                    this.execute(new DeleteMessage(userChatId, i));
                }
            }
                if (message != null) {
            String text = message.text();
            if (message.text() != null)
            if (message.text().equals("Главное меню"))
                text = "/start";
            if (message.contact() !=null)
            if (message.contact().phoneNumber() != null)
            {
                this.execute(new SendMessage(message.chat().id(), "Номер отправлен").replyMarkup(new ReplyKeyboardRemove(true)));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                text = "/enroll";
                try {
                    mySql.UPDATE_NUMBER(message.chat().id(), message.contact().phoneNumber());
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }//registration phone number
                    messageId = message.messageId() ;

                    Optional.ofNullable(text)
                    .ifPresent(commandName -> this.serveCommand(commandName, message.chat().id()));
        }else if(callbackQuery != null){
                    userData = callbackQuery.from();
                    System.out.println("кнопка");
                    String text = callbackQuery.data();
                    String adminMessage;
                    if(text.equals("send1")||text.equals("send2")){
                    if (text.equals("send1"))
                        adminMessage = "Мастер-класс";
                    else{
                        adminMessage = "Абонимент/Курс";
                    }
                        this.execute(new SendMessage(admin1ID, adminMessage));
                        this.execute(new SendMessage(admin2ID, adminMessage));
                        this.execute(new SendMessage(admin3ID, adminMessage));


                    try {
                        this.execute(new SendContact(admin1ID, mySql.GETNUMBER(userChatId), mySql.GETNAME(userChatId)));
                        this.execute(new SendContact(admin2ID, mySql.GETNUMBER(userChatId), mySql.GETNAME(userChatId)));
                        this.execute(new SendContact(admin3ID, mySql.GETNUMBER(userChatId), mySql.GETNAME(userChatId)));

                        System.out.println("send");
                        this.execute(new SendMessage(userChatId, "Успешно, мы скоро с вами свяжемся!").replyMarkup(new ReplyKeyboardRemove(true)));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        text = "/start";
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    }
                    inlineMessageId = update.callbackQuery().inlineMessageId();


                    messageId = update.callbackQuery().message().messageId();
                    BotCommand botCommand = new BotCommand(text, "Первое меню ");
                    botCommand.command();
                    this.serveCommand(botCommand.command(),callbackQuery.from().id());
        }
    }
    private void serveCommand(String commandName, Long chatId) {
        switch (commandName) {
            case "/start" -> {
                if (chatId == 302185821) {
                    this.execute(new SendSticker(chatId, "CAACAgIAAxkBAAIFRGLy2H08CgF2nneKa8Sgv1pZi1k3AAIhAANdBYIW8hjFHoIvWpUpBA"));
                    this.execute(new SendMessage(chatId, "Привет, любимая ")
                            .replyMarkup((new InlineKeyboardMarkup(new InlineKeyboardButton("Кит").callbackData("/kit")))));
                } //Чисто китовья тема
                try {
                    if (!(mySql.select(chatId))) {
                        mySql.insert(userData.firstName(),
                                userData.lastName(),
                                userData.username(),
                                chatId);
                    }//registration new user
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                } //check and registration

                try {
                    if(mySql.GETNUMBER(chatId) == null) {
                        this.execute(new SendSticker(chatId, "CAACAgIAAxkBAAILfWMLcKC58mkrKGax4PSxux9sG1abAAI-GAACcHSQSyzM1roiBZr-KQQ")
                                .replyMarkup((KeyboardMain.sendInlineKeyBoardMessageNewUser())));
                    }else this.execute(new SendSticker(chatId, "CAACAgIAAxkBAAILfWMLcKC58mkrKGax4PSxux9sG1abAAI-GAACcHSQSyzM1roiBZr-KQQ")
                            .replyMarkup((KeyboardMain.sendInlineKeyBoardMessage())));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (memberVars.size() == 0)
                    memberVars.add(new Member(chatId, menuBotSelection));
                boolean memberFind = false;
                for (Member memberVar : memberVars) {
                    if (memberVar.chatId == chatId) {
                        memberFind = true;
                        break;
                    }
                }
                if (!(memberFind)) {
                    memberVars.add(new Member(chatId, menuBotSelection));

                }

            }
            case "/registration" ->
                    this.execute(new SendMessage(chatId,"Чтобы записаться отправьте нам ваш номер телефона, кнопкой внизу экрана")
                            .replyMarkup(new ReplyKeyboardMarkup(new KeyboardButton("Отравить номер").requestContact(true)).resizeKeyboard(true).addRow(new KeyboardButton("Главное меню"))));
            case "/>" ->
                menuSelect(chatId, 1);
            case "/<" ->
                    menuSelect(chatId, -1);
            case "/kit" -> {
                this.execute(new SendMessage(chatId, "Кита кит"));
                this.execute(new SendSticker(chatId, "CAACAgIAAxkBAAIFSmLy2fJNay2X3WUgAAF6anxD66olZwACQQADspiaDm4PLeCw1KxAKQQ"));
            }
            case "/price" -> {
                String text = null;
                try {
                    //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\Price.txt"));
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/Price.txt"));
                    List<String> list = bufferedReader.lines().toList();
                    StringBuilder textBuilder = new StringBuilder(list.get(0));
                    for(int i = 1; i <= list.size()-1; i++){
                        textBuilder.append("\n").append(list.get(i));
                    }
                    text = textBuilder.toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                this.execute(new SendMessage(chatId, text).replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Главное меню").callbackData("/start"))));

            }
            case "/tematic" -> {
                menuBotSelection = "tematic";
                for (Member number : memberVars) {
                    if (number.chatId == chatId) {
                        number.menuString = menuBotSelection;
                    }
                }
                SendMediaGroup sendMediaGroup = new SendMediaGroup(chatId, new InputMediaPhoto(photos.getPhoto(1, menuBotSelection)));
                SendMessage sendMessage = new SendMessage(chatId, TextReader.read(1, 1));
                textSelect = new TextSelect(0, messageId, menuBotSelection);
                try {
                    for (Member number : memberVars) {

                        if (number.chatId == chatId) {
                            number.setVarMenu(1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.execute(sendMediaGroup);
                this.execute(sendMessage.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("<").callbackData("/<"), new InlineKeyboardButton(">").callbackData("/>")).addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));
            }

            case "/subscription" -> {
                menuBotSelection = "subscription";
                for (Member number : memberVars) {
                    if (number.chatId == chatId) {
                        number.menuString = menuBotSelection;
                    }
                }
                SendMediaGroup sendMediaGroup = new SendMediaGroup(chatId, new InputMediaPhoto(photos.getPhoto(1, menuBotSelection)));
                SendMessage sendMessage = new SendMessage(chatId, TextReader.read(2, 1));
                textSelect = new TextSelect(0, messageId, menuBotSelection);
                try {
                    for (Member number : memberVars) {

                        if (number.chatId == chatId) {
                            number.setVarMenu(1);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



                this.execute(sendMediaGroup);
                this.execute(sendMessage.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("<").callbackData("/<"), new InlineKeyboardButton(">").callbackData("/>")).addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));

            }
            case "/courses" -> {
                menuBotSelection = "course";
                for (Member number : memberVars) {
                    if (number.chatId == chatId) {
                        number.menuString = menuBotSelection;
                    }
                }
                SendMediaGroup sendMediaGroup = new SendMediaGroup(chatId, new InputMediaPhoto(photos.getPhoto(1, menuBotSelection)));
                SendMessage sendMessage = new SendMessage(chatId, TextReader.read(3, 1));
                textSelect = new TextSelect(0, messageId, menuBotSelection);
                try {
                    for (Member number : memberVars) {

                        if (number.chatId == chatId) {
                            number.setVarMenu(1);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



                this.execute(sendMediaGroup);
                this.execute(sendMessage.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("<").callbackData("/<"), new InlineKeyboardButton(">").callbackData("/>")).addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));
            }
            case "/master" -> {
                menuBotSelection = "master";
                for (Member number : memberVars) {
                    if (number.chatId == chatId) {
                        number.menuString = menuBotSelection;
                    }
                }
                SendMediaGroup sendMediaGroup = new SendMediaGroup(chatId, new InputMediaPhoto(photos.getPhoto(1, menuBotSelection)));
                SendMessage sendMessage = new SendMessage(chatId, TextReader.read(4, 1));
                textSelect = new TextSelect(0, messageId, menuBotSelection);
                try {
                    for (Member number : memberVars) {

                        if (number.chatId == chatId) {
                            number.setVarMenu(1);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



                this.execute(sendMediaGroup);
                this.execute(sendMessage.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("<").callbackData("/<"), new InlineKeyboardButton(">").callbackData("/>")).addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));
            }
            case "/creation" -> {
                menuBotSelection = "creation";
                for (Member number : memberVars) {
                    if (number.chatId == chatId) {
                        number.menuString = menuBotSelection;
                    }
                }
                SendMediaGroup sendMediaGroup = new SendMediaGroup(chatId, new InputMediaPhoto(photos.getPhoto(1, menuBotSelection)));
                SendMessage sendMessage = new SendMessage(chatId, TextReader.read(5, 1));
                textSelect = new TextSelect(0, messageId, menuBotSelection);
                try {
                    for (Member number : memberVars) {

                        if (number.chatId == chatId) {
                            number.setVarMenu(1);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



                this.execute(sendMediaGroup);
                this.execute(sendMessage.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("<").callbackData("/<"),
                        new InlineKeyboardButton(">").callbackData("/>")).addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));
            }
            case "/update" -> {
                if (chatId == 435445422 || chatId == 696515725) {
                    photos = new Photos();
                    SendMessage sendMessage = new SendMessage(chatId, "Фотографии обновлены");
                    this.execute(sendMessage);

                } else {
                    SendMessage sendMessage = new SendMessage(chatId, "Неверная команда");
                    this.execute(sendMessage);
                }
            }
            case "/users" -> {
                if (chatId == 435445422 || chatId == 696515725) {
                    try {

                        SendMessage sendMessage = new SendMessage(chatId, mySql.COUNT());

                        this.execute(sendMessage);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }

                } else {
                    SendMessage sendMessage = new SendMessage(chatId, "Неверная команда");
                    this.execute(sendMessage);
                }
            }

            case "/try" ->{
                try {
                    mySql.COUNT();
                }catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case "/enroll" ->
                this.execute(new SendMessage(chatId,"Выбор записи").replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Мастер-класс").callbackData("send1"))
                        .addRow
                                (new InlineKeyboardButton("Абонемент/Курс").callbackData("send2"))
                        .addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));



            default -> {
                    SendMessage response = new SendMessage(chatId, "Неверная команда, нажмите /start");
                    this.execute(response);
                }
        }
    }
    private void menuSelect (long chatId, int numberText){
        if (textSelect == null) {
            textSelect = new TextSelect(0, messageId, menuBotSelection);
        }
        int i = 0;
        int j = 0;
        String z = null;
        if (memberVars.size() == 0)
            memberVars.add(new Member(chatId, menuBotSelection));
        boolean memberFind = false;
        for (Member memberVar : memberVars) {
            if (memberVar.chatId == chatId) {
                memberFind = true;
                break;
            }

        }
        if (!(memberFind)) {
            memberVars.add(new Member(chatId, menuBotSelection));

        }


        for (Member number : memberVars) {

            if (number.chatId == chatId) {
                number.setVar(numberText, photos.getPhotoSize(number.menuString));
                i = number.back();
                j = number.getMenuSelect();
                z = number.menuString;
            }
        }
        this.execute(new EditMessageMedia(chatId, messageId-1, new InputMediaPhoto(photos.getPhoto(i, z))));
        this.execute(textSelect.editMessageText(chatId, j, i, messageId).replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("<").callbackData("/<"),new InlineKeyboardButton(">").callbackData("/>"))
                .addRow(new InlineKeyboardButton("Главное меню").callbackData("/start"))));
    }
}

