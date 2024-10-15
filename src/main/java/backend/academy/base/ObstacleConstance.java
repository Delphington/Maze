package backend.academy.base;

public interface ObstacleConstance {
    int PASSAGE_COST = 40;
    int MEDAL_COST = 5;
    int VIRUS_COST = 80;
    int WALL_COST = 0;
    int[] TYPE_COST_CELL = {PASSAGE_COST, PASSAGE_COST, MEDAL_COST, PASSAGE_COST, VIRUS_COST, PASSAGE_COST};
}
