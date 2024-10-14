package backend.academy;

import backend.academy.base.Cell;
import backend.academy.base.Maze;
import lombok.Getter;

public class Obstruction {
    private Maze maze;
    //10 -  обычная цена прохода
    //5 - монетка
    //20 - песок
    private final int[] typeCostCell = {10, 10, 5, 10, 20, 10};

    @Getter
    private int[][] matrixCost;
    @Getter
    private Cell[][] cells;

    public Obstruction(Maze maze) {
        this.maze = maze;
        matrixCost = new int[maze.height()][maze.width()];
    }

    public void generateNewMaze() {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.getCell(i, j).type == Cell.Type.WALL) {
                    matrixCost[i][j] = 0;
                } else {
                    int x = (int) (Math.random() * 6);
                    matrixCost[i][j] = typeCostCell[x]; //заполнение пути и разными препятсивями
                }
            }
        }
    }

//    public void printMatrix() {
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
    public void applyNewMaze() {
        cells = new Cell[matrixCost.length][matrixCost[0].length];
        for (int i = 0; i < matrixCost.length; i++) {
            for (int j = 0; j < matrixCost[0].length; j++) {
                switch (matrixCost[i][j]) {
                    case 5:
                        cells[i][j] = new Cell(i, j, Cell.Type.MONEY);
                        break;
                    case 10:
                        cells[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                        break;
                    case 20:
                        cells[i][j] = new Cell(i, j, Cell.Type.VIRUS);
                        break;
                    default:
                        cells[i][j] = new Cell(i, j, Cell.Type.WALL);
                        break;
                }
            }
        }
    }
}
