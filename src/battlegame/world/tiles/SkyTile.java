package battlegame.world.tiles;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

public class SkyTile extends Tile {
	
	
	public SkyTile(Image skyTile, int id) {
		super(skyTile, id);
		solid = false;
		frictionCoefficient = 1.05125F;
	}

	
}
