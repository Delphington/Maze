package backend.academy.render;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

public interface Renderer {

    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);

    StringBuilder renderPathCell(List<Coordinate> path, Coordinate temp, StringBuilder builder);

    StringBuilder renderCell(Cell cell, StringBuilder builder);
}
