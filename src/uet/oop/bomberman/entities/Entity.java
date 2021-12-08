package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected Coordinates pixel;

    protected Coordinates tile;

    protected Image img;

    public DIRECTION _direction = DIRECTION.NONE;

    public enum DIRECTION {
        NONE, UP, RIGHT, DOWN, LEFT, CENTER
    }

    public Entity(Coordinates tile) {
        this.tile = tile;
        pixel = tile.convertTileToPixel();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixel.getX(), pixel.getY());
    }

    public Coordinates getTile() {
        return tile;
    }

    public void setTile(Coordinates tile) {
        this.tile = tile;
    }
}
