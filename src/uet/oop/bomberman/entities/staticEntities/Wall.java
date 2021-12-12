package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends StaticEntity {

    public Wall(Coordinates tile) {
        super(tile);
        this.img = Sprite.wall.getFxImage();
    }

}
