package backend.academy.generate.kruskal;

import backend.academy.base.Coordinate;
import java.util.HashMap;
import java.util.Map;

public class UnionPlenty {
    private Map<Coordinate, Coordinate> mapaRoots;

    public UnionPlenty() {
        mapaRoots = new HashMap<>();
    }

    public void setInitialization(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate coordinate = new Coordinate(y, x);
                mapaRoots.put(coordinate, coordinate); // Каждый является родителем
                //  ключ  -> дочерний, значение-> родитель
            }

        }
    }

    // Объединения двух координат
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

    // Нахоит корень множества
    public Coordinate getFindRoot(Coordinate node) {
        // Проверка является ли родителем, если нет, то ищем
        if (!mapaRoots.get(node).equals(node)) {
            // Каждый корень будет указывать на узел - родителя
            mapaRoots.put(node, getFindRoot(mapaRoots.get(node)));
        }
        return mapaRoots.get(node);
    }
}
