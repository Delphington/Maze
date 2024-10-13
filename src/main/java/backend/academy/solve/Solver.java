package backend.academy.solve;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

public interface Solver {
    int[][] MOVE = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
