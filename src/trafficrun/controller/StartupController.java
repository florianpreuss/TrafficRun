package trafficrun.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import trafficrun.Main;

public class StartupController {
    @FXML
    public ComboBox playerSelect;

    @FXML
    private void startGame(ActionEvent event) {
        Main.primaryStage.setScene(Main.gameScene);
        Main.primaryStage.centerOnScreen();
    }
}
