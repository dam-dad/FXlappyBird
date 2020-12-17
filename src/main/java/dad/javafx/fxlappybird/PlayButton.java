package dad.javafx.fxlappybird;

import dad.game.Button;
import dad.game.Screen;

public abstract class PlayButton extends Button {
	
	public PlayButton(Screen screen) {
		super(screen);
	}

	@Override
	public void init() {		
		setNormalImage(Images.load("button_play.png"));
		setClickedImage(Images.load("button_play_over.png"));
		setOverImage(Images.load("button_play_over.png"));
		setImage(getNormalImage());
		setLocation((getScreen().getWidth() - getWidth()) / 2, (getScreen().getHeight() - getHeight()) / 2);
	}
	
}
