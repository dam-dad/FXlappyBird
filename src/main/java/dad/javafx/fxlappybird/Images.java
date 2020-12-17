package dad.javafx.fxlappybird;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Images {
	
	private static Map<String, Image> cache = new HashMap<String, Image>();

	public static Image load(String name) {
		Image image = cache.get(name);
		if (image == null) {
			image = new Image(Images.class.getResource("/images/" + name).toExternalForm());
			cache.put(name, image);
		}
		return image;
	}
	
}
