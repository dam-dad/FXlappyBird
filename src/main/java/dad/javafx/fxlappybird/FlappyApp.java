package dad.javafx.fxlappybird;

import dad.game.Game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class FlappyApp extends Application {
	
	private Game game;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Canvas canvas = new Canvas(576, 512);

		Scene scene = new Scene(new Group(canvas), canvas.getWidth(), canvas.getHeight());
		
		primaryStage.setTitle("FXlappyBird");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
		
		game = new FlappyGame(canvas);
		game.bindInputToScene(scene);
		game.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
