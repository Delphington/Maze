package backend.academy;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Cell[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(int x, int y){
        return grid[x][y];
    }


    public Maze(Cell [][]grid){
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
    }

}
