package dad.javafx.fxlappybird;

import dad.game.ImageSprite;
import dad.game.Screen;

public abstract class PipeSprite extends ImageSprite {

	public static final float SPEED = -250f;

	private double speed;

	public PipeSprite(Screen screen) {
		super(screen);
		speed = SPEED;
	}

	@Override
	public abstract void init();

	@Override
	public void update(long timeDiff) {
		double offset = speed * (timeDiff / 1000.0f);
		setX(getX() + offset);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
