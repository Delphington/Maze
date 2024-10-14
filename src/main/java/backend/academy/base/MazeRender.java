package backend.academy.base;

import java.util.List;

@SuppressWarnings("MissingSwitchDefault")
public class MazeRender implements Renderer {
    public MazeRender() {

    }

    @Override
    public String render(Maze maze) {
        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.grid();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                switch (grid[x][y].type) {
                    case WALL -> builder.append('⬛');
                    case PASSAGE -> builder.append('⬜');
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
}
