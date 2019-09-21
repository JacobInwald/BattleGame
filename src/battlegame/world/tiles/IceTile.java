package battlegame.world.tiles;

import java.awt.image.BufferedImage;

public class IceTile extends Tile {

	public IceTile(BufferedImage texture, int id) {
		super(texture, id);
		solid = true;
		frictionCoefficient = (float) 1.05;
		// TODO Auto-generated constructor stub
	}

}
