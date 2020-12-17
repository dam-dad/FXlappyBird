package dad.javafx.fxlappybird;

import dad.game.ImageSprite;
import dad.game.Screen;
import javafx.scene.image.Image;

public class FlyingBirdSprite extends ImageSprite {
	
	private static final float SPEED = 1000.0f; // velocidad en p�xeles/segundo (px/s)

	private Image upImage, stillImage, downImage; 
//	private Sound sound;
	
	public FlyingBirdSprite(Screen screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		upImage = Images.load("bird0_2.png");
		stillImage = Images.load("bird0_1.png");
		downImage = Images.load("bird0_0.png");
//		sound = Sounds.load("fly.aiff");
		still();
		setLocation(getScreen().getWidth() / 4, getScreen().getHeight() / 3);			
	}

	@Override
	public void update(long timeDiff) {
		float offset = 0f;
		if (getScreen().getInput().isUp()) {
			up(timeDiff);			
		} else if (getScreen().getInput().isDown()) {
			down(timeDiff);
		} else {
			still();
		}
		move(0, offset);
	}

	private void still() {
		setImage(stillImage);
	}
	
	private void up(long timeDiff) {
		float offset = -SPEED * (timeDiff / 1000.0f);
		if (getImage() == upImage)
			still();
		else if (getImage() == downImage)
			setImage(upImage);
		else if (getImage() == stillImage)
			setImage(downImage);
//		sound.playIfCompleted();
		move(0, offset);
	}

	private void down(long timeDiff) {
		float offset = SPEED * (timeDiff / 1000.0f);
		setImage(downImage);
//		sound.playIfCompleted();
		move(0, offset);		
	}
}
