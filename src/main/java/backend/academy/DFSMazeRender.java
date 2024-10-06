package backend.academy;

import java.util.List;

public class DFSMazeRender implements Renderer {

    @Override
    public String render(Maze maze) {
        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.getGrid();
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
        return null;
    }
}
