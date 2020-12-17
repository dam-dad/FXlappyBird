package dad.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class ImageSprite extends Sprite {

	private Image image;
	
	public ImageSprite(Screen screen) {
		super(screen);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		adjustToImage();
	}
	
	public void adjustToImage() {
		setSize(getImage().getWidth(), getImage().getHeight());
	}

	@Override
	public void paint(GraphicsContext gc) {
		if (isVisible()) {
			gc.drawImage(image, getX(), getY());
		}
	}

}
