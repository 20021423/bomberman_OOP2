package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.staticEntities.Flame;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Flame> flames = new ArrayList<>();
    private static List<Entity> bombs = new ArrayList<>();
    private static List<Entity> walls = new ArrayList<>();
    private static List<Entity> portals = new ArrayList<>();
    private static List<Entity> bricks = new ArrayList<>();
    private static List<Entity> items = new ArrayList<>();
    private static List<Entity> enemies = new ArrayList<>();
    private static List<Grass> grasses = new ArrayList<>();
//    private static List<Enemy> dead = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

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

        createMap();

//       Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//        entities.add(bomberman);
    }

    public void createMap() throws IOException {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }

        Map.getInstance().loadMap(1);
    }

    public void update() {
        //entities.forEach(Entity::update);
    }

    public static void setStillObjects(Entity entity) {
        stillObjects.add(entity);
    }

    public void render() {
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
//        entities.forEach(g -> g.render(gc));
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        grasses.forEach(grass -> grass.render(gc));
        walls.forEach(wall -> wall.render(gc));
        portals.forEach(portal -> portal.render(gc));
        items.forEach(item -> item.render(gc));
        for (Flame flame : flames) {
            flame.get_flameSegments().forEach(flameSegment -> flameSegment.render(gc));
        }
        bricks.forEach(brick -> brick.render(gc));
        enemies.forEach(enemy -> enemy.render(gc));
        bombs.forEach(g -> g.render(gc));
//        dead.forEach(dead->dead.render(gc));
//        if (bomber != null) {
//            bomber.render(gc);
//        }
    }
}
