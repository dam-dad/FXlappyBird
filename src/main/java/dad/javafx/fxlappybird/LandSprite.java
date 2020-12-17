package dad.javafx.fxlappybird;

import dad.game.Screen;
import dad.game.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LandSprite extends Sprite {
	
	public static final float SPEED = -250f; 

	private Image image;
	
	private double speed;
	private double imageX;
	private double imageWidth;
	
	public LandSprite(Screen screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		image = Images.load("land.png");
		setSize(getScreen().getWidth() - 1, image.getHeight() - 1);
		setLocation(0, getScreen().getHeight() - getHeight() - 1);
		imageX = 0f;
		imageWidth = image.getWidth();
		speed = SPEED;
	}

	@Override
	public void update(long timeDiff) {
		double offset = speed * (timeDiff / 1000.0f);
		imageX += offset;
		if (speed < 0 && imageX <= -imageWidth) {
			imageX = 0;
		} else if (speed > 0 && imageX >= 0) {
			imageX = -imageWidth;
		}
	}

	@Override
	public void paint(GraphicsContext g) {
		int x = (int) Math.round(imageX);
		while (x < getWidth()) {
			g.drawImage(image, getX() + x, getY());
			x += imageWidth;
		}
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
