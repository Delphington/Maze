package backend.academy;

import lombok.experimental.UtilityClass;
import org.checkerframework.checker.units.qual.C;
import java.util.List;

@UtilityClass
public class Main {
    public static void main(String[] args) {

        InputValid.inputSizeOfMaze();
        InputValid.print();
        //Создали метод для генерации
        DFSMaze dfsMaze = new DFSMaze();
        //Создали сам лабик с координатами
        Maze maze = dfsMaze.generate(InputValid.getHeight(),InputValid.getWeight());

        //Создаем объект render
        DFSMazeRender dfsMazeRender = new DFSMazeRender();

        //Создаем рендер всего этого дела
        String printMaze = dfsMazeRender.render(maze);

        System.out.println(printMaze);




        InputValid.inputCoordinatePoint(maze,1);
        InputValid.inputCoordinatePoint(maze,2);


        Coordinate point1 = InputValid.getStart();
        System.out.println("Наша точка1 : " +point1.row() + " " + point1.col());

        Coordinate point2 = InputValid.getFinish();
        System.out.println("Наша точка2 : " +point2.row() + " " + point2.col());

        //Проверить если вторая точка меньше второй


        DFSSolverMaze dfsSolverMaze = new DFSSolverMaze();
        List<Coordinate> path = dfsSolverMaze.solve(maze,point1,point2);



    }
}
