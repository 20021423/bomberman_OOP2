package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Coordinates;

public abstract class Entity {

    protected Coordinates pixel;
    protected Coordinates tile;

    public Entity(Coordinates tile) {
        this.tile = tile;
        pixel = tile.convertTileToPixel();
    }

    protected Image img;

    public DIRECTION direction = DIRECTION.NONE;


    public enum DIRECTION {
        NONE, UP, RIGHT, DOWN, LEFT, CENTER
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
