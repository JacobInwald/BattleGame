package battlegame.world;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import battlegame.entities.Bullet;
import battlegame.entities.Player;
import battlegame.entities.entitymanager.EntityManager;
import battlegame.utility.Utilities;
import battlegame.world.tiles.Tile;
import battlegame.world.tiles.TileManager;

public class World {
	
	private static int width, height;
	private static int[][] tiles;
	private ArrayList<Integer> ids;
	private int[] idArray;
	private String path;
	private String file;
	private static EntityManager entityManager;
	private TileManager tileManager;
	private Player player;
	
	public World(String path) {
		this.path = path;
		ids = new ArrayList<Integer>();
		entityManager = new EntityManager();
		tileManager = new TileManager();
		player = new Player(100, 300);
	}

	public void init() throws IOException {
		entityManager.addEntity(player);
		parseTileMap();
		
		tileManager.init();
	}
	
	public void tick(){
		entityManager.tickEntities();
	}	
	
	public void render(Graphics g) {
		for(int x = 0; x < width; x ++) {
			for(int y = 0; y < height; y++) {
				g.drawImage(getTile(x, y).getTexture(), x * Tile.tileWidth, y * Tile.tileHeight, Tile.tileWidth, Tile.tileHeight, null);
			}
		}
		entityManager.renderEntities(g);
	}
	
	
	public static Tile getTile(int x, int y) {
		if(x > width || y > height || x < 0 || y < 0) 
			return Tile.skyTile;
		
		else if(Tile.tiles[tiles[x][y]] == null)
			return Tile.skyTile;

		return Tile.tiles[tiles[x][y]];
	}
	
	public void parseTileMap() throws IOException{
		file = Utilities.loadFileAsString(path);
		System.out.println(file);
		for(String s : file.split("\\s+")) {
			ids.add(Utilities.parseInt(s));
		}
		idArray = ids.stream().mapToInt(Integer::intValue).toArray();
		World.width = idArray[0];
		World.height = idArray[1];
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = idArray[(x + y * width) + 2];
			}
		}

	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int[][] getTiles() {
		return tiles;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
	
	
}
