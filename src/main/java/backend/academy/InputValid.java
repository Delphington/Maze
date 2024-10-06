package backend.academy;

import java.util.Optional;
import java.util.Scanner;

public class InputValid {
    private static int height = 0;
    private static int weight = 0;
    private static String line;

    private static int MIN_SIZE = 3;
    private static Scanner scanner = new Scanner(System.in);

    //Для проверки размера лабиринта
    public static boolean validSizeMaze(int size) {
        if (size < 0) {
            System.out.println("Размер не может быть отрицательным");
            return false;
        } else if (size < MIN_SIZE) {
            System.out.println("Минимальный размер лабиринта 3");
            return false;
        }

        return true;
    }

    public static Optional<Object> checkLineToInt(String str) {
        if (line == null || str.isEmpty()) {
            return Optional.empty();
        }
        try {
            int x = Integer.valueOf(str);
            return Optional.ofNullable(x);
        } catch (RuntimeException runtimeException) {
        }
        return Optional.empty();
    }

    public static void print() {
        System.out.println("heigth = " + height);
        System.out.println("weigt = " + weight);
    }

    public static void input() {
        //ввод ширины
        while (true) {
            System.out.println("Введите ширину лабирита: ");
            line = scanner.nextLine();
            Optional<Object> optional = checkLineToInt(line);
            if (optional.isPresent()) {
                if (validSizeMaze((int) optional.get())) {
                    weight = (int) optional.get();
                    break;
                }

            }
        }

        while (true) {
            System.out.println("Введите высоту лабирита: ");
            line = scanner.nextLine();
            Optional<Object> optional = checkLineToInt(line);
            if (optional.isPresent()) {
                if (validSizeMaze((int) optional.get())) {
                    height = (int) optional.get();
                    break;
                }

            }
        }
    }
}
