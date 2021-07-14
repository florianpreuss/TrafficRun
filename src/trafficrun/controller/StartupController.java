package trafficrun.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import trafficrun.Main;
import trafficrun.gameobjects.GameObjectCar;
import trafficrun.gameobjects.GameObjectPlayer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StartupController {
    private List<Point2D> playerSpawns = Stream.of(new Point2D(-290, 430), new Point2D(270, 430), new Point2D(-80, 430), new Point2D(70, 430)).collect(Collectors.toList());
    private StringBuilder scoreboardString = new StringBuilder("Scoreboard:\n");
    private Label scoreText = new Label(scoreboardString.toString());

    @FXML
    public ComboBox playerSelect;


    @FXML
    private void startGame(ActionEvent event) {
        int playerAmount = Integer.parseInt(playerSelect.getValue().toString().split(" ")[0]);

        Main.mediaPlayer.stop();
        Main.mediaPlayer.setStartTime(Duration.millis(11500));
        Main.mediaPlayer.setStopTime(Duration.millis(91500));
        Main.mediaPlayer.play();
        Main.mediaPlayer.setRate(1.0);

        Main.primaryStage.setScene(Main.gameScene);
        Main.primaryStage.centerOnScreen();

        for (int i = 0; i < playerAmount; i++) {
            GameObjectPlayer player = new GameObjectPlayer(i, (i==0) ? Color.BLUE : (i==1) ? Color.RED : (i==2) ? Color.GREEN : Color.YELLOW, playerSpawns.get(i));
            Main.gameRoot.getChildren().add(player.getView());
            Main.players.add(player);
            player.moveObjectTo((i == 2 && playerAmount== 3) ? 0.0 : playerSpawns.get(i).getX(), playerSpawns.get(i).getY());
        }

        Main.gameScene.getStylesheets().add(ClassLoader.getSystemClassLoader().getResource("style.css").toExternalForm());
        scoreText.getStyleClass().add("outline");
        scoreText.setTextAlignment(TextAlignment.LEFT);
        Main.gameRoot.getChildren().add(scoreText);
        for (int i = 0; i < Main.players.size(); i++) {
            GameObjectPlayer pl = Main.players.get(i);
            scoreboardString.append("Spieler " + (pl.index + 1) + ": {pl" + pl.index + "}" + "\n");
        }
        String tempText = scoreboardString.toString();
        for (int i = 0; i < Main.players.size(); i++) {
            GameObjectPlayer pl = Main.players.get(i);
            tempText = tempText.replace("{pl" + pl.index + "}", "" + pl.score);
        }
        scoreText.setText(tempText);
        scoreText.setTranslateX(Main.gameRoot.getWidth() / 2 - scoreText.getBoundsInLocal().getWidth() - 100);
        scoreText.setTranslateY(Main.gameRoot.getHeight() / -2 + scoreText.getBoundsInLocal().getHeight() + 60);

        Main.gameScene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:     Main.players.get(0).goNorth = true; break;
                case S:     Main.players.get(0).goSouth = true; break;
                case A:     Main.players.get(0).goWest  = true; break;
                case D:     Main.players.get(0).goEast  = true; break;
                case UP:    Main.players.get(1).goNorth = true; break;
                case DOWN:  Main.players.get(1).goSouth = true; break;
                case LEFT:  Main.players.get(1).goWest  = true; break;
                case RIGHT: Main.players.get(1).goEast  = true; break;
            }
        });

        Main.gameScene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:     Main.players.get(0).goNorth = false; break;
                case S:     Main.players.get(0).goSouth = false; break;
                case A:     Main.players.get(0).goWest  = false; break;
                case D:     Main.players.get(0).goEast  = false; break;
                case UP:    Main.players.get(1).goNorth = false; break;
                case DOWN:  Main.players.get(1).goSouth = false; break;
                case LEFT:  Main.players.get(1).goWest  = false; break;
                case RIGHT: Main.players.get(1).goEast  = false; break;
            }
        });

        AnimationTimer playerTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Main.players.forEach(player -> {
                    if (player.getView().getTranslateY() <= -90) {
                        player.moveObjectTo(player.spawn.getX(), player.spawn.getY());
                        player.score++;
                        String tempText2 = scoreboardString.toString();
                        for (int i = 0; i < Main.players.size(); i++) {
                            GameObjectPlayer pl = Main.players.get(i);
                            tempText2 = tempText2.replace("{pl" + pl.index + "}", "" + pl.score);
                        }
                        scoreText.setText(tempText2);
                    }
                    Main.cars.forEach(it -> {
                        if (it.getView().getBoundsInParent().intersects(player.getView().getBoundsInParent().getMinX() + 5, player.getView().getBoundsInParent().getMinY() + 5, player.getView().getBoundsInParent().getWidth() - 10, player.getView().getBoundsInParent().getHeight() - 10)) {
                            player.moveObjectTo(player.spawn.getX(), player.spawn.getY());
                        }
                    });
                    int dx = 0, dy = 0;

                    if (player.goNorth) dy -= 2;
                    if (player.goSouth) dy += 2;
                    if (player.goEast)  dx += 2;
                    if (player.goWest)  dx -= 2;

                    player.moveObjectBy(dx, dy);
                });
            }
        };
        playerTimer.start();

        AnimationTimer carTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<GameObjectCar> removedCars = new ArrayList<>();
                Main.cars.forEach(car -> {
                    double width = (car.goWest ? (-1) : 1) * (Main.primaryStage.getWidth() / 2 + car.getView().getBoundsInLocal().getWidth());

                    if ((car.goEast && (car.getView().getTranslateX() - 1) > width) || (car.goWest && (car.getView().getTranslateX()) < width) ) {
                        car.alive = false;
                        Main.gameRoot.getChildren().remove(car.getView());
                        removedCars.add(car);
                    }
                    int dx = 0, dy = 0;

                    if (car.goEast)  dx += car.lane.speed;
                    if (car.goWest)  dx -= car.lane.speed;

                    car.moveObjectBy(dx, dy);
                });
                removedCars.forEach(it -> {
                    Main.cars.remove(it);
                });
            }
        };
        carTimer.start();

        List<CarLane> carLanes = Stream.of(new CarLane(-12, false, randomLineSpeed()),
                new CarLane(38, true, randomLineSpeed()),
                new CarLane(130, false, randomLineSpeed()),
                new CarLane(186, true, randomLineSpeed()),
                new CarLane(276, false, randomLineSpeed()),
                new CarLane(330, true, randomLineSpeed())).collect(Collectors.toList());

        AnimationTimer spawnTimer = new AnimationTimer() {
            private long prevTime = 0;
            private long prevTime2 = 0;
            private double[] lastSpawns = new double[2];

            @Override
            public void handle(long now) {
                if ((now - prevTime) < 400 * 1000000) {
                    return;
                };
                CarLane carLane = carLanes.stream().filter(it -> {
                    if (it.posY == lastSpawns[0] || it.posY == lastSpawns[1]) {
                        return false;
                    } else return true;
                }).collect(Collectors.toList()).get(new Random().nextInt(carLanes.size() - 2));
                GameObjectCar car = new GameObjectCar(carLane);
                Main.gameRoot.getChildren().add(car.getView());
                double x = (carLane.left ? (-1) : 1) * (Main.primaryStage.getWidth() / 2 + car.getView().getBoundsInLocal().getWidth());
                car.moveObjectTo(x, carLane.posY);
                Main.cars.add(car);

                if (carLane.left) {
                    car.goEast = true;
                } else {
                    car.goWest = true;
                }

                lastSpawns[1] = lastSpawns[0];
                lastSpawns[0] = carLane.posY;

                prevTime = now;

                if ((now - prevTime2) > (long) (Main.random.nextInt(3) + 1) * 1000 * 1000000) {
                    carLanes.get(Main.random.nextInt(carLanes.size())).speed = randomLineSpeed();
                    prevTime2 = now;
                };
            }
        };
        spawnTimer.start();
    }

    List<Integer> values = Stream.of( 4, 4, 4, 7, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 8).collect(Collectors.toList());

    private int randomLineSpeed() {
        return values.get(Main.random.nextInt(values.size()));
    }

    public class CarLane {
        public double posY;
        public boolean left;
        public int speed;

        public CarLane(double posY, boolean left, int speed) {
            this.posY = posY;
            this.left = left;
            this.speed = speed;
        }
    }
}
