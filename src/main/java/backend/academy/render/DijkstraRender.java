package backend.academy.render;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

/**
 * Класс DijkstraRender реализует интерфейс Renderer и делает визуализацию лабиринта
 */

@SuppressWarnings("MissingSwitchDefault")
public class DijkstraRender implements Renderer {

    @Override
    public StringBuilder renderCell(Cell cell, StringBuilder builder) {
        switch (cell.type) {
            case WALL -> builder.append('⬛');
            case MEDAL -> builder.append('\uD83E').append('\uDD47');
            case VIRUS -> builder.append('\uD83E').append('\uDDA0');
            case PASSAGE -> builder.append('⬜');
            // Добавьте другие типы ячеек, если необходимо
        }
        return builder;
    }

    @Override
    public StringBuilder renderPathCell(List<Coordinate> path, Coordinate temp, StringBuilder builder) {
        if (path.get(0).equals(temp)) {
            builder.append('\uD83D').append('\uDD34'); // точка старта
        } else if (path.get(path.size() - 1).equals(temp)) {
            builder.append('\uD83D').append('\uDEA9'); // флаг точка финиша
        } else {
            builder.append('\uD83D').append('\uDFE9'); // зеленый
        }
        return builder;
    }


    private String generalRender(Maze maze, List<Coordinate> path) {

        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.grid();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                Coordinate temp = new Coordinate(x, y);
                if (path != null && path.contains(temp)) {
                    renderPathCell(path, temp, builder);
                } else {
                    renderCell(grid[x][y], builder);
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }


    /**
     * Отображает лабиринт в текстовом виде без пути.
     *
     * @param maze Лабиринт, который нужно отобразить.
     * @return Строка, представляющая визуализацию лабиринта.
     */

    @Override
    public String render(Maze maze) {
        return generalRender(maze, null);
    }

    /**
     * Отображает лабиринт с отмеченным путем
     *
     * @param maze Лабиринт, который нужно отобразить
     * @param path Список координат, представляющий путь в лабиринте
     * @return Строка, представляющая визуализацию лабиринта с отмеченным путем.
     */
    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return generalRender(maze, path);

    }
}
