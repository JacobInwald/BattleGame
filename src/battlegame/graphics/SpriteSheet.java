package battlegame.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

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
	
	public Image cropSheet(int x, int y, int width, int height) {
		return convertToImage(sheet.getSubimage(x, y, width, height));
	}

	private static Image convertToImage(BufferedImage image) {
	    WritableImage wr = null;
	    if (image != null) {
	        wr = new WritableImage(image.getWidth(), image.getHeight());
	        PixelWriter pw = wr.getPixelWriter();
	        for (int x = 0; x < image.getWidth(); x++) {
	            for (int y = 0; y < image.getHeight(); y++) {
	                pw.setArgb(x, y, image.getRGB(x, y));
	            }
	        }
	    }

	    return new ImageView(wr).getImage();
	}
	
	public Image getSheet() {
		return convertToImage(sheet);
	}
	
}
