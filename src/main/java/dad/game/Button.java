package dad.game;

import javafx.scene.image.Image;

public abstract class Button extends ImageSprite {

	private Image normalImage;
	private Image overImage;
	private Image clickedImage;

	public Button(Screen screen) {
		super(screen);
	}
	
	@Override
	public void update(long timeDiff) {
		if (!isVisible()) return;
		
		Input input = getScreen().getInput();
		if (getBounds().contains(input.getMouseX(), input.getMouseY())) {
//			getScreen().getGame().
			if (input.getClickCount() > 0) {
				setImage(clickedImage);
				input.resetClick();
//				getScene().getGame().setCursor(Cursor.getDefaultCursor());
				click();
			} else {
				setImage(overImage);
			}
		} else {
			setImage(normalImage);
//			getScreen().getGame().setCursor(Cursor.getDefaultCursor());
		}
	}

	public void setNormalImage(Image normalImage) {
		this.normalImage = normalImage;
		setImage(this.normalImage);
	}
	
	public Image getNormalImage() {
		return normalImage;
	}

	public void setOverImage(Image overImage) {
		this.overImage = overImage;
	}
	
	public Image getOverImage() {
		return overImage;
	}

	public void setClickedImage(Image clickedImage) {
		this.clickedImage = clickedImage;
	}
	
	public Image getClickedImage() {
		return clickedImage;
	}

	protected abstract void click();

}
