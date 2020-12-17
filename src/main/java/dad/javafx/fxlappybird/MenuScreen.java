package dad.javafx.fxlappybird;

import dad.game.BackgroundSprite;
import dad.game.Screen;
import javafx.scene.canvas.GraphicsContext;

public class MenuScreen extends Screen {

	private PlayButton playButton;
	private BackgroundSprite background;
	private TitleSprite title;

	public MenuScreen() {
		super("menu");
	}

	@Override
	protected void init() {
		background = new BackgroundSprite(this, "bg_night.png");
		title = new TitleSprite(this);
		playButton = new PlayButton(this) {
			protected void click() { letsPlay(); }
		};
		
		background.init();
		title.init();
		playButton.init();
	}

	@Override
	protected void update(long timeDiff) {
		if (getInput().isShoot()) {
			letsPlay();
		}
        background.update(timeDiff);
		title.update(timeDiff);
		playButton.update(timeDiff);
	}

	@Override
	protected void render(GraphicsContext g) {
		background.paint(g);
		title.paint(g);
		playButton.paint(g);
	}
	
	protected void letsPlay() {
		getGame().setCurrentScreen("play");
	}
	
}
