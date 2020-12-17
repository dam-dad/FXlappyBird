package dad.javafx.fxlappybird;

import dad.game.ImageSprite;
import dad.game.Screen;
import javafx.scene.image.Image;

public class GetReadySprite extends ImageSprite {
	
	private long time;
	private Image [] imageSequence; 
	private int current = 0;

	public GetReadySprite(Screen screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		imageSequence = new Image[] {
			Images.load("font_051.png"),
			Images.load("font_050.png"),
			Images.load("font_049.png"),
			Images.load("text_ready.png"),
			};
		current = 0;
		time = 4000L;
		setImage(imageSequence[current]);
		centerOnScene();
	} 

	@Override
	public void update(long timeDiff) {
		if (time > 3000L) {
			current = 0;
		} else if (time > 2000L) {
			current = 1;
		} else if (time > 1000L) {
			current = 2;
		} else if (time > 0) {
			current = 3;
		} else if (time <= 0){
			setVisible(false);
		}
		setImage(imageSequence[current]);
		centerOnScene();
		if (time > 0) time -= timeDiff;
	}

}
