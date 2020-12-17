package dad.game;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {
	private double x, y;
	private double width, height;
	private Screen screen;
	private boolean visible;

	public Sprite(Screen screen) {
		visible = true;
		setX(x);
		setY(y);
		this.screen = screen;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setLocation(double x, double y) {
		setX(x);
		setY(y);
	}

	public void setSize(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	protected Screen getScreen() {
		return screen;
	}

	public void move(double incX, double incY) {
		x += incX;
		y += incY;
	}

	public Bounds getBounds() {
		return new BoundingBox(x, y, width, height);
	}
	
	public void centerOnScene() {
		setLocation((getScreen().getWidth() - getWidth()) / 2, (getScreen().getHeight() - getHeight()) / 2);
	}

	public abstract void init();
	public abstract void update(long timeDiff);
	public abstract void paint(GraphicsContext gc);

}
