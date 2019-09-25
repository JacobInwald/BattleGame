package battlegame.world.tiles;

import java.awt.image.BufferedImage;

public class BrickTile extends Tile {

	public BrickTile(BufferedImage texture, int id) {
		super(texture, id);
		this.solid = true;
		this.turnsGravityOn = false;
		this.wallSlideCoefficient = 1.1F;
	}

}
