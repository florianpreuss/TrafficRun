package trafficrun.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import trafficrun.Main;
import trafficrun.gameobjects.GameObjectCar;
import trafficrun.gameobjects.GameObjectPlayer;

import java.util.List;

public class StartupController {
    private List<Point2D> playerSpawns = List.of(new Point2D(-290, 430), new Point2D(270, 430), new Point2D(-80, 430), new Point2D(70, 430));

    @FXML
    public ComboBox playerSelect;


    @FXML
    private void startGame(ActionEvent event) {
        int playerAmount = Integer.parseInt(playerSelect.getValue().toString().split(" ")[0]);

        Main.primaryStage.setScene(Main.gameScene);
        Main.primaryStage.centerOnScreen();

        for (int i = 0; i < playerAmount; i++) {
            GameObjectPlayer player = new GameObjectPlayer();
            Main.gameRoot.getChildren().add(player.getView());
            Main.players.add(player);
            player.moveObjectTo((i == 2 && playerAmount== 3) ? 0.0 : playerSpawns.get(i).getX(), playerSpawns.get(i).getY());
        }

        GameObjectCar car = new GameObjectCar();
        Main.gameRoot.getChildren().add(car.getView());
        car.moveObjectTo(100, 100);
    }
}
