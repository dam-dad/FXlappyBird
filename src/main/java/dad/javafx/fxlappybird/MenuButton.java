package dad.javafx.fxlappybird;

import dad.game.Button;
import dad.game.Screen;

public abstract class MenuButton extends Button {
	
	public MenuButton(Screen screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {		
		setNormalImage(Images.load("button_menu.png"));
		setClickedImage(Images.load("button_menu.png"));
		setOverImage(Images.load("button_menu.png"));
		setLocation(getScreen().getWidth() - getWidth() - 5, getScreen().getHeight() - getHeight() - 5);
	}
	
}
