package battlegame.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private BufferedImage sheet;
	private String filepath;
	private File imageFile;
	
	public SpriteSheet(String filepath) {
		this.filepath = filepath;
		imageFile = new File(filepath);
		try {
			sheet = ImageIO.read(SpriteSheet.class.getResource(filepath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public BufferedImage cropSheet(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public BufferedImage getSheet() {
		return sheet;
	}
	
}
