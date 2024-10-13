package backend.academy.generate.kruskal;

import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.generate.Generator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Вспомогательный класс, для объекдинения двух координат в стену
public final class KruskalMaze implements Generator {

    public KruskalMaze() {

    }

    /**
     * Генерирует лабиринт заданной высоты и ширины
     *
     * @param height Высота лабиринта
     * @param width  Ширина лабиринта
     * @return Сгенерированный лабиринт
     */

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
            Coordinate cell1 = wall.cell1();
            Coordinate cell2 = wall.cell2();

            Coordinate root1 = unionPlenty.getFindRoot(cell1);
            Coordinate root2 = unionPlenty.getFindRoot(cell2);

            if (!root1.equals(root2)) {

                //Объединяем два разных множества
                unionPlenty.setUnion(cell1, cell2);

                if (cell1.row() == cell2.row()) {
                    cells[cell1.row()][Math.max(cell1.col(), cell2.col())].type = Cell.Type.PASSAGE;
                } else {
                    cells[Math.max(cell1.row(), cell2.row())][cell1.col()].type = Cell.Type.PASSAGE;
                }
            }
        }

        return new Maze(cells);
    }
}


