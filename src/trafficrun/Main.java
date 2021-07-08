package trafficrun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import trafficrun.gameobjects.GameObjectPlayer;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static List<GameObjectPlayer> players = new ArrayList<>();
    public static List<GameObjectPlayer> cars = new ArrayList<>();
    public static List<String> imageFiles = List.of("car_1.png");

    public static Stage primaryStage;
    public static Scene startupScene;
    public static Scene gameScene;
    public static Pane gameRoot;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent startupRoot = FXMLLoader.load(getClass().getResource("fxml/startup.fxml"));
        gameRoot = FXMLLoader.load(getClass().getResource("fxml/game.fxml"));
        primaryStage.setTitle("Traffic Run");

        Main.startupScene = new Scene(startupRoot, 498, 278);
        Main.gameScene = new Scene(gameRoot, 1000, 900);

        primaryStage.setScene(startupScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Main.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
