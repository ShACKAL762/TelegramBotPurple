import Other.Bot;

public class Main {

    private static final String BOT_TOKEN = "5445585320:AAHh5U1OHS4UhzMXi2nFpJXT1GWuEzif6qE";

    public static void main(String[] args) {
        Bot application = Bot.builder()
                .botToken(BOT_TOKEN)
                .build();


        application.run();

    }


}