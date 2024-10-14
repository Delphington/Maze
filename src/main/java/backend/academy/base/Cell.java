package backend.academy.base;

/**
 * Класс представляющий ячеку в лабиринте
 * */
public class Cell {

    public enum Type {
        WALL,
        PASSAGE,
        MEDAL,
        VIRUS
    }

    public int row;
    public int col;
    public Type type;

    /**
     * Конструктор для создания ячейки лабиринта.
     *
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
