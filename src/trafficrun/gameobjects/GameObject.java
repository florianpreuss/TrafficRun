package trafficrun.gameobjects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import trafficrun.Main;

public abstract class GameObject {
    private int posX, posY;
    private Image image;
    private Node node;

    public boolean goNorth, goSouth, goEast, goWest;

    public GameObject(int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
    }

    private void moveObjectBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = node.getBoundsInLocal().getWidth()  / 2;
        final double cy = node.getBoundsInLocal().getHeight() / 2;

        double x = cx + node.getLayoutX() + dx;
        double y = cy + node.getLayoutY() + dy;

        moveObjectTo(x, y);
    }

    private void moveObjectTo(double x, double y) {
        final double cx = node.getBoundsInLocal().getWidth()  / 2;
        final double cy = node.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= Main.gameScene.getWidth() &&
                y - cy >= 0 &&
                y + cy <= Main.gameScene.getHeight()) {
            node.relocate(x - cx, y - cy);
        }
    }

}
