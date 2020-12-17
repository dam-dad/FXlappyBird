package dad.javafx.fxlappybird;

import dad.game.Screen;

public class PipeUpSprite extends PipeSprite {

	public PipeUpSprite(Screen screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("pipe_up.png"));
		setLocation(getScreen().getWidth(), getScreen().getHeight() / 2);
	}
	
}
