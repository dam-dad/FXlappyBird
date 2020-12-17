package dad.game;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class Game extends AnimationTimer {

	private static final Double NANOSECOND = 1000000000.0; // 1.000.000.000

	private long lastTime;
	private int fps;
	private Canvas canvas;
	private Screen currentScreen;
	private Map<String, Screen> screens;
	private Input input;

	public Game(Canvas canvas) {
		this.screens = new HashMap<>();
		this.canvas = canvas;
		this.input = new Input();
	}
	
	@Override
	public void handle(long now) {
		long diffTime = now - lastTime;
		fps = (int) ((1.0 / diffTime) * NANOSECOND);

		clean(canvas.getGraphicsContext2D());
		
		currentScreen.update(diffTime / 1000000);
		currentScreen.render(canvas.getGraphicsContext2D());

		lastTime = now;
	}
	
	public void clean(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void start() {
		lastTime = System.nanoTime();
		init();
		super.start();
	}

	public int getFPS() {
		return fps;
	}

	public Input getInput() {
		return input;
	}

	protected void add(Screen screen) {
		screen.setGame(this);
		screens.put(screen.getName(), screen);
	}
	
	public void setCurrentScreen(Screen screen) {
		currentScreen = screen;
		currentScreen.init();
	}

	public void setCurrentScreen(String screenName) {
		setCurrentScreen(screens.get(screenName));
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public double getWidth() {
		return canvas.getWidth();
	}

	public double getHeight() {
		return canvas.getHeight();
	}
	
	public void bindInputToScene(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> onKeyPressed(e));
		scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> onKeyReleased(e));
		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> onMousePressed(e));
		scene.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> onMouseReleased(e));
		scene.addEventHandler(MouseEvent.MOUSE_MOVED, e -> onMouseMoved(e));
	}
	
	private void onMouseMoved(MouseEvent e) {
		getInput().setMouseX((int)e.getSceneX());
		getInput().setMouseY((int)e.getSceneY());
	}

	private void onMousePressed(MouseEvent e) {
		getInput().setClickCount(e.getClickCount());
	}

	private void onMouseReleased(MouseEvent e) {
		getInput().setClickCount(0);
	}

	private void onKeyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.UP)
			getInput().setUp(true);
		else if (e.getCode() == KeyCode.DOWN)
			getInput().setDown(true);
		else if (e.getCode() == KeyCode.LEFT)
			getInput().setLeft(true);
		else if (e.getCode() == KeyCode.RIGHT)
			getInput().setRight(true);
		else if (e.getCode() == KeyCode.SPACE)
			getInput().setShoot(true);
		else if (e.getCode() == KeyCode.ESCAPE)
			getInput().setQuit(true);
	}

	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.UP)
			getInput().setUp(false);
		else if (e.getCode() == KeyCode.DOWN)
			getInput().setDown(false);
		else if (e.getCode() == KeyCode.LEFT)
			getInput().setLeft(false);
		else if (e.getCode() == KeyCode.RIGHT)
			getInput().setRight(false);
		else if (e.getCode() == KeyCode.SPACE)
			getInput().setShoot(false);
		else if (e.getCode() == KeyCode.ESCAPE)
			getInput().setQuit(false);
	}

	protected abstract void init();

}
