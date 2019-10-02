package battlegame.world.tiles;

import javafx.scene.image.Image;

public class TopDirtTile extends Tile {

	public TopDirtTile(Image texture, int id) {
		super(texture, id);
		this.solid = true;
		this.turnsGravityOn = false;
		wallSlideCoefficient = 1.1F;
	}

}
