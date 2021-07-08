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

        double x = cx + view.getLayoutX() + dx;
        double y = cy + view.getLayoutY() + dy;

        moveObjectTo(x, y);
    }

    public void moveObjectTo(double x, double y) {
        final double cx = view.getBoundsInLocal().getWidth()  / 2;
        final double cy = view.getBoundsInLocal().getHeight() / 2;

        if (x + cx <= Main.gameScene.getWidth() &&
                y + cy <= Main.gameScene.getHeight()) {
            view.setTranslateX(x - cx);
            view.setTranslateY(y - cy);
        }
    }

    public Node getView() {
        return view;
    }
}
