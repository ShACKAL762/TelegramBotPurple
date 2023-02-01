package Other;

public class Member {
    public long chatId;
    private int var;
    private int menuSelect;
    public String menuString;


    public Member(long chatId, String menuSelect) {
        this.menuString = menuSelect;
        if (this.menuString == null) {
            menuString = "tematic";
        }
        this.var = 1;
        this.chatId = chatId;

        switch (menuString) {
            case "tematic" -> this.menuSelect = 1;
            case "subscription" -> this.menuSelect = 2;
            case "course" -> this.menuSelect = 3;
            case "master" -> this.menuSelect = 4;
            case "creation" -> this.menuSelect = 5;
            default -> System.out.println("Ошибка");
        }

        System.out.println("Пользователь создан " + chatId);
        System.out.println("member menuselect - " + this.menuString);
    }



    public void setVar(int i, int photoSize){

        System.out.println(this.var +  " + " + i);
        this.var += i;

        if (this.var > photoSize){
            this.var = 1;
        }else if (this.var < 1){
            this.var = photoSize;
        }
        if (this.menuString == null)
            menuString = "tematic";
        switch (menuString) {
            case "tematic" -> this.menuSelect = 1;
            case "subscription" -> this.menuSelect = 2;
            case "course" -> this.menuSelect = 3;
            case "master" -> this.menuSelect = 4;
            case "creation" -> this.menuSelect = 5;
        }

    }
    public void setVarMenu (int i){
        this.var = i;

    }

    public int getMenuSelect(){
        System.out.println("getmenuSelect " + menuSelect);
        return this.menuSelect;
    }
    public int back (){
        return this.var;

    }

}
