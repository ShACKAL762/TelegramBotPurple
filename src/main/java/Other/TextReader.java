package Other;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TextReader {
    public static String read(int menuNumber, int textNumber) {

        String menu = null;
        switch (menuNumber){
            case 1 ->
                    menu = "Tematic";
            case 2 ->
                    menu = "Abonement";
            case 3 ->
                    menu = "Courses";
            case 4 ->
                    menu = "Master";
            case 5 ->
                    menu = "Tvorch";

        }
        //System.out.println("Папка файла = " + menu);
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\"+ menu + "\\" +  textNumber + ".txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/"+ menu + "/" +  textNumber + ".txt"));
            List<String> list = bufferedReader.lines().toList();
            StringBuilder textBuilder = new StringBuilder(list.get(0));
            for(int i = 1; i <= list.size()-1; i++){
                textBuilder.append("\n").append(list.get(i));
            }
            //System.out.println(text);
            return textBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
