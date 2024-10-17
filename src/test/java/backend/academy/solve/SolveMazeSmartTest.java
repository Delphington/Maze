package backend.academy.solve;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.render.DijkstraRender;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolveMazeSmartTest {
    private Cell[][] grid = {
        {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
            new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL), new Cell(0, 5, Cell.Type.WALL),
            new Cell(0, 6, Cell.Type.WALL)},

        {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE), new Cell(1, 2, Cell.Type.VIRUS),
            new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL), new Cell(1, 5, Cell.Type.WALL),
            new Cell(1, 6, Cell.Type.WALL)},

        {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.MEDAL), new Cell(2, 2, Cell.Type.WALL),
            new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL), new Cell(2, 5, Cell.Type.WALL),
            new Cell(2, 5, Cell.Type.WALL)},



        {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE), new Cell(3, 2, Cell.Type.PASSAGE),
            new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.PASSAGE), new Cell(3, 5, Cell.Type.MEDAL),
            new Cell(3, 6, Cell.Type.WALL)},

        {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
            new Cell(4, 3, Cell.Type.PASSAGE), new Cell(4, 4, Cell.Type.WALL), new Cell(4, 5, Cell.Type.PASSAGE),
            new Cell(4, 6, Cell.Type.WALL)},



        {new Cell(5, 0, Cell.Type.WALL), new Cell(5, 1, Cell.Type.WALL), new Cell(5, 2, Cell.Type.WALL),
            new Cell(5, 3, Cell.Type.VIRUS), new Cell(5, 4, Cell.Type.PASSAGE), new Cell(5, 5, Cell.Type.PASSAGE),
            new Cell(5, 6, Cell.Type.WALL)},


        {new Cell(7, 0, Cell.Type.WALL), new Cell(7, 1, Cell.Type.WALL), new Cell(7, 2, Cell.Type.WALL),
            new Cell(7, 3, Cell.Type.WALL), new Cell(7, 4, Cell.Type.WALL), new Cell(7, 5, Cell.Type.WALL),
            new Cell(7, 6, Cell.Type.WALL)}
    };

    @Test
    public void renderMazeTest() {
        Maze maze = new Maze(grid);
        DijkstraRender renderer = new DijkstraRender();
        String p = renderer.render(maze);
        //  \uD83E\uDDA0
        String ans =
                  "⬛⬛⬛⬛⬛⬛⬛\n"
                + "⬛⬜\uD83E\uDDA0⬜⬛⬛⬛\n" // вирус
                + "⬛\uD83E\uDD47⬛⬜⬛⬛⬛\n" //медаль
                + "⬛⬜⬜⬜⬜\uD83E\uDD47⬛\n" //медаль
                + "⬛⬛⬛⬜⬛⬜⬛\n"
                + "⬛⬛⬛\uD83E\uDDA0⬜⬜⬛\n" //вирус
                + "⬛⬛⬛⬛⬛⬛⬛\n";

//              "⬛⬛⬛⬛⬛⬛⬛\n"
//            + "⬛⬜⬜⬜⬛⬛⬛\n"
//            + "⬛⬜⬛⬜⬛⬛⬛\n"
//            + "⬛⬜⬜⬜⬜⬜⬛\n"
//            + "⬛⬛⬛⬜⬛⬜⬛\n"
//            + "⬛⬛⬛⬜⬜⬜⬛\n"
//            + "⬛⬛⬛⬛⬛⬛⬛\n";

        assertThat(p).isEqualTo(ans);
    }


    @Test
    public void renderSmartSolutionTest() {
        Maze maze = new Maze(grid);

        DijkstraRender renderer = new DijkstraRender();

        List<Coordinate> solutionPath = List.of(
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(3, 1),
            new Coordinate(3, 2),
            new Coordinate(3, 3),
            new Coordinate(3, 4),
            new Coordinate(3, 5),
            new Coordinate(4, 5),
            new Coordinate(5, 5)

            );

        String renderPath = renderer.render(maze, solutionPath);

        String ans =
            "⬛⬛⬛⬛⬛⬛⬛\n"
                + "⬛\uD83D\uDD34\uD83E\uDDA0⬜⬛⬛⬛\n" //точка старта, вирус
                + "⬛\uD83D\uDFE9⬛⬜⬛⬛⬛\n" //путь(через медаль)
                + "⬛\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9⬛\n" // путь -4 + путь(медаль)
                + "⬛⬛⬛⬜⬛\uD83D\uDFE9⬛\n" //путь
                + "⬛⬛⬛\uD83E\uDDA0⬜\uD83D\uDEA9⬛\n" //вирус, финиш
                + "⬛⬛⬛⬛⬛⬛⬛\n";

        assertThat(renderPath).isEqualTo(ans);
    }

    @Test
    public void dijkstraSolverTest() {
        Maze maze = new Maze(grid);
        Coordinate start = new Coordinate(1, 1);
        Coordinate finish = new Coordinate(5, 5);
        Solver solver = new DijkstraSolverMaze();
        List<Coordinate> path = solver.solve(maze, start, finish);
        assertThat(path.get(0)).isEqualTo(start);
        assertThat(path.get(path.size() - 1)).isEqualTo(finish);
    }
}
