package Other;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Photos {
    private final List<String> tematicPhotos = new ArrayList<>();
    private final List<String> coursePhotos = new ArrayList<>();
    private final List<String> masterPhotos = new ArrayList<>();
    private final List<String> creationPhotos = new ArrayList<>();
    private final List<String> subscriptionPhotos = new ArrayList<>();

    public Photos() {
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\Tematic\\Photo.txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/Tematic/Photo.txt"));
            List<String> list = bufferedReader.lines().toList();
            for (int j = 1; j < list.size(); j += 3) {
                tematicPhotos.add(list.get(j));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\Courses\\Photo.txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/Courses/Photo.txt"));
            List<String> list = bufferedReader.lines().toList();
            for (int j = 1; j < list.size(); j += 3) {
                coursePhotos.add(list.get(j));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\Master\\Photo.txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/Master/Photo.txt"));
            List<String> list = bufferedReader.lines().toList();
            for (int j = 1; j < list.size(); j += 3) {
                masterPhotos.add(list.get(j));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\Tvorch\\Photo.txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/Tvorch/Photo.txt"));
            List<String> list = bufferedReader.lines().toList();
            for (int j = 1; j < list.size(); j += 3) {
                creationPhotos.add(list.get(j));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\cer97\\Desktop\\telejav\\Texts\\Abonement\\Photo.txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/Texts/Abonement/Photo.txt"));
            List<String> list = bufferedReader.lines().toList();
            for (int j = 1; j < list.size(); j += 3) {
                subscriptionPhotos.add(list.get(j));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getPhoto(int i, String menu) {
        if (menu == null) menu = "Ошибка";
        if (menu.equals("tematic")) {
            if (i < 1) i = tematicPhotos.size();
            if (i > tematicPhotos.size()) i = 1;
            return tematicPhotos.get(i - 1);

        }
        if (menu.equals("course")) {
            if (i < 1) i = coursePhotos.size();
            if (i > coursePhotos.size()) i = 1;
            return coursePhotos.get(i - 1);
        }
        if (menu.equals("master")) {
            if (i < 1) i = masterPhotos.size();
            if (i > masterPhotos.size()) i = 1;
            return masterPhotos.get(i - 1);
        }
        if (menu.equals("creation")) {
            if (i < 1) i = creationPhotos.size();
            if (i > creationPhotos.size()) i = 1;
            return creationPhotos.get(i - 1);
        }
        if (menu.equals("subscription")) {
            if (i < 1) i = subscriptionPhotos.size();
            if (i > subscriptionPhotos.size()) i = 1;
            return subscriptionPhotos.get(i - 1);
        }
        return "что то пошло не так";
    }
    public int getPhotoSize(String menu){
        if (menu == null)
            return 0;
        if (menu.equals("tematic"))
            return tematicPhotos.size() ;
        if (menu.equals("course"))
            return coursePhotos.size();
        if (menu.equals("master"))
            return masterPhotos.size() ;
        if (menu.equals("creation"))
            return creationPhotos.size();
        if (menu.equals("subscription"))
            return subscriptionPhotos.size();
        return 0;
    }
}



