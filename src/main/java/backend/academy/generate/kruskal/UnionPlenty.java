package backend.academy.generate.kruskal;

import backend.academy.base.Coordinate;
import java.util.HashMap;
import java.util.Map;
/**
 * Класс для реализации структуры данных "Система непересекающихся множеств"
 * используемой в алгоритме Краскала для объединения клеток
 */
public class UnionPlenty {
    private Map<Coordinate, Coordinate> mapaRoots;

    public UnionPlenty() {
        mapaRoots = new HashMap<>();
    }
    /**
     * Инициализирует систему непересекающихся множеств для заданных размеров
     *
     * @param width  Ширина
     * @param height Высота
     */
    public void setInitialization(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate coordinate = new Coordinate(y, x);
                mapaRoots.put(coordinate, coordinate); // Каждый является родителем
                //  ключ  -> дочерний, значение-> родитель
            }

        }
    }

    /**
     * Объединяет два множества
     *
     * @param point1 Первая координата.
     * @param point2 Вторая координата.
     */
    public void setUnion(Coordinate point1, Coordinate point2) {
        //находим корни
        Coordinate root1 = getFindRoot(point1);
        Coordinate root2 = getFindRoot(point2);
        if (!root1.equals(root2)) {
            // Объединяем, корни множества
            // root1 -> становится дочерним к root2
            mapaRoots.put(root1, root2);
        }
    }


    /**
     * Находит корень множества для координаты
     *
     * @param point Координата для поиска корня.
     * @return Корень множества.
     */
    public Coordinate getFindRoot(Coordinate point) {
        // Проверка является ли родителем, если нет, то ищем
        if (!mapaRoots.get(point).equals(point)) {
            // Каждый корень будет указывать на узел - родителя
            mapaRoots.put(point, getFindRoot(mapaRoots.get(point)));
        }
        return mapaRoots.get(point);
    }
}
