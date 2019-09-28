package battlegame.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	
	private BufferedImage texture;
	private int width, height;
	
	public Background(BufferedImage texture, int width, int height) {
		this.texture = texture;
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, 0, 0, width, height, null);
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
