package Other;

import com.pengrad.telegrambot.request.EditMessageText;

public class TextSelect {
    int classNumber;
    int messageId;
    String menuSelection;

    public TextSelect(int classNumber, int messageId, String menuSelection) {

        this.classNumber = classNumber;
        this.messageId = messageId;
        this.menuSelection = menuSelection;


    }
    public EditMessageText editMessageText(long chatId, int menuNumber, int numberText, int messageId){
        this.messageId = messageId;
        this.classNumber = numberText;

        System.out.println("Edit Message " + menuNumber + "-" + classNumber + " " + chatId + " " + messageId);

        return new EditMessageText(chatId, this.messageId, TextReader.read(menuNumber, classNumber));

    }

}
