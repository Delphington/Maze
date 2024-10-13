package backend.academy;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public final class StartGame {

    private StartGame() {

    }

    private static PrintStream printStream = System.out;
    private static InputValid inputValid = new InputValid(new Scanner(System.in), printStream);

    public static void start() {

        inputValid.inputSizeOfMaze(); // запрос ввода координат
        inputValid.inputTypeGenerateMaze(); // запрос ввода координат
        inputValid.voidTypeSolveMaze(); //Запрос на запрос метода решения

        Generator typeMaze; //Тип генерации лабиринта
        Maze maze; // Наш лабиринт
        Renderer rendererMaze;
        Solver solver;

        //Генерация каким методом будем генерировать
        if (inputValid.typeGenerateMaze() == InputValid.TypeGenerate.DFS) {
            typeMaze = new DFSMaze();
            rendererMaze = new MazeRender();
        } else {
            //maze
            typeMaze = new KruskalMaze();
            rendererMaze = new MazeRender();
        }

        //Каким методом будем решать
        if (inputValid.typeSolveMaze() == InputValid.TypeSolve.DFS) {
            solver = new DFSSolverMaze();
        } else {
            solver = new BFSSolverMaze();
        }

        //-------------------------------------
        maze = typeMaze.generate(inputValid.heightMaze(), inputValid.widthMaze());

        //Исходный, что будем выводить
        String printMaze = rendererMaze.render(maze);
        printStream.println(printMaze);

        //Запрос точек
        inputValid.inputCoordinatePoint(maze, 1);
        inputValid.inputCoordinatePoint(maze, 2);

        Coordinate point1 = inputValid.startPoint();
        Coordinate point2 = inputValid.finishPoint();

        //Вывод всей информации
        //  InputValid.printInfo();

        //Получали массив точек
        List<Coordinate> path = solver.solve(maze, point1, point2);

        //Печатаем Итог
        String printMazePath = rendererMaze.render(maze, path);

        if (printMazePath.equals(printMaze)) {
            printStream.println("Путь не был найден");
        } else {
            printStream.println(printMazePath);
        }
    }
}
