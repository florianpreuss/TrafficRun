package trafficrun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import trafficrun.gameobjects.GameObjectCar;
import trafficrun.gameobjects.GameObjectPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    public static List<GameObjectPlayer> players = new ArrayList<>();
    public static List<GameObjectCar> cars = new ArrayList<>();
    public static List<String> imageFiles = List.of("car_black.png", "car_police.png");
    public static List<Color> colors = List.of(Color.GREEN, Color.YELLOW, Color.AQUA, Color.BLUE, Color.PINK, Color.BROWN, Color.GRAY);

    public static Stage primaryStage;
    public static Scene startupScene;
    public static Scene gameScene;
    public static Pane gameRoot;

    public static Random random = new Random();

    static Media media = new Media(ClassLoader.getSystemClassLoader().getResource("music/synthwave_game.mp3").toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(media);

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

        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.millis(11500));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });

        Main.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
