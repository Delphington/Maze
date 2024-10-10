package backend.academy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class DFSSolverMaze implements Solver {

    private List<Coordinate> getNeighbors(Maze maze, Coordinate point) {
        List<Coordinate> neighbors = new ArrayList<>();

        for (int[] cell : move) {
            int newRow = point.row() + cell[0];
            int newCol = point.col() + cell[1];
            if ((newRow >= 0) && (newRow < maze.getHeight()) && (newCol >= 0) && (newCol < maze.getWidth())) {
                if (maze.getCell(newRow, newCol).type == Cell.Type.PASSAGE) {
                    neighbors.add(new Coordinate(newRow, newCol));
                }
            }
        }
        return neighbors;
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate finish) {
        List<Coordinate> path = new ArrayList<>();

        Deque<Coordinate> stack = new ArrayDeque<>();

        boolean[][] visitedCell = new boolean[maze.getHeight()][maze.getWidth()];

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
