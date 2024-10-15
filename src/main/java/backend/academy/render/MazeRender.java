package backend.academy.render;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

@SuppressWarnings("MissingSwitchDefault")
public class MazeRender implements Renderer {

    public String generalRender(Maze maze, List<Coordinate> path) {
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
                    switch (grid[x][y].type) {
                        case WALL -> builder.append('⬛');
                        case PASSAGE -> builder.append('⬜');
                    }
                }
            }
            builder.append('\n');
        }

        return builder.toString();
    }

    @Override
    public String render(Maze maze) {
        return generalRender(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return generalRender(maze, path);
    }
}
