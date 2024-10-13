package backend.academy.input;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import lombok.Getter;

/**
 * Класс InputValid отвечает за ввод параметров для генерации и решения лабиринта.
 * Он предоставляет методы для получения размеров лабиринта, типов генерации и решения
 * и проверяет корректность введенных данных.
 */

@Getter
public final class InputValid implements Constants {

    private int heightMaze = 0;
    private int widthMaze = 0;

    private Coordinate startPoint;
    private Coordinate finishPoint;

    private TypeGenerate typeGenerateMaze;
    private TypeSolve typeSolveMaze;

    private String line;
    private int xF = 0;
    private int yF = 0;

    private Scanner scanner;
    private PrintStream printStream;

    public enum TypeGenerate {
        DFS,
        Kruskal
    }

    public enum TypeSolve {
        DFS,
        BFS
    }

    /**
     * Конструктор класса InputValid.
     *
     * @param scanner  Объект Scanner для ввода данных.
     * @param printStream Объект PrintStream для вывода данных.
     */
    public InputValid(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
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


    /**
     * Метод для ввода размеров лабиринта.
     */
    public void inputSizeOfMaze() {
        while (true) {
            printStream.print("Введите размер лабиринта {cow, col}: ");
            line = scanner.nextLine();
            Optional<Object> optional = checkLineSeparate(line.trim());

            try {
                int[] g = (int[]) optional.orElseThrow(() -> new IllegalArgumentException(INCORRECT_INPUT));
                if (isValidSizeMaze(g[0]) && isValidSizeMaze(g[1])) {
                    widthMaze = g[0];
                    heightMaze = g[1];
                    break;
                } else {
                    printStream.println(INCORRECT_INPUT);
                }
            } catch (IllegalArgumentException e) {
                printStream.println(INCORRECT_INPUT);
            }

        }
    }


    /**
     * Метод для выбора типа генерации лабиринта.
     */
    public void inputTypeGenerateMaze() {
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


    /**
     * Метод для выбора типа решения лабиринта.
     */
    public void inputTypeSolveMaze() {
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



    /**
     * Проверяет, является ли размер лабиринта допустимым.
     *
     * @param size Размер лабиринта.
     * @return true, если размер допустим; false в противном случае.
     */
    private boolean isValidSizeMaze(int size) {
        if (size < 0) {
            printStream.println("Размер не может быть отрицательным");
            return false;
        } else if (size < MIN_SIZE) {
            printStream.println("Минимальный размер лабиринта 3");
            return false;
        }
        return true;
    }


    /**
     * Проверяет строку на наличие разделителей и возвращает массив целых чисел.
     *
     * @param str Строка для проверки.
     * @return Optional с массивом целых чисел, если строка корректна; иначе Optional.empty().
     */
    protected Optional<Object> checkLineSeparate(String str) {
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

    /**
     * Проверяет, являются ли координата валидная
     *
     * @param cord   введенная координата
     * @param str    модфификатор, по которому сравнивают допустим размер по высоте/ширине
     * @return true, если координата корректна; false в противном случае.
     */
    private boolean isValidCoordinateSize(int cord, String str) {
        //случай когда точка "w" - координата X
        if (str.equals("w") && (cord > 0) && (cord <= widthMaze)) {
            return true;
        } else if (str.equals("h") && (cord > 0) && (cord <= heightMaze)) {
            return true;
        }
        printStream.println("Кооридината должна быть положительная и меньше размера лабиринта");
        return false;
    }

    /**
     * Проверяет, является ли указанная координата стеной.
     *
     * @param maze   Лабиринт, в котором проверяется координата.
     * @param point  Координата для проверки.
     * @return true, если координата не является стеной; false в противном случае.
     */
    private boolean isValidCoordinateNotWall(Maze maze, Coordinate point) {
        if (maze.getCell(point.row(), point.col()).type != Cell.Type.WALL) {
            return true;
        }
        printStream.println("К сожалению выбранная точка является стеной. Выбири другую");
        return false;
    }

    /**
     * Метод для выбора координат точек (стартовой и конечной).
     *
     * @param maze       Лабиринт, в котором выбираются точки.
     * @param numberPoint Номер точки (1 для стартовой, 2 для конечной).
     */
    public void inputCoordinatePoint(Maze maze, int numberPoint) {
        while (true) {
            while (true) {
                printStream.print("Введите координаты точки " + numberPoint + " {cow, col}" + ": ");
                line = scanner.nextLine();
                Optional<Object> optional = checkLineSeparate(line.trim());
                try {
                    int[] g = (int[]) optional.orElseThrow(() -> new IllegalArgumentException(INCORRECT_INPUT));

                    if (isValidCoordinateSize(g[0], "w") && isValidCoordinateSize(g[1], "h")) {
                        xF = g[0];
                        yF = g[1];
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    printStream.println(INCORRECT_INPUT);
                }

            }
            if (numberPoint == 1) {
                startPoint = new Coordinate(xF - 1, yF - 1);
                if (isValidCoordinateNotWall(maze, startPoint)) {
                    break;
                }
            } else {
                finishPoint = new Coordinate(xF - 1, yF - 1);
                if (isValidCoordinateNotWall(maze, finishPoint)) {
                    break;
                }
            }
        }
    }
}
