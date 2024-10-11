package backend.academy;

public class Cell {

    public enum Type {
        WALL,
        PASSAGE
    }

    public int row;
    public int col;
    public Type type;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }
}
