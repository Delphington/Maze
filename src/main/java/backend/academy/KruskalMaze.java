package backend.academy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

// Вспомогательный класс, для объекдинения двух координат в стену
@Getter
@AllArgsConstructor
class Wall {
    private Coordinate cell_1;
    private Coordinate cell_2;
}

class UnionPlenty {
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
    public void setUnion(Coordinate node_1, Coordinate node_2) {
        //находим корни
        Coordinate root_1 = getFindRoot(node_1);
        Coordinate root_2 = getFindRoot(node_2);
        if (!root_1.equals(root_2)) {
            // Объединяем, корни множества
            // root1 -> становится дочерним к root2
            mapaRoots.put(root_1, root_2);
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



public class KruskalMaze implements Generator {

    @Override
    public Maze generate(int height, int width) {

        // Для отслеживания объединенных клеток
        UnionPlenty unionPlenty = new UnionPlenty();
        unionPlenty.setInitialization(width, height);

        Cell[][] cells = new Cell[height][width];
        List<Wall> walls = new ArrayList<>();

        // Заполняем потолок и низ
        for (int x = 0; x < width; x++) {
            cells[0][x] = new Cell(0, x, Cell.Type.WALL);
            cells[height - 1][x] = new Cell(height - 1, x, Cell.Type.WALL);
        }

        // Заполняем левую и правую стенку
        for (int y = 0; y < height; y++) {
            cells[y][0] = new Cell(y, 0, Cell.Type.WALL);
            cells[y][width - 1] = new Cell(y, width - 1, Cell.Type.WALL);
        }



        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                // Все заполняем стенами
                cells[y][x] = new Cell(y, x, Cell.Type.WALL);

                // Обязательно где будет проход
                if (y == height - 2 && x == width - 2) {
                    cells[y][x].type = Cell.Type.PASSAGE;
                }

                //Заполняем стенами, и стеной рядом
                if (y < height - 2) {
                    walls.add(new Wall(new Coordinate(y, x), new Coordinate(y + 1, x)));
                }

                if (x < width - 2) {
                    walls.add(new Wall(new Coordinate(y, x), new Coordinate(y, x + 1)));
                }

            }
        }

        Collections.shuffle(walls);

        for (Wall wall : walls) {
            Coordinate cell_1 = wall.cell_1();
            Coordinate cell_2 = wall.cell_2();

            Coordinate root_1 = unionPlenty.getFindRoot(cell_1);
            Coordinate root_2 = unionPlenty.getFindRoot(cell_2);


            if (!root_1.equals(root_2)) {

                //Объединяем два разных множества
                unionPlenty.setUnion(cell_1, cell_2);

                if (cell_1.row() == cell_2.row()) {
                    cells[cell_1.row()][Math.max(cell_1.col(), cell_2.col())].type = Cell.Type.PASSAGE;
                } else {
                    cells[Math.max(cell_1.row(), cell_2.row())][cell_1.col()].type = Cell.Type.PASSAGE;
                }
            }
        }

        return new Maze(cells);
    }
}


