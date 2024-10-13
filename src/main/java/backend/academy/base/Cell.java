package backend.academy.base;

/**
 * Класс представляющий ячеку в лабиринте
 * */

public class Cell {

    /**
     * Тип ячейки
     * */
    public enum Type {
        WALL,
        PASSAGE
    }

    public int row;
    public int col;
    public Type type;

    /**
     * @param row - ширина
     * @param col - высота
     * @param type - тип ячейки: проход/стена
     * */
    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }
}
