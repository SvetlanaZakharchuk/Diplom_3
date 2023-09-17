package stellarsburgers.Utils;

import java.util.Random;

public class Utils {
    public static String randomString(int length) {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i < length; ++i) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (float) (rightLimit - leftLimit + 1));
            buffer.append(Character.toChars(randomLimitedInt));
        }

        return buffer.toString();
    }

    //генерируем почту
    public static String randomEmail() {
        return (randomString(8) + "@" + randomString(4) + "." + randomString((2)));
    }

    //генерируем пароль
    public static String randomPassword() {
        return randomString(10);
    }

    //генерируем имя пользователя
    public static String randomName() {
        return randomString(8);
    }
}
