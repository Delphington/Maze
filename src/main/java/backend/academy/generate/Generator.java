package backend.academy.generate;

import backend.academy.base.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
