package battlegame.graphics;

import java.awt.image.BufferedImage;

import battlegame.world.tiles.Tile;

public class Assets {

	public static int height = 16, width = 16;
	public static int tileHeight = 32, tileWidth = 32;
	
	
	public static BufferedImage player;
	public static BufferedImage skyTile, topDirtTile;
	public static SpriteSheet sprites, tiles;
	
	public static void init() {
		sprites = new SpriteSheet("/textures/Sprites.png");
		tiles = new SpriteSheet("/textures/Tiles.png");
		
		
		player = sprites.cropSheet(0, 0, width, height);
		
		skyTile = tiles.cropSheet(0, 0, tileWidth, tileHeight);
		topDirtTile = tiles.cropSheet(tileWidth, 0, tileWidth, tileHeight);
	}
	

}
