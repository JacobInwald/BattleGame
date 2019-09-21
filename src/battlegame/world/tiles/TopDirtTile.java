package battlegame.world.tiles;

import java.awt.image.BufferedImage;

public class TopDirtTile extends Tile {

	public TopDirtTile(BufferedImage texture, int id) {
		super(texture, id);
		this.solid = true;
		this.turnsGravityOn = false;
	}

}
