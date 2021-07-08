package trafficrun.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import trafficrun.Main;
import trafficrun.gameobjects.GameObjectPlayer;

import java.util.List;

public class StartupController {
    private List<Point2D> playerSpawns = List.of(new Point2D(-290, 430), new Point2D(270, 430));

    @FXML
    public ComboBox playerSelect;


    @FXML
    private void startGame(ActionEvent event) {
        Main.primaryStage.setScene(Main.gameScene);
        Main.primaryStage.centerOnScreen();

        GameObjectPlayer player = new GameObjectPlayer();
        GameObjectPlayer player2 = new GameObjectPlayer();

        Main.gameRoot.getChildren().add(player.getView());
        Main.gameRoot.getChildren().add(player2.getView());

        player.moveObjectTo(playerSpawns.get(0).getX(), playerSpawns.get(0).getY());
        player2.moveObjectTo(playerSpawns.get(1).getX(), playerSpawns.get(1).getY());
        player2.moveObjectTo(playerSpawns.get(1).getX(), playerSpawns.get(1).getY());

        System.out.println(Main.gameScene.getHeight());
    }
}
