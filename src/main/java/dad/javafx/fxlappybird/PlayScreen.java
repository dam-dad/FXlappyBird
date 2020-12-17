package dad.javafx.fxlappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dad.game.BackgroundSprite;
import dad.game.Screen;
import dad.game.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class PlayScreen extends Screen {

	private MenuButton menuButton;
	private FPSSprite fps;
	private Sprite bird;
	private BackgroundSprite background;
	private LandSprite land;
	private GameOverSprite gameover;
	private GetReadySprite ready;
	private ScoreSprite score;
	private List<PipeSprite> pipes;
	
	private Random random = new Random();
	private long timeBetweenPipes;
	private boolean isGameover;
	private int rnd;
	private boolean betweenPipes;
	private long pipesDistance;
	private long globalTime;
	private float pipesSpeed;

	public PlayScreen() {
		super("play");
	}
	
	@Override
	protected void init() {
		
		background = new BackgroundSprite(this, "bg_day.png");
		background.init();
		
		fps = new FPSSprite(this);
		
		bird = new JumpingBirdSprite(this); // FlyingBirdSprite(this);
		land = new LandSprite(this);
		gameover = new GameOverSprite(this);
		ready = new GetReadySprite(this);
		menuButton = new MenuButton(this) {
			protected void click() {
				getGame().setCurrentScreen("menu");
			}
		};
		pipes = new ArrayList<PipeSprite>();
		score = new ScoreSprite(this);
		
		gameover.setVisible(false);
		
		isGameover = false;
		betweenPipes = false;
		timeBetweenPipes = 0;
		rnd = 0;
		pipesDistance = 1000L;
		globalTime = 0L;
		pipesSpeed = PipeSprite.SPEED;
		
		addPipe(50);
	}

	@Override
	protected void update(long timeDiff) {
		
		fps.update(timeDiff);		
		menuButton.update(timeDiff);
		
		if (getInput().isQuit()) {
			getGame().setCurrentScreen("menu");
		}
		else if (isGameover) {
			gameover.setVisible(true);
		}
		else if (isPlaying()) {
			
			land.update(timeDiff);
	        background.update(timeDiff);
			bird.update(timeDiff);
			score.update(timeDiff);
			
			// crea una tuber�a cada 1-1.5 segundos 
			if (timeBetweenPipes > pipesDistance + rnd) {
				
				int pipeY = -100 + random.nextInt(200); 
				
				addPipe(pipeY);

				timeBetweenPipes = 0;
				
				rnd = 100; random.nextInt(500);
			}
			
			if (globalTime >= 500L) {
				pipesDistance -= 10;
				pipesSpeed -= 5f;
				for (PipeSprite pipe : pipes) {
					pipe.setSpeed(pipesSpeed);
				}
				land.setSpeed(pipesSpeed);
				background.setSpeed(background.getSpeed() - 2.5f);
				globalTime = 0;
			}
			
			// actualiza las tuber�as
			List<Sprite> removedPipes = new ArrayList<Sprite>();
			for (Sprite pipe : pipes) {
				pipe.update(timeDiff);
				if (!pipe.getBounds().intersects(0, 0, getWidth(), getHeight())) {
					removedPipes.add(pipe);
				}
			}
			pipes.removeAll(removedPipes);
			
			checkCollisions();
			
			timeBetweenPipes += timeDiff;

			globalTime += timeDiff;

		} 
		else if (ready.isVisible()){
			
			ready.update(timeDiff);
			
		}
		
	}

	private void addPipe(int pipeY) {
		PipeUpSprite newPipeUp = new PipeUpSprite(this);
		newPipeUp.init();
		newPipeUp.setSpeed(pipesSpeed);
		newPipeUp.move(0, pipeY);

		PipeDownSprite newPipeDown = new PipeDownSprite(this);
		newPipeDown.init();
		newPipeDown.setSpeed(pipesSpeed);
		newPipeDown.move(0, pipeY);
		
		pipes.add(newPipeUp);
		pipes.add(newPipeDown);
	}
	
	private boolean isPlaying() {
		return (!isGameover && !ready.isVisible());
	}

	@Override
	protected void render(GraphicsContext gc) {
		background.paint(gc);
		for (Sprite pipe : pipes) {
			pipe.paint(gc);
		}
		land.paint(gc);
		fps.paint(gc);
		menuButton.paint(gc);
		bird.paint(gc);
		score.paint(gc);
		gameover.paint(gc);
		ready.paint(gc);
	}
	
	private void checkCollisions() {
		
		// evitar que el pajarito se salga por arriba o por abajo
		if (bird.getY() < 0) bird.setY(0);
		if (bird.getY() + bird.getHeight() > getHeight()) bird.setY(getHeight() - bird.getHeight());

		// choque con el suelo
		if (bird.getBounds().intersects(land.getBounds())) {
			isGameover = true;
//			Sounds.load("collision.aiff").play();			
		}
		
		// choque con tuber�a
		for (Sprite pipe : pipes) {
			if (bird.getBounds().intersects(pipe.getBounds())) {
				isGameover = true;
//				Sounds.load("collision.aiff").play();							
			}
		}
		
		// comprueba que se paso la tuber�a (aumentar puntuaci�n)
		boolean between = false;
		for (Sprite pipe : pipes) {
			if (bird.getX() >= pipe.getX() && bird.getX() <= pipe.getX() + pipe.getWidth()) { 
				between = true;
			}
		}
		if (between && !betweenPipes) {
			score.incScore(1);
//			Sounds.load("point.aiff").play();
			betweenPipes = true;
		}
		if (!between && betweenPipes) {
			betweenPipes = false;
		}
		
	}
	
}
