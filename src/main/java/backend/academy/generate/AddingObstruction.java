package backend.academy.generate;

import backend.academy.base.Cell;
import backend.academy.base.Maze;
import backend.academy.base.ObstacleConstance;

/**
 * Класс AddingObstruction отвечает за добавление препятствий в лабиринт, добавляет
 * награды и вирусы
 * AddingObstruction преобразует лабиринт в матрицу с числами, изменяет числа, где есть
 * проход и преобразует в новый лабиринт
 */
public class AddingObstruction implements ObstacleConstance {
    private Maze maze;
    private int[][] matrixCost;
    private Cell[][] cells;

    /**
     * Конструктор класса AddingObstruction.
     *
     * @param maze Лабиринт, в который будут добавлены препятствия.
     */
    public AddingObstruction(Maze maze) {
        this.maze = maze;
        matrixCost = new int[maze.height()][maze.width()];
    }


    /**
     * Генерирует новый лабиринт с добавленными препятствиями.
     *
     * @return Новый экземпляр класса Maze с обновленными ячейками.
     */
    public Maze generateNewMaze() {
        //SLAP :)
        mazeToMatrixIntAndChangeValues();
        setCellsNewValues();
        return new Maze(cells);
    }
    /**
     * Преобразует лабиринт в матрицу целых чисел и изменяет значения ячеек.
     * Ячейки типа WALL получают значение 0, остальные заполняются случайными
     * значениями
     */

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

    /**
     * Устанавливает новые значения ячеек на основе матрицы стоимостей.
     */
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
