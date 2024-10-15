package backend.academy;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.solve.Solver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.Getter;

public class DijkstraMaze implements Solver, ObstacleConstance {

    @Getter
    private List<Coordinate> path;

    @SuppressWarnings("MissingSwitchDefault")
    private int[][] getMatrixCost(Cell[][] cell) {
        int[][] matrixCost = new int[cell.length][cell[0].length];
        for (int i = 0; i < matrixCost.length; i++) {
            for (int j = 0; j < matrixCost[0].length; j++) {
                switch (cell[i][j].type) {
                    case WALL -> matrixCost[i][j] = WALL_COST;
                    case MEDAL -> matrixCost[i][j] = MEDAL_COST;
                    case VIRUS -> matrixCost[i][j] = VIRUS_COST;
                    case PASSAGE -> matrixCost[i][j] = PASSAGE_COST;
                }
            }
        }
        return matrixCost;
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        path = dijkstra(getMatrixCost(maze.grid()), start.row(), start.col(), end.row(), end.col());
        return path;
    }

    // Метод для выполнения алгоритма Дейкстры
    private List<Coordinate> dijkstra(int[][] maze, int startX, int startY, int endX, int endY) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] costs = new int[rows][cols]; // Массив для хранения стоимости пути до каждой клетки
        boolean[][] visited = new boolean[rows][cols];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));

        // Инициализация массива costs
        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        costs[startX][startY] = maze[startX][startY]; // Стоимость начала равна стоимости начальной клетки
        pq.offer(new Node(startX, startY, costs[startX][startY]));

        // Массив для восстановления пути
        Map<String, String> previous = new HashMap<>();

        // Направления движения (вверх, вниз, влево, вправо)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            Node current = pq.poll(); // Извлекаем узел с наименьшей стоимостью

            // Если достигли конечной точки
            if (current.x == endX && current.y == endY) {
                return constructPath(previous, endX, endY);
            }

            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if ((newX >= 0) && (newX < rows) && (newY >= 0) && (newY < cols) && (maze[newX][newY] != 0)) {
                    int newCost = current.cost + maze[newX][newY];

                    // Если новая стоимость меньше ранее записанной,
                    // обновляем её и добавляем узел в очередь
                    if (newCost < costs[newX][newY]) {
                        costs[newX][newY] = newCost;
                        pq.offer(new Node(newX, newY, newCost));
                        previous.put(newX + "," + newY,
                            current.x + "," + current.y);
                    }
                }
            }
        }

        return null; // Если путь не найден
    }

    // Метод для восстановления пути от конечной точки к начальной
    private List<Coordinate> constructPath(Map<String, String> previous, int endX, int endY) {
        List<Coordinate> localPath = new ArrayList<>();
        String key = endX + "," + endY; // Начинаем с конечной точки
        while (key != null) {
            String[] parts = key.split(","); // Разделяем координаты
            localPath.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            key = previous.get(key); // Переходим к предшествующему узлу
        }
        Collections.reverse(localPath);
        return localPath;
    }

    // Вложенный класс для представления узла
    private class Node {
        private int x;
        private int y;
        private int cost;

        Node(int x, int y, int cost) {
            this.x = x; // Координата x
            this.y = y; // Координата y
            this.cost = cost; // Стоимость перемещения в узел
        }
    }
}
