package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends StaticEntity {
    public Portal(Coordinates tile) {
        super(tile);
        this.img = Sprite.portal.getFxImage();
    }
}
