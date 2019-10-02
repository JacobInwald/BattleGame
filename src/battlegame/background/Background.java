package battlegame.background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {
	
	private Image texture;
	private int width, height;
	
	public Background(Image background, int width, int height) {
		this.texture = background;
		this.width = width;
		this.height = height;
	}
	
	public void render(GraphicsContext g) {
		g.drawImage(texture, 0, 0, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
