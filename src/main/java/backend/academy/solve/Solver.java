package backend.academy.solve;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

/**
 * Интерфейс Solver определяет метод решения лабиринта.
 */
public interface Solver {
    //Масив для направлений движений

    /** Метод для решения лабиринта.
     *
     * @param maze   Лабиринт для решения
     * @param start  Начальная координата
     * @param end    Конечная координата
     * @return Список координат, представляющих путь от старта до конца.
     */
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
