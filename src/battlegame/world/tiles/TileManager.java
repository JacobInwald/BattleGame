package battlegame.world.tiles;

import java.awt.Rectangle;

import battlegame.entities.Creature;
import battlegame.entities.Entity;
import battlegame.world.World;

public class TileManager {
	
	private static Rectangle[][] bounds;
	private static Tile[][] tiles;
	
	public TileManager() {}
	
	public void init() {
		bounds = new Rectangle[World.getWidth()][World.getHeight()];
		tiles = new Tile[World.getWidth()][World.getHeight()];	
		for(int x = 0; x < World.getWidth(); x ++) {
			for(int y = 0; y < World.getHeight(); y++) {
				tiles[x][y] = World.getTile(x, y);
			}
		}
		setCollisionBounds();
	}
	
	public void tickTiles() {
		for(Tile[] tArray : tiles) {
			for(Tile t : tArray) {
				t.tick();
			}
		}
	}
	
	public void setCollisionBounds() {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				bounds[x][y] = new Rectangle((x * Tile.tileWidth), (y * Tile.tileHeight), Tile.tileWidth, Tile.tileHeight);

			}
		}
	}
	
	public static boolean checkTileCollision(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static float tileFrictionCoefficient(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				e.getBounds().x = (int)(e.getX() + (e.getWidth() / 2));
				if (e.getBounds().intersects(bounds[x][y])) {
					if((y + 1) >= World.getHeight())
						return Tile.skyTile.frictionCoefficient;
					return tiles[x][y + 1].frictionCoefficient;
				}
				e.getBounds().x = (int)(e.getX());
				if (e.getBounds().intersects(bounds[x][y])) {
					if((y + 1) >= World.getHeight())
						return Tile.skyTile.frictionCoefficient;
					return tiles[x + 1][y + 1].frictionCoefficient;
				}
				
			}
		}
		return Tile.skyTile.frictionCoefficient;
	} 
	
	public static boolean isCollidingInPosX(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) && ((Creature) e).getxSpeed() > 0 
					&& e.getX() - (e.getWidth() / 2) < bounds[x][y].getX() && (e.getY() + e.getHeight()) >= bounds[x][y].y + 3){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCollidingInNegX(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) && ((Creature) e).getxSpeed() < 0
					&& (bounds[x][y].getX() + (bounds[x][y].getWidth() / 2) < e.getX()) && (e.getY() + e.getHeight()) >= bounds[x][y].y + 3) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCollidingInPosY(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])
					&& e.getY() + (e.getHeight() / 2) < bounds[x][y].getY() - 1) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCollidingInNegY(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])
					&& bounds[x][y].getY() + (bounds[x][y].getHeight() / 2) < e.getY() + 1) { 
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static boolean isCollidingWithBouncyTile(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (e.getBounds().intersects(bounds[x][y]) && tiles[x][y].bouncy) { 
					return true;
				}
			}
		}
		return false;
	}	
	
	public static boolean returnTileBelow(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				//The statements (tiles[(int)(e.getX() / Tile.tileWidth)][(int)(e.getY() / Tile.tileHeight) + 1].solid) and 
				// (tiles[(int)(e.getX() / Tile.tileWidth) + 1][(int)(e.getY() / Tile.tileHeight) + 1].solid) checks whether the tiles
				// to the left of the player is solid
				// The statement (tiles[(int)((e.getX() + e.getWidth()) / Tile.tileWidth)][(int)(e.getY() / Tile.tileHeight) + 1].solid) checks
				// whether the tiles to the right of the player are solid

				if ((e.getY() + Tile.tileHeight)  >= World.getHeight() * Tile.tileHeight) {
					System.out.println(true);
					return true;
				}
				if (e.getBounds().intersects(bounds[x][y]) &&
					(tiles[(int)(e.getX() / Tile.tileWidth)][(int)(e.getY() / Tile.tileHeight) + 1].solid) &&
					!(tiles[(int)((e.getX() + e.getWidth()) / Tile.tileWidth)][(int)(e.getY() / Tile.tileHeight) + 1].solid)) {
						return tiles[x][y + 1].isTurnsGravityOn();
				}
				else if (e.getBounds().intersects(bounds[x][y]) && 
					!(tiles[(int)((e.getX() + e.getWidth()) / Tile.tileWidth)][(int)(e.getY() / Tile.tileHeight) + 1].solid) &&
					(tiles[(int)(e.getX() / Tile.tileWidth) + 1][(int)(e.getY() / Tile.tileHeight) + 1].solid) ) {
						return tiles[x + 1][y + 1].isTurnsGravityOn();
				}
				else if(e.getBounds().intersects(bounds[x][y]) && 
						(tiles[(int)((e.getX() + e.getWidth()) / Tile.tileWidth)][(int)(e.getY() / Tile.tileHeight) + 1].solid) &&
						(tiles[(int)(e.getX() / Tile.tileWidth) + 1][(int)(e.getY() / Tile.tileHeight) + 1].solid) ) {
					return false;
				}
			}
		}
		return true;
	} 
}
