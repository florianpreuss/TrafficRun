package trafficrun.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import trafficrun.Main;
import trafficrun.controller.StartupController;

import javax.imageio.ImageIO;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class GameObjectCar extends GameObject {
    public StartupController.CarLane lane;
    public GameObjectCar(StartupController.CarLane lane) {
        super(new ImageView(Main.imageFiles.get(new Random().nextInt(Main.imageFiles.size()))));
        this.lane = lane;
    }

    @Override
    public void moveObjectTo(double x, double y) {
        final double cx = super.getView().getBoundsInLocal().getWidth()  / 2;
        final double cy = super.getView().getBoundsInLocal().getHeight() / 2;

        if (x + cx <= Main.gameScene.getWidth() &&
                y + cy <= Main.gameScene.getHeight()) {
            super.getView().setTranslateX(x - cx);
            super.getView().setTranslateY(y - cy);
        }
    }
}
