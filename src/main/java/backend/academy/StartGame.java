package backend.academy;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.base.MazeRender;
import backend.academy.base.Renderer;
import backend.academy.generate.DFSMaze;
import backend.academy.generate.Generator;
import backend.academy.generate.kruskal.KruskalMaze;
import backend.academy.input.InputValid;
import backend.academy.solve.BFSSolverMaze;
import backend.academy.solve.DFSSolverMaze;
import backend.academy.solve.Solver;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public final class StartGame {

    private static PrintStream printStream = System.out;
    private static InputValid inputValid = new InputValid(new Scanner(System.in), printStream);



    public  void start() {

        inputValid.inputSizeOfMaze(); // запрос ввода координат
        inputValid.inputTypeGenerateMaze(); // запрос ввода координат
        inputValid.inputTypeSolveMaze(); //Запрос на запрос метода решения

        Generator typeMaze; //Тип генерации лабиринта
        Maze maze; // Наш лабиринт
        Renderer rendererMaze = new MazeRender();
        Solver solver;

        //Генерация каким методом будем генерировать
        if (inputValid.typeGenerateMaze() == InputValid.TypeGenerate.DFS) {
            typeMaze = new DFSMaze();
        } else {
            //maze
            typeMaze = new KruskalMaze();
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

        //Получали массив точек
        List<Coordinate> path = solver.solve(maze, inputValid.startPoint(), inputValid.finishPoint());

        //Печатаем Итог
        String printMazePath = rendererMaze.render(maze, path);

        if (printMazePath.equals(printMaze)) {
            printStream.println("Путь не был найден");
        } else {
            printStream.println(printMazePath);
        }
    }
}
