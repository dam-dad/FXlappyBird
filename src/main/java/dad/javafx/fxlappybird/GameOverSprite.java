package dad.javafx.fxlappybird;

import dad.game.ImageSprite;
import dad.game.Screen;

public class GameOverSprite extends ImageSprite {

	public GameOverSprite(Screen screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		setImage(Images.load("text_game_over.png"));
		centerOnScene();
		setVisible(false);
	}

	@Override
	public void update(long timeDiff) {
		// no hace nada
	}
	

}
