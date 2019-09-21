package battlegame.world.tiles;

import java.awt.image.BufferedImage;

public class BouncyTile extends Tile{

	public BouncyTile(BufferedImage texture, int id) {
		super(texture, id);
		solid = true;
		bouncy = true;
		frictionCoefficient = (float) 1.2;
	}

}
