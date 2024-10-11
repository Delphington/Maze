package backend.academy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class DFSMaze implements Generator {

    private void createPassage(Cell current, Cell next, Cell[][] grid) {
        int newRow = ((current.row + next.row) / 2); //Делится всегда хорошо, так как
        //мы через клетку
        int newCol = (current.col + next.col) / 2;
        grid[newRow][newCol].type = Cell.Type.PASSAGE;
        next.type = Cell.Type.PASSAGE;
    }

    private List<Cell> getUnvisitedNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighborCells = new ArrayList<>();
        int[] rowOffsets = {-2, 2, 0, 0};
        int[] colOffsets = {0, 0, -2, 2};

        for (int i = 0; i < 4; i++) {
            int newRow = cell.row + rowOffsets[i];
            int newCol = cell.col + colOffsets[i];

            if ((newRow >= 0) && (newRow < grid.length) && (newCol >= 0) && (newCol < grid[0].length)){
                if(grid[newRow][newCol].type == Cell.Type.WALL){
                    neighborCells.add(grid[newRow][newCol]);
                }
            }
        }
        Collections.shuffle(neighborCells);
        return neighborCells;
    }


    //Сразу приходят валидные данные
    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        //заполняем стенами
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(i, j, Cell.Type.WALL);
                grid[i][j] = cell;
            }
        }

        Cell first = grid[1][1]; //Начальная клетка без границы
        first.type = Cell.Type.PASSAGE;
        Deque<Cell> stack = new ArrayDeque<>();
        stack.add(first);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighborCells = getUnvisitedNeighbors(current, grid);
            if (!neighborCells.isEmpty()) {
                Cell next = neighborCells.get((int) (Math.random() * neighborCells.size()));
                createPassage(current, next, grid);
                stack.push(next);
            } else {
                stack.pop();
            }
        }

        return new Maze(grid);
    }

}
