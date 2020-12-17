package dad.javafx.fxlappybird;

import dad.game.Game;
import javafx.scene.canvas.Canvas;

public class FlappyGame extends Game {

	public FlappyGame(Canvas canvas) {
		super(canvas);
	}

	@Override
	protected void init() {
		add(new MenuScreen());
		add(new DemoScreen());
		add(new PlayScreen());
		setCurrentScreen("menu");
	}

}
