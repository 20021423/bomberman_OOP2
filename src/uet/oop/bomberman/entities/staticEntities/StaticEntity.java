package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Entity;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Coordinates tile) {
        super(tile);
    }
}
