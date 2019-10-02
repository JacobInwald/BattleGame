package battlegame.world.tiles;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

public class BouncyTile extends Tile{

	public BouncyTile(Image bouncyTile, int id) {
		super(bouncyTile, id);
		solid = true;
		bouncy = true;
		frictionCoefficient = (float) 1.2;
	}

}
