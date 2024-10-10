package backend.academy;

import java.util.Optional;
import java.util.Scanner;

public class InputValid {

    private static int height = 0;
    private static int weight = 0;
    private static String line;
    private static Coordinate start;
    private static Coordinate finish;

    private static TypeGenerate typeGenerate;
    private static TypeSolve typeSolve;

    public static TypeGenerate getTypeGenerate() {
        return typeGenerate;
    }

    public static TypeSolve getTypeSolve() {
        return typeSolve;
    }

    public enum TypeGenerate {
        DFS,
        Kruskal
    }


    public enum TypeSolve {
        DFS,
        BFS
    }

    public static Coordinate getFinish() {
        return finish;
    }

    public static Coordinate getStart() {
        return start;
    }

    private static int MIN_SIZE = 3;
    private static Scanner scanner = new Scanner(System.in);

    public static int getHeight() {
        return height;
    }

    public static int getWeight() {
        return weight;
    }

    //Для проверки размера лабиринта
    private static boolean validSizeMaze(int size) {
        if (size < 0) {
            System.out.println("Размер не может быть отрицательным");
            return false;
        } else if (size < MIN_SIZE) {
            System.out.println("Минимальный размер лабиринта 3");
            return false;
        }
        return true;
    }

    public static void printInfo() {
        System.out.println("==========================================");
        System.out.println("Размер лабиринта: (" + height + ", " + weight + ")");
        System.out.println("Тип генерации: " + typeGenerate.name());
        System.out.println("Тип решения: " + typeSolve.name());
        System.out.println("Точка старта: (" + start.row() + ", " + start.col() + ")");
        System.out.println("Точка финиша: (" + finish.row() + ", " + finish.col() + ")");
        System.out.println("==========================================");
    }

    //Метод отвечающий за вбор генерации лабика
    public static void inputTypeGenerateMaze() {
        while (true) {
            System.out.print("[1] Генерация DFS\n" +
                "[2] Генерация Kraskal\n" +
                "Введите метод генерации лабиринта: ");
            line = scanner.nextLine().trim();
            if (line.matches("^[12]$")) {
                if (line.charAt(0) == '1') {
                    typeGenerate = TypeGenerate.DFS;
                    break;
                }
                typeGenerate = TypeGenerate.Kruskal;
                break;
            }
            System.out.println("Ошибка! Попробуйте еще раз");
        }

    }

    public static void voidTypeSolveMaze() {
        while (true) {
            System.out.print("[1] DFS\n" +
                "[2] BFS\n" +
                "Введите метод решение лабиринта: ");
            line = scanner.nextLine().trim();
            if (line.matches("^[12]$")) {
                if (line.charAt(0) == '1') {
                    typeSolve = TypeSolve.DFS;
                    break;
                }
                typeSolve = TypeSolve.BFS;
                break;
            }
            System.out.println("Ошибка! Попробуйте еще раз");
        }
    }

    public static void inputSizeOfMaze() {
        while (true) {
            System.out.print("Введите размер лабиринта {cow, col}: ");
            line = scanner.nextLine();
            Optional<Object> optional = checkLineSeparate(line.trim());
            if (optional.isPresent()) {
                int[] g = (int[]) optional.get();
                if (validSizeMaze(g[0]) && validSizeMaze(g[1])) {
                    weight = g[0];
                    height = g[1];
                    break;
                }
            } else {
                System.out.println("Вы ввели пустую строку или не число");
            }
        }
    }

    private static Optional<Object> checkLineSeparate(String str) {
        if (line == null || str.isEmpty()) {
            return Optional.empty();
        }
        try {

            String[] arr = str.split(" ");
            if (arr.length != 2) {
                return Optional.empty();
            }

            int x = Integer.valueOf(arr[0]);
            int y = Integer.valueOf(arr[1]);
            int[] ans = {x, y};
            return Optional.ofNullable(ans);
        } catch (RuntimeException runtimeException) {
        }
        return Optional.empty();
    }

    private static boolean validCoordinateSize(int cord, String str) {
        //случай когда точка "w" - координаты x
        if (str.equals("w")) {
            if (cord > 0 && cord <= weight) {
                return true;
            }
            System.out.println("Кооридината должна быть положительная и меньше размера лабиринта");
            return false;
        } else if (str.equals("h")) {
            if (cord > 0 && cord <= height) {
                return true;
            }
            System.out.println("Кооридината должна быть положительная и меньше размера лабиринта");
            return false;
        }
        return false;
    }

    //проверка на стенку
    private static boolean validCoordinateNotWall(Maze maze, Coordinate point) {
        if (maze.getCell(point.row(), point.col()) .type != Cell.Type.WALL) {
            return true;
        }
        System.out.println("К сожалению выбранная точка является стеной. Выбири другую");
        return false;
    }




    //Метод для выбора точек
    public static void inputCoordinatePoint(Maze maze, int numberPoint) {
        while (true) {
            int xF, yF;
            while (true) {
                System.out.print("Введите координаты точки " + numberPoint + " {cow, col}" + ": ");
                line = scanner.nextLine();
                Optional<Object> optional = checkLineSeparate(line.trim());
                if (optional.isPresent()) {
                    int[] g = (int[]) optional.get();
                    if ((validCoordinateSize(g[0], "w")) && (validCoordinateSize(g[1], "h"))) {
                        xF = g[0];
                        yF = g[1];
                        break;
                    }
                } else {
                    System.out.println("Вы ввели пустую строку или не число");
                }
            }
            if (numberPoint == 1) {
                start = new Coordinate(xF - 1, yF - 1);
                if (validCoordinateNotWall(maze, start)) {
                    break;
                }
            } else {
                finish = new Coordinate(xF - 1, yF - 1);
                if (validCoordinateNotWall(maze, finish)) {
                    break;
                }
            }
        }
    }
}
