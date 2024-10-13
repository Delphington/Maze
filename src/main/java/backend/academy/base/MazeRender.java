package backend.academy.base;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.base.Renderer;
import java.util.List;

public class MazeRender implements Renderer {

    @Override
    public String render(Maze maze) {
        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.grid();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {

                if (grid[x][y].type == Cell.Type.WALL) {
                    builder.append('#');
                } else {
                    builder.append(' ');
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
                        builder.append("S");
                    } else if (path.get(path.size() - 1).equals(temp)) {
                        builder.append("F");
                    } else {
                        builder.append('.');
                    }

                } else {
                    if (grid[x][y].type == Cell.Type.WALL) {
                        builder.append('#');
                    } else {
                        builder.append(' ');
                    }
                }
            }
            builder.append('\n');
        }

        return builder.toString();
    }
}
