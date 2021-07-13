package trafficrun.gameobjects;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameObjectPlayer extends GameObject {

    public Point2D spawn;

    public GameObjectPlayer(Color color, Point2D spawn) {
        super(new Circle(15, 15, 15, color));
        this.spawn = spawn;
    }
}


