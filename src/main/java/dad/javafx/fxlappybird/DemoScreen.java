package dad.javafx.fxlappybird;

import dad.game.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DemoScreen extends Screen {

	private FPSSprite fps;

	public DemoScreen() {
		super("demo");
	}

	@Override
	protected void init() {
		fps = new FPSSprite(this);
		fps.init();
	}

	@Override
	protected void update(long timeDiff) {
		if (getInput().isQuit()) {
			getGame().setCurrentScreen("menu");
		}
		fps.update(timeDiff);
	}

	@Override
	protected void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, getWidth(), getHeight());
		gc.setFill(Color.BLACK);
		gc.fillText("width: " + getWidth() + " / height: " + getHeight() + " / up: " + getGame().getInput().isUp() + " / down: " + getGame().getInput().isDown(), 5, 15);		
		fps.paint(gc);
	}

}
