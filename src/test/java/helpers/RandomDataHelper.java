package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public  class RandomDataHelper {
    public static HashMap<String, String> clientParamRandom = new HashMap<String, String>();

    private static final String SYMBOLS = "0123456789.,()      ";
    private static final String NUMBERS = "0123456789";
    private static final String ENG_BIG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENG_SMALL = "abcdefghijklmnopqrstuvwxyz";
    private static final String RUS_BIG = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФЧЦЧШЩЫЬЪЭЮЯ";
    private static final String RUS_SMALL = "абвгдеёжзийклмнопрстуфхцчшщыьъэюя";
    private static Random rnd = new Random(TestHelper.getCurrentTime());

    public enum randomType {
        STR, ENGSTR, ENG_STR, RURSTR, RUR_STR, NUM, ENGNUM, RUS_SMALL_STR, ENG, RUS;
    }

    public static String randomString(randomType typ, int len) {
        StringBuilder sb = new StringBuilder(len);
        String alphanum = "";
        switch (typ) {
            case STR:
                alphanum = SYMBOLS + ENG_BIG + ENG_SMALL + RUS_BIG + RUS_SMALL;
                break;
            case ENGSTR:
                alphanum = SYMBOLS + ENG_BIG;
                break;
            case ENG_STR:
                alphanum = SYMBOLS + ENG_SMALL + ENG_BIG;
                break;
            case ENG:
                alphanum = ENG_SMALL + ENG_BIG;
                break;
            case RURSTR:
                alphanum = SYMBOLS + RUS_BIG;
                break;
            case RUR_STR:
                alphanum = SYMBOLS + RUS_BIG + RUS_SMALL;
                break;
            case NUM:
                alphanum = NUMBERS;
                break;
            case ENGNUM:
                alphanum = NUMBERS + ENG_SMALL;
                break;
            case RUS:
                alphanum = RUS_BIG + RUS_SMALL;
                break;
            case RUS_SMALL_STR:
                alphanum = RUS_SMALL;
                len = len - 1;
                break;
        }

        for (int i = 0; i < len; i++)
            sb.append(alphanum.charAt(rnd.nextInt(alphanum.length())));
        if (typ == randomType.RUS_SMALL_STR) {
            sb.insert(0, "" + RUS_BIG.charAt(rnd.nextInt(RUS_BIG.length())), 0, 1);
        }
        return sb.toString();
    }

    public static void randomBirthDate(String key){
        Random random = new Random();
        int minDay = (int) LocalDate.of(1985, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(1990,11,1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate localDate = LocalDate.ofEpochDay(randomDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(localDate);
        clientParamRandom.put(key, date);
    }

    public static void randomFIO(String key) {
        Random random = new Random();
        List<String> list = null;
        switch (key) {
            case "Фамилия":
                list = Arrays.asList("Тестов", "Кексов", "Остов", "Гостов", "Постов", "Шустов", "Кустов", "Пестов", "Лостов", "Кастов");
                break;
            case "Имя":
                list = Arrays.asList("Тест", "Кекс", "Ост", "Гост", "Пост", "Шуст", "Куст", "Пест", "Лост", "Каст");
                break;
            case "Отчество":
                list = Arrays.asList("Тестович", "Кексович", "Остович", "Гостович", "Постович", "Шустович", "Кустович", "Пестович", "Лостович", "Кастович");
                break;
        }
        String fio = list.get(random.nextInt(list.size()));
        clientParamRandom.put(key, fio);
    }
}
