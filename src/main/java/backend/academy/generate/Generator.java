package backend.academy.generate;

import backend.academy.base.Maze;

/** Интерфейс описывающий поведение классов-генераторов лабиринтов */
public interface Generator {
    Maze generate(int height, int width);
}
