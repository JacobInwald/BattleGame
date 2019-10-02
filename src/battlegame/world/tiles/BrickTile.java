package battlegame.world.tiles;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

public class BrickTile extends Tile {

	public BrickTile(Image brickTile, int id) {
		super(brickTile, id);
		this.solid = true;
		this.turnsGravityOn = false;
		this.wallSlideCoefficient = 1.1F;
	}

}
