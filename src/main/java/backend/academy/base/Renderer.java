package backend.academy.base;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;

import java.util.List;

public interface Renderer {

    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
