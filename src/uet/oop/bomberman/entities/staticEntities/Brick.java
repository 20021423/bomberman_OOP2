package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StaticEntity {

    public Brick(Coordinates tile) {
        super(tile);
        this.img = Sprite.wall.getFxImage();
    }

}
