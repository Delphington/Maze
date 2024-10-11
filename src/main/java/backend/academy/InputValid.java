package backend.academy;

import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public final class InputValid implements Constants {
    private InputValid() {
    }

    private static int heightMaze = 0;
    private static int widthMaze = 0;

    private static Coordinate startPoint;
    private static Coordinate finishPoint;

    private static TypeGenerate typeGenerateMaze;
    private static TypeSolve typeSolveMaze;

    private static String line;
    private static int xF = 0;
    private static int yF = 0;

    private static Scanner scanner = new Scanner(System.in);
    private static PrintStream printStream = System.out;

    public enum TypeGenerate {
        DFS,
        Kruskal
    }

    public enum TypeSolve {
        DFS,
        BFS
    }

    public static TypeGenerate getTypeGenerate() {
        return typeGenerateMaze;
    }

    public static TypeSolve getTypeSolve() {
        return typeSolveMaze;
    }

    public static Coordinate getFinish() {
        return finishPoint;
    }

    public static Coordinate getStart() {
        return startPoint;
    }

    public static int getHeight() {
        return heightMaze;
    }

    public static int getWeight() {
        return widthMaze;
    }

    //Для проверки размера лабиринта
    private static boolean validSizeMaze(int size) {
        if (size < 0) {
            printStream.println("Размер не может быть отрицательным");
            return false;
        } else if (size < MIN_SIZE) {
            printStream.println("Минимальный размер лабиринта 3");
            return false;
        }
        return true;
    }

    @SuppressWarnings("JavadocMissingLeadingAsterisk")
    /**
     public static void printInfo() {
     printStream.println("==========================================");
     printStream.println("Размер лабиринта: (" + heightMaze + ", " + widthMaze + ")");
     printStream.println("Тип генерации: " + typeGenerateMaze.name());
     printStream.println("Тип решения: " + typeSolveMaze.name());
     printStream.println("Точка старта: (" + startPoint.row() + ", " + startPoint.col() + ")");
     printStream.println("Точка финиша: (" + finishPoint.row() + ", " + finishPoint.col() + ")");
     printStream.println("==========================================");
     }
     */

    //Метод отвечающий за вбор генерации лабика
    public static void inputTypeGenerateMaze() {
        while (true) {
            printStream.print("[1] Генерация DFS\n"
                + "[2] Генерация Kraskal\n"
                + "Введите метод генерации лабиринта: ");
            line = scanner.nextLine().trim();
            if (line.matches(TYPES_REG)) {
                if (line.charAt(0) == '1') {
                    typeGenerateMaze = TypeGenerate.DFS;
                    break;
                }
                typeGenerateMaze = TypeGenerate.Kruskal;
                break;
            }
            printStream.println(WARNING_INPUT);
        }
    }

    public static void voidTypeSolveMaze() {
        while (true) {
            printStream.print("[1] DFS\n"
                + "[2] BFS\n"
                + "Введите метод решение лабиринта: ");
            line = scanner.nextLine().trim();
            if (line.matches(TYPES_REG)) {
                if (line.charAt(0) == '1') {
                    typeSolveMaze = TypeSolve.DFS;
                    break;
                }
                typeSolveMaze = TypeSolve.BFS;
                break;
            }
            printStream.println(WARNING_INPUT);
        }
    }

    public static void inputSizeOfMaze() {
        while (true) {
            printStream.print("Введите размер лабиринта {cow, col}: ");
            line = scanner.nextLine();
            Optional<Object> optional = checkLineSeparate(line.trim());
            if (optional.isPresent()) {
                int[] g = (int[]) optional.get();
                if (validSizeMaze(g[0]) && validSizeMaze(g[1])) {
                    widthMaze = g[0];
                    heightMaze = g[1];
                    break;
                }
            } else {
                printStream.println(INCORRECT_INPUT);
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

        //случай когда точка "w" - координата X
        if (str.equals("w") && (cord > 0) && (cord <= widthMaze)) {
            return true;
        } else if (str.equals("h") && (cord > 0) && (cord <= heightMaze)) {
            return true;
        }
        printStream.println("Кооридината должна быть положительная и меньше размера лабиринта");
        return false;
    }

    //проверка на стенку
    private static boolean validCoordinateNotWall(Maze maze, Coordinate point) {
        if (maze.getCell(point.row(), point.col()).type != Cell.Type.WALL) {
            return true;
        }
        printStream.println("К сожалению выбранная точка является стеной. Выбири другую");
        return false;
    }

    //Метод для выбора точек
    public static void inputCoordinatePoint(Maze maze, int numberPoint) {
        while (true) {
            while (true) {
                printStream.print("Введите координаты точки " + numberPoint + " {cow, col}" + ": ");
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
                    printStream.println(INCORRECT_INPUT);
                }
            }
            if (numberPoint == 1) {
                startPoint = new Coordinate(xF - 1, yF - 1);
                if (validCoordinateNotWall(maze, startPoint)) {
                    break;
                }
            } else {
                finishPoint = new Coordinate(xF - 1, yF - 1);
                if (validCoordinateNotWall(maze, finishPoint)) {
                    break;
                }
            }
        }
    }
}
