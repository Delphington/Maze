package backend.academy.generate.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import backend.academy.base.Cell;
import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import backend.academy.generate.Generator;

// Вспомогательный класс, для объекдинения двух координат в стену
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


