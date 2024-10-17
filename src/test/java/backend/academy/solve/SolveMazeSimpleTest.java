package backend.academy.solve;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.render.MazeRender;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolveMazeSimpleTest {

    private Cell[][] grid = {
        {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
            new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL), new Cell(0, 5, Cell.Type.WALL),
            new Cell(0, 6, Cell.Type.WALL)},

        {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE), new Cell(1, 2, Cell.Type.PASSAGE),
            new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.PASSAGE), new Cell(1, 5, Cell.Type.PASSAGE),
            new Cell(1, 6, Cell.Type.WALL)},

        {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE), new Cell(2, 2, Cell.Type.WALL),
            new Cell(2, 3, Cell.Type.WALL), new Cell(2, 4, Cell.Type.WALL), new Cell(2, 5, Cell.Type.WALL),
            new Cell(2, 5, Cell.Type.WALL)},

        {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE), new Cell(3, 2, Cell.Type.PASSAGE),
            new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.PASSAGE), new Cell(3, 5, Cell.Type.PASSAGE),
            new Cell(3, 6, Cell.Type.WALL)},

        {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.PASSAGE), new Cell(4, 2, Cell.Type.PASSAGE),
            new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL), new Cell(4, 5, Cell.Type.WALL),
            new Cell(4, 6, Cell.Type.WALL)},

        {new Cell(5, 0, Cell.Type.WALL), new Cell(5, 1, Cell.Type.WALL), new Cell(5, 2, Cell.Type.PASSAGE),
            new Cell(5, 3, Cell.Type.PASSAGE), new Cell(5, 4, Cell.Type.PASSAGE), new Cell(5, 5, Cell.Type.WALL),
            new Cell(5, 6, Cell.Type.WALL)},

        {new Cell(6, 0, Cell.Type.WALL), new Cell(6, 1, Cell.Type.PASSAGE), new Cell(6, 2, Cell.Type.PASSAGE),
            new Cell(6, 3, Cell.Type.WALL), new Cell(6, 4, Cell.Type.PASSAGE), new Cell(6, 5, Cell.Type.PASSAGE),
            new Cell(6, 6, Cell.Type.WALL)},

        {new Cell(7, 0, Cell.Type.WALL), new Cell(7, 1, Cell.Type.WALL), new Cell(7, 2, Cell.Type.WALL),
            new Cell(7, 3, Cell.Type.WALL), new Cell(7, 4, Cell.Type.WALL), new Cell(7, 5, Cell.Type.WALL),
            new Cell(7, 6, Cell.Type.WALL)}
    };

    @Test
    public void renderMazeTest() {
        Maze maze = new Maze(grid);
        MazeRender renderer = new MazeRender();
        String p = renderer.render(maze);

        String ans =
                  "⬛⬛⬛⬛⬛⬛⬛\n"
                + "⬛⬜⬜⬜⬜⬜⬛\n"
                + "⬛⬜⬛⬛⬛⬛⬛\n"
                + "⬛⬜⬜⬜⬜⬜⬛\n"
                + "⬛⬜⬜⬛⬛⬛⬛\n"
                + "⬛⬛⬜⬜⬜⬛⬛\n"
                + "⬛⬜⬜⬛⬜⬜⬛\n"
                + "⬛⬛⬛⬛⬛⬛⬛\n";

        assertThat(p).isEqualTo(ans);
    }

    @Test
    public void renderSimpleSolutionTest() {
        Maze maze = new Maze(grid);

        MazeRender renderer = new MazeRender();

        List<Coordinate> solutionPath = List.of(
            new Coordinate(1, 5),
            new Coordinate(1, 4),
            new Coordinate(1, 3),
            new Coordinate(1, 2),
            new Coordinate(1, 1),

            new Coordinate(2, 1),

            new Coordinate(3, 1),
            new Coordinate(3, 2),

            new Coordinate(4, 2),

            new Coordinate(5, 2),
            new Coordinate(5, 3),
            new Coordinate(5, 4),

            new Coordinate(6, 4),
            new Coordinate(6, 5)
        );

        String renderPath = renderer.render(maze, solutionPath);

        String ans =
            "⬛⬛⬛⬛⬛⬛⬛\n"
                + "⬛\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDD34⬛\n"
                + "⬛\uD83D\uDFE9⬛⬛⬛⬛⬛\n"
                + "⬛\uD83D\uDFE9\uD83D\uDFE9⬜⬜⬜⬛\n"
                + "⬛⬜\uD83D\uDFE9⬛⬛⬛⬛\n"
                + "⬛⬛\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9⬛⬛\n"
                + "⬛⬜⬜⬛\uD83D\uDFE9\uD83D\uDEA9⬛\n"
                + "⬛⬛⬛⬛⬛⬛⬛\n";

        assertThat(renderPath).isEqualTo(ans);
    }

    @Test
    public void solveSimpleTst() {
        Maze maze = new Maze(grid);
        Coordinate start = new Coordinate(1, 5);
        Coordinate finish = new Coordinate(6, 5);
        Solver solver = new BFSSolverMaze();
        List<Coordinate> path = solver.solve(maze, start, finish);
        assertThat(path.get(0)).isEqualTo(start);
        assertThat(path.get(path.size() - 1)).isEqualTo(finish);
    }
}


