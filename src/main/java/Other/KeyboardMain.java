package Other;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
public class KeyboardMain {
    public static InlineKeyboardMarkup sendInlineKeyBoardMessageNewUser() {


        return new InlineKeyboardMarkup()
                .addRow
                        (new InlineKeyboardButton("Записаться").callbackData("/registration"))
                .addRow
                        (new InlineKeyboardButton("Прайс").callbackData("/price"))
                .addRow
                        (new InlineKeyboardButton("Тематические МК").callbackData("/tematic"))
                .addRow
                        (new InlineKeyboardButton("Мастер-Классы").callbackData("/master"))
                .addRow
                        (new InlineKeyboardButton("Курсы").callbackData("/courses"))
                .addRow
                        (new InlineKeyboardButton("Творческие мероприятия").callbackData("/creation"))
                .addRow
                        (new InlineKeyboardButton("Абонементы").callbackData("/subscription"));//возврат меню.
    }
    public static InlineKeyboardMarkup sendInlineKeyBoardMessage() {


        return new InlineKeyboardMarkup()

                .addRow
                        (new InlineKeyboardButton("Записаться").callbackData("/enroll"))
                .addRow
                        (new InlineKeyboardButton("Прайс").callbackData("/price"))
                .addRow
                        (new InlineKeyboardButton("Тематические МК").callbackData("/tematic"))
                .addRow
                        (new InlineKeyboardButton("Мастер-Классы").callbackData("/master"))
                .addRow
                        (new InlineKeyboardButton("Курсы").callbackData("/courses"))
                .addRow
                        (new InlineKeyboardButton("Творческие мероприятия").callbackData("/creation"))
                .addRow
                        (new InlineKeyboardButton("Абонементы").callbackData("/subscription"));//возврат меню.
    }
}