package backend.academy.render;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

@SuppressWarnings("MissingSwitchDefault")
public class MazeRender implements Renderer {

    @Override
    public String render(Maze maze) {
        return GeneralRender.generalRender(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return GeneralRender.generalRender(maze, path);
    }
}
