package backend.academy.solve;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;


/**
 * Класс DFSSolverMaze реализует алгоритм поиска в глубину
 * Этот класс ищет путь от заданной стартовой координаты до конечной в лабиринте, представленном
 * объектом Maze
 */
public final class DFSSolverMaze implements Solver {


    /**
     * Получает соседние клетки, которые являются проходами.
     *
     * @param maze  Лабиринт, в котором происходит поиск
     * @param point Текущая координата, для которой нужно найти соседей
     * @return Список соседних координат, которые являются проходами
     */
    private List<Coordinate> getNeighbors(Maze maze, Coordinate point) {
        List<Coordinate> neighbors = new ArrayList<>();

        for (int[] cell : MOVE) {
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

    /**
     * Решает задачу о лабиринте с использованием алгоритма поиска в глубину (DFS)
     *
     * @param maze   Лабиринт, в котором происходит поиск
     * @param start  Начальная координата
     * @param finish Конечная координата
     * @return Список координат, представляющих путь от стартовой до конечной точки,
     * или пустой список, если путь не найден.
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate finish) {
        List<Coordinate> path = new ArrayList<>();

        Deque<Coordinate> stack = new ArrayDeque<>();

        boolean[][] visitedCell = new boolean[maze.height()][maze.width()];

        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            //Если мы пришли к выходу
            if (current.equals(finish)) {
                path.add(current);
                return path;
            }
            path.add(current);
            visitedCell[current.row()][current.col()] = true;

            List<Coordinate> neighbors = getNeighbors(maze, current);

            for (Coordinate neighbor : neighbors) {
                if (!visitedCell[neighbor.row()][neighbor.col()]) {
                    stack.push(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }
}
