package trafficrun.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import trafficrun.Main;
import trafficrun.gameobjects.GameObjectPlayer;

import java.util.ArrayList;
import java.util.List;

public class StartupController {
    private List<Point2D> playerSpawns2 = List.of(new Point2D(100, 430), new Point2D(270, 430));

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

        player.moveObjectTo(playerSpawns2.get(0).getX(), playerSpawns2.get(0).getY());
        player2.moveObjectTo(playerSpawns2.get(1).getX(), playerSpawns2.get(1).getY());
    }
}
