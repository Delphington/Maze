package backend.academy.generate;

import backend.academy.base.Cell;
import backend.academy.base.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Класс реализует генерацию лабиринта через поиск в глубину
 * Каждая клетка лабиринта или стена, или проход
 * */
public final class DFSMaze implements Generator {
    private final int doubleMove = 2;
    private final int zeroMove = 0;
    private final int variuos = 4;

    public DFSMaze() {
    }


    /**
     * Создает проход между текущей клеткой и следующей
     * @param current Текущая клетка
     * @param next    Следующая клетка
     * @param grid    Двумерный массив клеток (лабиринт)
     */
    private void createPassage(Cell current, Cell next, Cell[][] grid) {
        int newRow = (current.row + next.row) / 2; //Делится всегда хорошо, так как //мы через клетку
        int newCol = (current.col + next.col) / 2;
        grid[newRow][newCol].type = Cell.Type.PASSAGE;
        next.type = Cell.Type.PASSAGE;
    }

    /**
     * Получает список соседей для данной клетки
     *
     * @param cell Текущая клетка
     * @param grid Двумерный массив клеток (лабиринт)
     * @return Список непосещенных соседних клеток
     */
    private List<Cell> getUnvisitedNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighborCells = new ArrayList<>();
        int[] row = {-doubleMove, doubleMove, zeroMove, zeroMove};
        int[] col = {zeroMove, zeroMove, -doubleMove, doubleMove};

        for (int i = 0; i < variuos; i++) {
            int newRow = cell.row + row[i];
            int newCol = cell.col + col[i];

            if ((newRow >= 0) && (newRow < grid.length) && (newCol >= 0) && (newCol < grid[0].length)) {
                if (grid[newRow][newCol].type == Cell.Type.WALL) {
                    neighborCells.add(grid[newRow][newCol]);
                }
            }
        }
        Collections.shuffle(neighborCells);
        return neighborCells;
    }

    /**
     * Генерирует лабиринт заданной высоты и ширины(сразу приходят валидные данные)
     *
     * @param height Высота лабиринта
     * @param width  Ширина лабиринта
     * @return Сгенерированный лабиринт
     */
    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        //заполняем стенами
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(i, j, Cell.Type.WALL);
                grid[i][j] = cell;
            }
        }

        Cell first = grid[1][1]; //Начальная клетка без границы
        first.type = Cell.Type.PASSAGE;
        Deque<Cell> stack = new ArrayDeque<>();
        stack.add(first);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighborCells = getUnvisitedNeighbors(current, grid);
            if (!neighborCells.isEmpty()) {
                Cell next = neighborCells.get((int) (neighborCells.size() * Math.random()));
                createPassage(current, next, grid);
                stack.push(next);
            } else {
                stack.pop();
            }
        }

        return new Maze(grid);
    }
}
