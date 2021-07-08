package trafficrun.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameObjectPlayer extends GameObject {
    public GameObjectPlayer() {
        super(new Circle(20, 20, 20, Color.RED));
    }
}
