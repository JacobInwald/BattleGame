package battlegame.world.tiles;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

public class IceTile extends Tile {

	public IceTile(Image player, int id) {
		super(player, id);
		solid = true;
		frictionCoefficient = (float) 1.05;
		// TODO Auto-generated constructor stub
	}

}
