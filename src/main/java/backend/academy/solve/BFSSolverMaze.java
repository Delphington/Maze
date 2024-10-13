package backend.academy.solve;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс предназанченный для нахождения пути в лабиринте по методу поиска в ширину
 * */
public class BFSSolverMaze implements Solver {

    /**
     * Получает соседние клетки для заданной точки.
     *
     * @param maze  Лабиринт, в котором ищутся соседние клетки
     * @param point Точка, для которой ищутся соседи
     * @return Список соседних клеток, которые являются проходами
     */
    private List<Coordinate> getNeighbors(Maze maze, Coordinate point) {
        List<Coordinate> neighbors = new ArrayList<>();
        for (int[] cell : MOVE) {
            int newRow = point.row() + cell[0];
            int newCol = point.col() + cell[1];

            // Выбираем свободные клетки
            if ((newRow >= 0) && (newRow < maze.height()) && (newCol >= 0) && (newCol < maze.width())) {
                if (maze.getCell(newRow, newCol).type == Cell.Type.PASSAGE) {
                    neighbors.add(new Coordinate(newRow, newCol));
                }
            }
        }
        return neighbors;
    }

    /**
     * Строит путь на основе двумерного массива
     *
     * @param previous Двумерный массив, хранящий предыдущие клетки для каждой клетки лабиринта.
     * @param end     Конечная точка пути.
     * @return Список координат, представляющих путь от старта до конца.
     */
    private List<Coordinate> buildPath(Coordinate[][] previous, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate at = end;

        while (at != null) {
            path.add(0, at); // Добавляем элемент в начало списка
            at = previous[at.row()][at.col()];
        }

        return path;
    }

    /**
     * Решает лабиринт с использованием метода поиска в ширину.
     *
     * @param maze   Лабиринт, который необходимо решить.
     * @param start  Начальная координата.
     * @param finish Конечная координата.
     * @return Список координат, представляющих путь от стартовой до конечной точки.
     *         Если путь не найден, возвращает пустой список.
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate finish) {

        Coordinate[][] previous = new Coordinate[maze.height()][maze.width()];
        boolean[][] visitedCell = new boolean[maze.height()][maze.width()];
        Queue<Coordinate> queue = new LinkedList<>();

        List<Coordinate> neighbors; // соседи не стены

        queue.offer(start);
        visitedCell[start.row()][start.col()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(finish)) {
                return buildPath(previous, finish);
            }

            neighbors = getNeighbors(maze, current);
            for (Coordinate neighbor : neighbors) {
                //если еще не были там
                if (!visitedCell[neighbor.row()][neighbor.col()]) {
                    queue.offer(neighbor);
                    visitedCell[neighbor.row()][neighbor.col()] = true;
                    previous[neighbor.row()][neighbor.col()] = current; // Сохраняем предыдущую клетку
                }
            }
        }

        return Collections.emptyList();
    }
}
