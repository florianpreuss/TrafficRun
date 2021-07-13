package trafficrun.gameobjects;

import javafx.scene.Node;
import trafficrun.Main;

public abstract class GameObject {
    private Node view;

    public boolean goNorth, goSouth, goEast, goWest, alive;

    public GameObject(Node view) {
        this.view = view;
    }

    public void moveObjectBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = view.getBoundsInLocal().getWidth()  / 2;
        final double cy = view.getBoundsInLocal().getHeight() / 2;

        double x = cx + view.getTranslateX() + dx;
        double y = cy + view.getTranslateY() + dy;

        moveObjectTo(x, y);
    }

    public void moveObjectTo(double x, double y) {
        final double cx = view.getBoundsInLocal().getWidth()  / 2;
        final double cy = view.getBoundsInLocal().getHeight() / 2;

        double width = (Main.gameScene.getWidth() / 2) * (x < 0 ? (-1) : 1) + cx;
        double height = (Main.gameScene.getHeight() / 2) * (y < 0 ? (-1) : 1) + cy;

        if (((width >= 0 && x <= width) || (width < 0 && x >= width))) {
            view.setTranslateX(x - cx);
        }
        if (((height >= 0 && y <= height) || (height < 0 && y >= height))) {
            view.setTranslateY(y - cy);
        }
    }

    public Node getView() {
        return view;
    }
}
