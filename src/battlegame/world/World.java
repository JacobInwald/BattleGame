package battlegame.world;

import java.io.IOException;
import java.util.ArrayList;

import battlegame.background.Background;
import battlegame.entities.enemies.Gooi;
import battlegame.entities.entitymanager.EntityManager;
import battlegame.entities.players.PlatformingPlayer;
import battlegame.graphics.Assets;
import battlegame.utility.Utilities;
import battlegame.world.tiles.Tile;
import battlegame.world.tiles.TileManager;
import javafx.scene.canvas.GraphicsContext;

public class World {
	
	private int width, height;
	private int[][] tiles;
	private ArrayList<Integer> ids;
	private int[] idArray;
	private Background bg;
	private String path;
	private String file;
	private EntityManager entityManager;
	private TileManager tileManager;
	private PlatformingPlayer player;
	
	public World(String path) {
		this.path = path;
		ids = new ArrayList<Integer>();
		entityManager = new EntityManager();
		tileManager = new TileManager();
		player = new PlatformingPlayer(300, 155);
		bg = new Background(Assets.background, width * Tile.tileWidth, height * Tile.tileHeight);

	}

	public void init() {
		entityManager.addEntity(player);
		entityManager.addEntity(new Gooi(300, 200));
		try {
			parseTileMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tileManager.init();
	}
	
	public void tick(){
		entityManager.tickEntities();
	}	
	
	public void render(GraphicsContext g) {
		for(int i = 0; i < 3; i ++) {
			if(i == 0)
				bg.render(g);
			if(i == 1) {
				for(int x = 0; x < width; x ++) {
					for(int y = 0; y < height; y++) {
						g.drawImage(getTile(x, y).getTexture(), x * Tile.tileWidth, y * Tile.tileHeight, Tile.tileWidth, Tile.tileHeight);
					}
				}
			}
			if(i == 2)
				entityManager.renderEntities(g);
				
		}
	}
	
	
	
	public Tile getTile(int x, int y) {
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
		this.width = idArray[0];
		this.height = idArray[1];
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = idArray[(x + y * width) + 2];
			}
		}
		bg.setWidth(width * Tile.tileWidth);
		bg.setHeight(height * Tile.tileHeight);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getTiles() {
		return tiles;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public TileManager getTileManager() {
		return tileManager;
	}
	
}
