package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;

public abstract class Item extends StaticEntity {

    public Item(Coordinates tile) {
        super(tile);
    }

    public abstract void getItem();
}
