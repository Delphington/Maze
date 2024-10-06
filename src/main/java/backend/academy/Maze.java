package backend.academy;

public final class Maze {
    private final int height;
    private final int width;

    public Cell[][] getGrid() {
        return grid;
    }

    private final Cell[][] grid;


//     checkValidDate(int height, int width, Cell[][] grid){
//
//
//    }


//    public Maze(int height, int width, Cell[][] grid) {
//        this.height = height;
//        this.width = width;
//        this.grid = grid;
//    }

    public Maze(Cell [][]grid){
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
    }

}
