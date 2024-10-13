package backend.academy;

import backend.academy.base.Cell;
import backend.academy.base.Maze;
import java.util.Arrays;

public class Obstruction {
    private Maze maze;
    private int[][] matrix;

    public Obstruction(Maze maze) {
        this.maze = maze;
        matrix = new int[maze.height()][maze.width()];
    }

    int[] typeObs = {2, 2, 1, 2, 3, 2};

    public void print() {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.getCell(i, j).type == Cell.Type.WALL) {
                    matrix[i][j] = 5;
                } else {
                    int x =  (int)(Math.random() * 5);
                    matrix[i][j] = typeObs[x];
                }
            }
        }

        //------------------------------
        //Болото - 3
        //Монетка - 1
        //обычный путь - 2
        //Стена - 5

        System.out.println("==========================");
        System.out.println('⬛');
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        //------------------------------

    }
}
