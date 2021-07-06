package trafficrun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    public static Scene startupScene;
    public static Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent startupRoot = FXMLLoader.load(getClass().getResource("fxml/startup.fxml"));
        StackPane gameRoot = FXMLLoader.load(getClass().getResource("fxml/game.fxml"));
        primaryStage.setTitle("Traffic Run");

        Image img = new Image("../assets/pinky.png");
        ImageView imgView = new ImageView(img);

        gameRoot.getChildren().add(imgView);

        imgView.setX(100);
        imgView.setY(100);

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