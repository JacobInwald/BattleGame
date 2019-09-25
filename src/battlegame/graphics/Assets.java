package battlegame.graphics;

import java.awt.image.BufferedImage;

import battlegame.world.tiles.Tile;

public class Assets {

	public static int height = 16, width = 16;
	public static int tileHeight = 32, tileWidth = 32;
	
	
	public static BufferedImage player;
	public static BufferedImage background;
	public static BufferedImage skyTile, topDirtTile, brickTile, bouncyTile;
	public static SpriteSheet sprites, tiles, background1;
	
	public static void init() {
		sprites = new SpriteSheet("/textures/ArthurSpritesSheet.png");
		tiles = new SpriteSheet("/textures/TilesSheet.png");
		background1 = new SpriteSheet("/textures/testBackground.png"); 
		
		
		
		player = sprites.cropSheet(0, 0, 32, 45);
		
		brickTile = tiles.cropSheet(0, 0, tileWidth, tileHeight);
		skyTile = tiles.cropSheet(tileWidth, 0, tileWidth, tileHeight);
		bouncyTile = tiles.cropSheet(tileWidth * 2, 0, tileWidth, tileHeight);
		
		background = background1.getSheet();
	}
	

}
