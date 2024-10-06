package backend.academy;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {

        InputValid.input();
        InputValid.print();
        //Создали метод для генерации
        DFSMaze dfsMaze = new DFSMaze();
        //Создали сам лабик с координатами
        Maze maze = dfsMaze.generate(InputValid.getHeight(),InputValid.getHeight());

        //Создаем объект render
        DFSMazeRender dfsMazeRender = new DFSMazeRender();

        //Создаем рендер всего этого дела
        String printMaze = dfsMazeRender.render(maze);

        System.out.println(printMaze);
    }
}
