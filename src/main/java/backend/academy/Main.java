package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
public class Main {
    public static void main(String[] args) {

        InputValid.inputSizeOfMaze(); // запрос ввода координат
        InputValid.inputTypeGenerateMaze(); // запрос ввода координат
        InputValid.voidTypeSolveMaze(); //Запрос на запрос метода решения


        Generator TypeMaze; //Тип генерации лабиринта
        Maze maze; // Наш лабиринт
        Renderer rendererMaze;
        Solver solver;

        //Генерация каким методом будем генерировать
        if(InputValid.getTypeGenerate() == InputValid.TypeGenerate.DFS){
            TypeMaze = new DFSMaze();
            rendererMaze = new MazeRender();
        }else{
            //maze
            TypeMaze = new DFSMaze();
            rendererMaze = new MazeRender();
        }

        //Каким методом будем решать
        if(InputValid.getTypeSolve() == InputValid.TypeSolve.DFS){
            solver = new DFSSolverMaze();
        }else {
            solver = new BFSSolverMaze();
        }



        //-------------------------------------
        maze = TypeMaze.generate(InputValid.getHeight(), InputValid.getWeight());


        //Исходный, что будем выводить
        String printMaze = rendererMaze.render(maze);
        System.out.println(printMaze);


        //Запрос точек
        InputValid.inputCoordinatePoint(maze, 1);
        InputValid.inputCoordinatePoint(maze, 2);




        Coordinate point1 = InputValid.getStart();
        Coordinate point2 = InputValid.getFinish();

        //Вывод всей информации
        InputValid.printInfo();


        //Получали массив точек
        List<Coordinate> path = solver.solve(maze, point1, point2);

        //Печатаем Итог
        String printMazePath = rendererMaze.render(maze, path);
        System.out.println(printMazePath);

    }
}
