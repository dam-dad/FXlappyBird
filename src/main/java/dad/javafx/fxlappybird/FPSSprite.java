package dad.javafx.fxlappybird;

import dad.game.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FPSSprite extends NumberSprite {
	
	private long ms = 0L;
	private Image fpsImage;
	
	public FPSSprite(Screen screen) {
		super(screen);
	}

	@Override
	public void init() {
		super.init();
		ms = 0;
		fpsImage = Images.load("fps.png");
        setNumber(0);
		setSize(0, fpsImage.getHeight());
		setLocation(5, getScreen().getHeight() - getHeight() - 5);
	}
	
	@Override
	public void update(long timeDiff) {		
        ms += timeDiff;
        if (ms >= 250) {
        	setNumber((int)(1000.0 / timeDiff));
        	ms = 0;
        }
	}
	
	@Override
	public void paint(GraphicsContext g) {
		super.paint(g);
		int width = calcWidth("" + getNumber());
		g.drawImage(fpsImage, getX() + width + 1, getY());
	}

}
