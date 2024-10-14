package backend.academy;

import backend.academy.base.Coordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.Getter;

public class DijkstraMaze {

    private int[][] maze;
    private Coordinate start;
    private Coordinate end;
    @Getter
    private List<Coordinate> path;

    public DijkstraMaze(int[][] maze, Coordinate start, Coordinate end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    public void start() {
        path = dijkstra(maze, start.row(), start.col(), end.row(), end.col());
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
    private List<Coordinate> constructPath(Map<String, String> previous,  int endX, int endY) {
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
