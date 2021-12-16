package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.entities.Animated;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StaticEntity implements Animated {

    protected int frame = -1;
    protected boolean destroyed = false;


    public Brick(Coordinates tile) {
        super(tile);
        this.img = Sprite.brick.getFxImage();
    }

    public void remove() {
        destroyed = true;
    }


    @Override
    public void animate() {
        frame++;
    }

    @Override
    public void loadAnimated(Sprite sprite1, Sprite sprite2, Sprite sprite3) {
        img = Sprite.movingSprite(sprite1, sprite2, sprite3, frame, 10).getFxImage();
    }

    @Override
    public void update() {
        if (destroyed) {
            animate();
            if (frame == 30) {
                BombermanGame.removeBrick(this);
                return;
            }
            loadAnimated(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
        }
    }

}
