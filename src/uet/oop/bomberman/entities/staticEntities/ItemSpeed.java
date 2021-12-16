package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class ItemSpeed extends Item{

    public ItemSpeed(Coordinates tile) {
        super(tile);
        this.img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void getItem() {
        if (BombermanGame.getBomber().getDirect().getX() % 2 == 0
                && BombermanGame.getBomber().getDirect().getY() % 2 == 0) {
            BombermanGame.getBomber().setSpeed(BombermanGame.getBomber().getSpeed() + 1);
            BombermanGame.obtainItem(this);
        }
    }
}
