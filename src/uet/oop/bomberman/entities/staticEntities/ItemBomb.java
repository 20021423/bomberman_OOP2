package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public class ItemBomb extends Item{
    public ItemBomb(Coordinates tile) {
        super(tile);
        this.img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public void getItem() {
        BombermanGame.getBomber().addBomb();
        BombermanGame.obtainItem(this);
    }
}
