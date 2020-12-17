package dad.game;

import javafx.scene.canvas.GraphicsContext;

public abstract class Screen {

	private String name;
	private Game game;

	public Screen(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public Input getInput() {
		return getGame().getInput();
	}
	
	public double getWidth() {
		return game.getWidth();
	}

	public double getHeight() {
		return game.getHeight();
	}

	protected abstract void init();

	protected abstract void update(long timeDiff);

	protected abstract void render(GraphicsContext gc);

}
