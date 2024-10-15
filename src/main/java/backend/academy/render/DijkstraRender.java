package backend.academy.render;

import backend.academy.base.Coordinate;
import backend.academy.base.Maze;
import java.util.List;

/**
 * Класс DijkstraRender реализует интерфейс Renderer и делает визуализацию лабиринта
 */

@SuppressWarnings("MissingSwitchDefault")
public class DijkstraRender implements Renderer {


    /**
     * Отображает лабиринт в текстовом виде без пути.
     *
     * @param maze Лабиринт, который нужно отобразить.
     * @return Строка, представляющая визуализацию лабиринта.
     */

    @Override
    public String render(Maze maze) {
        return GeneralRender.generalRender(maze, null);
    }

    /**
     * Отображает лабиринт с отмеченным путем
     *
     * @param maze Лабиринт, который нужно отобразить
     * @param path Список координат, представляющий путь в лабиринте
     * @return Строка, представляющая визуализацию лабиринта с отмеченным путем.
     */
    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return GeneralRender.generalRender(maze, path);

    }
}
