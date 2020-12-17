package dad.javafx.fxlappybird;

import dad.game.ImageSprite;
import dad.game.Screen;

public class TitleSprite extends ImageSprite {

	public TitleSprite(Screen screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("title.png"));
		setLocation((getScreen().getWidth() - getWidth()) / 2, (getScreen().getHeight() - getHeight()) / 4);
	}

	@Override
	public void update(long timeDiff) {
		// no hace nada
	}

}
