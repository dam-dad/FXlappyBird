package dad.javafx.fxlappybird;

import dad.game.Screen;
import dad.game.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class NumberSprite extends Sprite {
	private Integer number;
	private Image [] numbers;
	private double numberWidth;
	
	public NumberSprite(Screen screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		this.numbers = new Image[10];
		this.number = 0;
		for (int i = 0; i <= 9; i++) {
			numbers[i] = Images.load("number_context_0" + i + ".png");
		}
		this.numberWidth = numbers[0].getWidth();
	}

	@Override
	public void paint(GraphicsContext g) {
		int x = (int)getX();
		String text = "" + this.number;
		for (int i = 0; i < text.length(); i++) {
			int num = 0;
			try {
				num = Integer.parseInt(text.substring(i, i + 1));
			} catch (NumberFormatException e) {
				break;
			}
			g.drawImage(numbers[num], x, getY());
			x += numberWidth;
		}
	}
	
	protected int calcWidth(String text) {
		int width = 0;
		for (int i = 0; i < text.length(); i++) {
			width += numberWidth;
		}
		return width;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
