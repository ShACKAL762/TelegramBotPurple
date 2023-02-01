import Other.Bot;

public class Main {

    private static final String BOT_TOKEN = "";

    public static void main(String[] args) {
        Bot application = Bot.builder()
                .botToken(BOT_TOKEN)
                .build();


        application.run();

    }


}