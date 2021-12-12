package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends StaticEntity {

    public Grass(Coordinates tile) {
        super(tile);
        this.img = Sprite.grass.getFxImage();
    }

}
