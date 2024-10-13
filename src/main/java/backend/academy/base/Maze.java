package backend.academy.base;

import lombok.Getter;


/**
 * Класс определеяющий само поле лабиринта
 * */

@Getter
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(Cell[][] grid) {
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}
