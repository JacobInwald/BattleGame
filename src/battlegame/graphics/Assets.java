package battlegame.graphics;

import javafx.scene.image.Image;

public class Assets {

	public static int height = 32, width = 32;
	public static int tileHeight = 32, tileWidth = 32;
	
	public static Image player, gooiFront;
	public static Image background;
	public static Image skyTile, topDirtTile, brickTile, bouncyTile;
	public static SpriteSheet arthurSprites, tiles, background1, gooiSprites;
	public static String samMusic, testSong;
	
	public static void init() {
		arthurSprites = new SpriteSheet("/ArthurSpritesSheet.png");
		tiles = new SpriteSheet("/TilesSheet.png");
		background1 = new SpriteSheet("/WaterBackground2.png"); 
		gooiSprites = new SpriteSheet("/GooiFront.png");
		
		
		player = arthurSprites.cropSheet(0, 0, 32, 45);
		
		gooiFront = gooiSprites.cropSheet(0, 0, width, height);
		
		brickTile = tiles.cropSheet(0, 0, tileWidth, tileHeight);
		skyTile = tiles.cropSheet(tileWidth, 0, tileWidth, tileHeight);
		bouncyTile = tiles.cropSheet(tileWidth * 2, 0, tileWidth, tileHeight);
		
		background = background1.getSheet();
		
		samMusic = "/sam.wav";
		testSong = "/Test Song.wav";
	}
	

}
