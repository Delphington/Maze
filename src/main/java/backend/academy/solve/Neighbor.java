package backend.academy.solve;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.ArrayList;
import java.util.List;

public final class Neighbor {
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private Neighbor() {
    }

    /**
     * Получает соседние клетки, которые являются проходами.
     *
     * @param maze  Лабиринт, в котором происходит поиск
     * @param point Текущая координата, для которой нужно найти соседей
     * @return Список соседних координат, которые являются проходами
     */

    public static List<Coordinate> getNeighbors(Maze maze, Coordinate point) {
        List<Coordinate> neighbors = new ArrayList<>();

        for (int[] cell : move) {
            int newRow = point.row() + cell[0];
            int newCol = point.col() + cell[1];
            if ((newRow >= 0) && (newRow < maze.height()) && (newCol >= 0) && (newCol < maze.width())) {
                if (maze.getCell(newRow, newCol).type == Cell.Type.PASSAGE) {
                    neighbors.add(new Coordinate(newRow, newCol));
                }
            }
        }
        return neighbors;
    }
}
