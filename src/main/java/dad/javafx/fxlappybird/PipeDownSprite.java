package dad.javafx.fxlappybird;

import dad.game.Screen;

public class PipeDownSprite extends PipeSprite {

	public PipeDownSprite(Screen screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("pipe_down.png"));
		setLocation(getScreen().getWidth(), -200);
	}

}
