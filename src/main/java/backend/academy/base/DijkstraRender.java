package backend.academy.base;

import java.util.List;

public class DijkstraRender implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.grid();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y].type == Cell.Type.WALL) {
                    builder.append('⬛');
                } else if (grid[x][y].type == Cell.Type.MONEY) {
                    builder.append('\uD83E').append('\uDD47');
                } else if (grid[x][y].type == Cell.Type.STONE) {
                    builder.append('\uD83E').append('\uDDA0');
                } else if (grid[x][y].type == Cell.Type.PASSAGE) {
                    builder.append('⬜');
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.grid();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                Coordinate temp = new Coordinate(x, y);
                if (path != null && path.contains(temp)) {
                    if (path.get(0).equals(temp)) {
                        builder.append('\uD83D').append('\uDD34'); //точка старта
                    } else if (path.get(path.size() - 1).equals(temp)) {
                        builder.append('\uD83D').append('\uDEA9');   //флаг точка финиша
                    } else {
                        builder.append('\uD83D').append('\uDFE9'); // зеленый
                    }

                } else {
                    if (grid[x][y].type == Cell.Type.WALL) {
                        builder.append('⬛');
                    } else if (grid[x][y].type == Cell.Type.MONEY) {
                        builder.append('\uD83E').append('\uDD47');
                    } else if (grid[x][y].type == Cell.Type.STONE) {
                        builder.append('\uD83E').append('\uDDA0');
                    } else if (grid[x][y].type == Cell.Type.PASSAGE) {
                        builder.append('⬜');
                    }
                }
            }
            builder.append('\n');
        }
        return builder.toString();

    }
}
