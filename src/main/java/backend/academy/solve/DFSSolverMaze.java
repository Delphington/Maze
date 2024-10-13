package backend.academy.solve;

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

            List<Coordinate> neighbors = Neighbor.getNeighbors(maze, current);

            for (Coordinate neighbor : neighbors) {
                if (!visitedCell[neighbor.row()][neighbor.col()]) {
                    stack.push(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }
}
