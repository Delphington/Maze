package backend.academy.generate;

import backend.academy.ObstacleConstance;
import backend.academy.base.Cell;
import backend.academy.base.Maze;
import lombok.Getter;

public class AddingObstruction implements ObstacleConstance {
    private Maze maze;

    private int[][] matrixCost;
    @Getter
    private Cell[][] cells;

    public AddingObstruction(Maze maze) {
        this.maze = maze;
        matrixCost = new int[maze.height()][maze.width()];
    }

    //SLAP :)
    public Maze generateNewMaze() {
        mazeToMatrixIntAndChangeValues();
        setCellsNewValues();
        return new Maze(cells);

    }

    private void mazeToMatrixIntAndChangeValues() {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.getCell(i, j).type == Cell.Type.WALL) {
                    matrixCost[i][j] = 0;
                } else {
                    int x = (int) (Math.random() * (TYPE_COST_CELL.length - 1));
                    matrixCost[i][j] = TYPE_COST_CELL[x]; //заполнение пути и разными препятсивями
                }
            }
        }
    }

    @SuppressWarnings("MissingSwitchDefault")
    private void setCellsNewValues() {
        cells = new Cell[matrixCost.length][matrixCost[0].length];
        for (int i = 0; i < matrixCost.length; i++) {
            for (int j = 0; j < matrixCost[0].length; j++) {
                switch (matrixCost[i][j]) {
                    case MEDAL_COST:
                        cells[i][j] = new Cell(i, j, Cell.Type.MEDAL);
                        break;
                    case PASSAGE_COST:
                        cells[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                        break;
                    case VIRUS_COST:
                        cells[i][j] = new Cell(i, j, Cell.Type.VIRUS);
                        break;
                    case WALL_COST:
                        cells[i][j] = new Cell(i, j, Cell.Type.WALL);
                        break;
                }
            }
        }
    }
}
