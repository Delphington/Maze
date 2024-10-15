package backend.academy;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.generate.AddingObstruction;
import backend.academy.generate.DFSMaze;
import backend.academy.generate.Generator;
import backend.academy.generate.kruskal.KruskalMaze;
import backend.academy.input.InputValid;
import backend.academy.render.DijkstraRender;
import backend.academy.render.MazeRender;
import backend.academy.render.Renderer;
import backend.academy.solve.BFSSolverMaze;
import backend.academy.solve.DFSSolverMaze;
import backend.academy.solve.DijkstraSolverMaze;
import backend.academy.solve.Solver;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public final class StartGame {

    private static PrintStream printStream = System.out;
    private static InputValid inputValid = new InputValid(new Scanner(System.in), printStream);

    public void start() {

        inputValid.inputSizeOfMaze(); // запрос ввода координат
        inputValid.inputTypeGenerateMaze(); // запрос ввода координат
        inputValid.inputTypeSolveMaze(); //Запрос на запрос метода решения

        Generator typeMaze; //Тип генерации лабиринта
        Maze maze; // Наш лабиринт

        Renderer rendererMaze;
        Solver solver;
        List<Coordinate> path;

        AddingObstruction obstruction;

        //Каким методом будем генерировать
        switch (inputValid.typeGenerateMaze()) {
            case DFS -> typeMaze = new DFSMaze();
            case Kruskal -> typeMaze = new KruskalMaze();
            default -> typeMaze = new KruskalMaze();
        }
        //Генерируем
        maze = typeMaze.generate(inputValid.heightMaze(), inputValid.widthMaze());

        //Каким методом будем решать
        switch (inputValid.typeSolveMaze()) {
            case InputValid.TypeSolve.DFS:
                solver = new DFSSolverMaze();
                rendererMaze = new MazeRender();
                break;

            case InputValid.TypeSolve.BFS:
                solver = new BFSSolverMaze();
                rendererMaze = new MazeRender();
                break;

            case InputValid.TypeSolve.DIJKSTRA:
                solver = new DijkstraSolverMaze();
                obstruction = new AddingObstruction(maze);
                maze = obstruction.generateNewMaze();
                rendererMaze = new DijkstraRender();
                break;
            default:
                solver = new DijkstraSolverMaze();
                obstruction = new AddingObstruction(maze);
                maze = obstruction.generateNewMaze();
                rendererMaze = new DijkstraRender();
                break;
        }

        //Исходный, что будем выводить
        String printMaze = rendererMaze.render(maze);
        printStream.println(printMaze);

        //Запрос точек
        inputValid.inputCoordinatePoint(maze, 1);
        inputValid.inputCoordinatePoint(maze, 2);

        //Получали массив точек
        path = solver.solve(maze, inputValid.startPoint(), inputValid.finishPoint());

        //Печатаем Итог
        String printMazePath = rendererMaze.render(maze, path);

        if (printMazePath.equals(printMaze)) {
            printStream.println("Путь не был найден");
        } else {
            printStream.println(printMazePath);
        }

    }
}
