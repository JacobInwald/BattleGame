package battlegame.world.tiles;

import java.awt.image.BufferedImage;

public class SkyTile extends Tile {
	
	
	public SkyTile(BufferedImage texture, int id) {
		super(texture, id);
		solid = false;
		frictionCoefficient = 1.05125F;
	}

	
}
