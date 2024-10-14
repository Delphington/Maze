package backend.academy;

import backend.academy.base.Coordinate;
import lombok.Getter;
import java.util.*;

public class DijkstraMaze {
    private int[][] maze;
    private Coordinate start;
    private Coordinate end;
    @Getter
    private ArrayList<Coordinate> arr = new ArrayList<>();


    public DijkstraMaze(int[][] maze, Coordinate start, Coordinate end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    // Вложенный класс для представления узла в графе (координаты и стоимость)
    class Node {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x; // Координата x
            this.y = y; // Координата y
            this.cost = cost; // Стоимость перемещения в узел
        }
    }

    public void start() {


        // Запуск алгоритма Дейкстры для нахождения пути
        List<int[]> path = dijkstra(maze, start.row(), start.col(), end.row(), end.col());

        // Если путь найден, выводим его и общую стоимость
        if (path != null) {
            System.out.println("Minimum cost path:");
            for (int[] point : path) {
                System.out.println(Arrays.toString(point)); // Выводим координаты каждого узла на пути
            }
            // Вычисляем и выводим общую стоимость пути
            int totalCost = calculateTotalCost(maze, path);
            System.out.println("Total cost: " + totalCost);
        } else {
            System.out.println("No path found."); // Если путь не найден
        }
    }

    // Метод для выполнения алгоритма Дейкстры
    public List<int[]> dijkstra(int[][] maze, int startX, int startY, int endX, int endY) {
        int rows = maze.length; // Количество строк в лабиринте
        int cols = maze[0].length; // Количество столбцов в лабиринте
        int[][] costs = new int[rows][cols]; // Массив для хранения стоимости пути до каждой клетки
        boolean[][] visited = new boolean[rows][cols]; // Массив для отслеживания посещенных клеток
        PriorityQueue<Node> pq =  new PriorityQueue<>(Comparator.comparingInt(node -> node.cost)); // Очередь с приоритетом для узлов

        // Инициализация массива costs значениями "бесконечности"
        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        costs[startX][startY] = maze[startX][startY]; // Стоимость начала равна стоимости начальной клетки
        pq.offer(new Node(startX, startY, costs[startX][startY])); // Добавляем начальный узел в очередь

        // Массив для восстановления пути
        Map<String, String> previous = new HashMap<>();

        // Направления движения (вверх, вниз, влево, вправо)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            Node current = pq.poll(); // Извлекаем узел с наименьшей стоимостью

            // Если достигли конечной точки, восстанавливаем путь
            if (current.x == endX && current.y == endY) {
                return constructPath(previous, startX, startY, endX, endY);
            }

            if (visited[current.x][current.y]) {
                continue; // Если узел уже посещен, пропускаем его
            }
            visited[current.x][current.y] = true; // Отмечаем узел как посещенный

            for (int[] dir : directions) {
                int newX = current.x + dir[0]; // Новая координата x
                int newY = current.y + dir[1]; // Новая координата y

                // Проверяем границы и проходимость новой клетки
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && maze[newX][newY] != 0) {
                    int newCost = current.cost + maze[newX][newY]; // Рассчитываем новую стоимость

                    // Если новая стоимость меньше ранее записанной, обновляем её и добавляем узел в очередь
                    if (newCost < costs[newX][newY]) {
                        costs[newX][newY] = newCost;
                        pq.offer(new Node(newX, newY, newCost));
                        previous.put(newX + "," + newY,
                            current.x + "," + current.y); // Сохраняем предшествующий узел для восстановления пути
                    }
                }
            }
        }

        return null; // Если путь не найден
    }

    // Метод для восстановления пути от конечной точки к начальной
    private List<int[]> constructPath(Map<String, String> previous, int startX, int startY, int endX, int endY) {
        List<int[]> path = new ArrayList<>();


        String key = endX + "," + endY; // Начинаем с конечной точки
        while (key != null) {
            String[] parts = key.split(","); // Разделяем координаты
            path.add(new int[] {Integer.parseInt(parts[0]), Integer.parseInt(parts[1])}); // Добавляем точку в путь
            arr.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            key = previous.get(key); // Переходим к предшествующему узлу
        }

        Collections.reverse(path); // Переворачиваем путь для правильного порядка от начала к концу
        return path;
    }

    // Метод для вычисления общей стоимости пути
    private int calculateTotalCost(int[][] maze, List<int[]> path) {
        int totalCost = 0;
        for (int[] point : path) {
            totalCost += maze[point[0]][point[1]]; // Суммируем стоимости всех клеток на пути
        }
        return totalCost; // Возвращаем общую стоимость
    }
}
