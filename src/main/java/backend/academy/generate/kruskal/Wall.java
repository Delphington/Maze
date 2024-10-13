package backend.academy.generate.kruskal;

import backend.academy.base.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class Wall {
    private Coordinate cell1;
    private Coordinate cell2;
}

