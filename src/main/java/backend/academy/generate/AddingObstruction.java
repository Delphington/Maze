package backend.academy.generate;

import backend.academy.base.Cell;
import backend.academy.base.Maze;
import lombok.Getter;

public class AddingObstruction {
    private Maze maze;
    private final int roadCost = 10;
    private final int medalCost = 5;
    private final int virusCost = 20;
    private final int[] typeCostCell = {roadCost, roadCost, medalCost, roadCost, virusCost, roadCost};

    @Getter
    private int[][] matrixCost;
    @Getter
    private Cell[][] cells;

    public AddingObstruction(Maze maze) {
        this.maze = maze;
        matrixCost = new int[maze.height()][maze.width()];
    }

    public void generateNewMaze() {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.getCell(i, j).type == Cell.Type.WALL) {
                    matrixCost[i][j] = 0;
                } else {
                    int x = (int) (Math.random() * (typeCostCell.length - 1));
                    matrixCost[i][j] = typeCostCell[x]; //заполнение пути и разными препятсивями
                }
            }
        }
    }

    public void applyNewMaze() {
        cells = new Cell[matrixCost.length][matrixCost[0].length];
        for (int i = 0; i < matrixCost.length; i++) {
            for (int j = 0; j < matrixCost[0].length; j++) {
                switch (matrixCost[i][j]) {
                    case medalCost:
                        cells[i][j] = new Cell(i, j, Cell.Type.MEDAL);
                        break;
                    case roadCost:
                        cells[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                        break;
                    case virusCost:
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
