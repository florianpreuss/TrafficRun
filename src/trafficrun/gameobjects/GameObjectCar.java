package trafficrun.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.net.URL;
import java.util.List;

public class GameObjectCar extends GameObject {
    private List<String> ImageFile = List.of("car_1.png");
    public GameObjectCar() {
        URL url = getClass().getResource();
        Image image = ImageIO.read(url);
        super();
    }
}
