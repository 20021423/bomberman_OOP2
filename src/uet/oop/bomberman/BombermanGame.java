package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.staticEntities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import javax.sound.sampled.Clip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static uet.oop.bomberman.GameSound.loopMusic;

public class BombermanGame extends Application {
    public static Stage stage = new Stage();

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private static Bomber bomber;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Flame> flames = new ArrayList<>();
    private static List<Entity> bombs = new ArrayList<>();
    private static List<Entity> walls = new ArrayList<>();
    private static List<Entity> portals = new ArrayList<>();
    private static List<Entity> bricks = new ArrayList<>();
    private static List<Entity> items = new ArrayList<>();
    private static List<Entity> enemies = new ArrayList<>();
    private static List<Grass> grasses = new ArrayList<>();
    private static List<Enemy> dead = new ArrayList<>();
    public static Keyboard input = new Keyboard();
    public static Clip THREAD_SOUNDTRACK = loopMusic(GameSound.inGameSound);

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public static void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }


    public static void removeDead(Enemy enemy) {
        BombermanGame.dead.remove(enemy);
    }

    public static void setEnemy(Enemy enemy) {
        BombermanGame.enemies.add(enemy);
    }

    public static Entity getEntityAt(int x, int y) {
        Entity entity;
        entity = get(walls, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(bricks, x, y);
        if (entity != null) {
            return entity;
        }

        entity = get(bombs, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(portals, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(items, x, y);
        if (entity != null) {
            return entity;
        }
        entity = get(enemies, x, y);
        if (entity != null) {
            return entity;
        }
        if (bomber != null
                && bomber.getTile().getX() == x
                && bomber.getTile().getY() == y) {
            entity = bomber;
        }
        return entity;
    }

    public static Entity get(List<Entity> entities, int x, int y) {
        Iterator<Entity> itr = entities.iterator();
        Entity cur;
        Entity entity = null;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur.getTile().getY() == y && cur.getTile().getX() == x) {
                entity = cur;
            }
        }
        return entity;
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static void setBomber(Bomber bomber) {
        BombermanGame.bomber = bomber;
    }

    public static void removeBomber() {
        bomber = null;
    }

    public static void setBomb(Bomb bomb) {
        BombermanGame.bombs.add(bomb);
    }

    public static void setDead(Enemy enemy) {
        BombermanGame.dead.add(enemy);
    }

    public static void setPortal(Portal portal) {
        portals.add(portal);
    }

    @Override
    public void start(Stage stage1) throws IOException {
        stage = stage1;
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> input.keyPressed(event));

        scene.setOnKeyReleased(event -> input.keyRelease(event));

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap(1);
        BombermanGame.THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void createMap(int level) throws IOException {
        resetMap();
        Map.getInstance().loadMap(level);
    }

    private static void resetMap() {
        entities.clear();
        flames.clear();
        bombs.clear();
        walls.clear();
        portals.clear();
        bricks.clear();
        items.clear();
        enemies.clear();
        grasses.clear();
        dead.clear();
    }

    public void update() {
        for (int i = 0; i < flames.size(); i++) {
            flames.get(i).update();
        }

        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = (Brick) bricks.get(i);
            brick.update();
        }

        for (int i = 0; i < enemies.size(); i++) {
            AnimatedEntity enemy = (AnimatedEntity) enemies.get(i);
            enemy.update();
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = (Bomb) bombs.get(i);
            bomb.update();
        }
        for (int i = 0; i < dead.size(); i++) {
            AnimatedEntity enemy = (AnimatedEntity) dead.get(i);
            enemy.update();
        }
        if (bomber != null) {
            bomber.update();
        }
    }

    public static void removeFlame() {
        flames.remove(0);
    }

    public static void removeBrick(Brick brick) {
        bricks.remove(brick);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grasses.forEach(grass -> grass.render(gc));
        walls.forEach(wall -> wall.render(gc));
        portals.forEach(portal -> portal.render(gc));
        items.forEach(item -> item.render(gc));
        for (Flame flame : flames) {
            flame.getFlameSegments().forEach(flameSegment -> flameSegment.render(gc));
        }
        bricks.forEach(brick -> brick.render(gc));
        bombs.forEach(g -> g.render(gc));
        dead.forEach(dead -> dead.render(gc));
        if (bomber != null) {
            bomber.render(gc);
        }
        enemies.forEach(enemy -> enemy.render(gc));
    }


    public static void setGrass(Grass grass) {
        BombermanGame.grasses.add(grass);
    }

    public static void setWall(Wall wall) {
        BombermanGame.walls.add(wall);
    }

    public static void setItem(Item item) {
        BombermanGame.items.add(item);
    }

    public static void obtainItem(Item item) {
        BombermanGame.items.remove(item);
    }

    public static void setFlame(Flame flame) {
        flames.add(flame);
    }

    public static void removeBomb() {
        bombs.remove(0);
        if (bomber != null) {
            bomber.addBomb();
        }
    }

    public static void setBrick(Brick brick) {
        BombermanGame.bricks.add(brick);
    }
}
