package backend.academy;

import backend.academy.base.Cell;
import backend.academy.base.Maze;

public class Obstruction {
    private Maze maze;
    private int[][] matrix;
    private Cell[][] cells;

    public Cell[][] getCells() {
        return cells;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Obstruction(Maze maze) {
        this.maze = maze;
        matrix = new int[maze.height()][maze.width()];
    }

    int[] typeObs = {10, 10, 5, 10, 20, 10, 10};



    public void processing() {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.getCell(i, j).type == Cell.Type.WALL) {
                    matrix[i][j] = 0;
                } else {
                    int x = (int) (Math.random() * 5);
                    matrix[i][j] = typeObs[x]; //заполнение путем и разными препятсивями
                }
            }
        }

        //10 -  обычная цена прохода
        //5 - монетка
        //20 - песок
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void SetInisilization() {
        cells = new Cell[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] ==0){
                    cells[i][j] = new Cell(i, j, Cell.Type.WALL);
                }else if(matrix[i][j] ==20){
                    cells[i][j] = new Cell(i, j, Cell.Type.STONE);
                }else if(matrix[i][j] ==5){
                    cells[i][j] = new Cell(i, j, Cell.Type.MONEY);
                }else{
                    cells[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }

    }

}
