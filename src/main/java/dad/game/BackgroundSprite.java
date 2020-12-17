package dad.game;

import dad.javafx.fxlappybird.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundSprite extends Sprite {

	public static final float SPEED = -150.0f;

	private double speed;
	private Image image;
	private String imageName;

	private double imageX;
	private double imageWidth;

	public BackgroundSprite(Screen screen, String imageName) {
		super(screen);
		this.imageName = imageName;
		init();
	}

	@Override
	public void init() {
		setLocation(0, 0);
		setSize(getScreen().getWidth(), getScreen().getHeight());
		image = Images.load(imageName);
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
	public void paint(GraphicsContext gc) {
		int x = (int)Math.round(imageX);
		while (x < getWidth()) {
			gc.drawImage(image, getX() + x, getY());
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
