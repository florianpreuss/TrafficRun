package trafficrun.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import trafficrun.Main;

import javax.imageio.ImageIO;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class GameObjectCar extends GameObject {
    public GameObjectCar() {
        super(new ImageView(Main.imageFiles.get(new Random().nextInt(Main.imageFiles.size()- 1))));
    }
}
