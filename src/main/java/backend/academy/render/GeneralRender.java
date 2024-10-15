package backend.academy.render;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeneralRender {

    public static StringBuilder renderCell(Cell cell, StringBuilder builder) {
        switch (cell.type) {
            case WALL -> builder.append('⬛');
            case MEDAL -> builder.append('\uD83E').append('\uDD47');
            case VIRUS -> builder.append('\uD83E').append('\uDDA0');
            case PASSAGE -> builder.append('⬜');
            default -> builder.append('⬛');

        }
        return builder;
    }



    public static StringBuilder renderPathCell(List<Coordinate> path, Coordinate temp, StringBuilder builder) {
        if (path.get(0).equals(temp)) {
            builder.append('\uD83D').append('\uDD34'); // точка старта
        } else if (path.get(path.size() - 1).equals(temp)) {
            builder.append('\uD83D').append('\uDEA9'); // флаг точка финиша
        } else {
            builder.append('\uD83D').append('\uDFE9'); // зеленый
        }
        return builder;
    }


    public static  String generalRender(Maze maze, List<Coordinate> path) {
        StringBuilder builder = new StringBuilder();
        Cell[][] grid = maze.grid();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                Coordinate temp = new Coordinate(x, y);
                if (path != null && path.contains(temp)) {
                    GeneralRender.renderPathCell(path, temp, builder);
                } else {
                    GeneralRender.renderCell(grid[x][y], builder);
                }
            }
            builder.append('\n');
        }

        return builder.toString();
    }


}
