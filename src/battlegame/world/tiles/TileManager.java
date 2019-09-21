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
		//setCoordinates();
	}
	
	public void tickTiles() {
		for(Tile[] tArray : tiles) {
			for(Tile t : tArray) {
				t.tick();
			}
		}
	}
	
/*	public void setCoordinates() {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				bounds[x][y].x = x * Tile.tileWidth;
				bounds[x][y].y = y * Tile.tileHeight;
				System.out.print(x);
				System.out.print(' ');
				System.out.println(y);
				System.out.println(bounds[0][0].x);
				
			}
		}
	}*/
	
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
					//System.out.println(true);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static float tileFrictionCoefficient(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (e.getBounds().intersects(bounds[x][y])) {
					if((y + 1) >= World.getHeight())
						return 1;
					return tiles[x][y + 1].frictionCoefficient;
				}
			}
		}
		return 1;
		
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
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) && ((Creature) e).getySpeed() > 0
					&& e.getY() + (e.getHeight() / 2) < bounds[x][y].getY()) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCollidingInNegY(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) && ((Creature) e).getySpeed() < 0
					&& bounds[x][y].getY() + (bounds[x][y].getHeight() / 2) < e.getY()) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCollidingWithBouncyTile(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) && ((Creature) e).getySpeed() > 0
					&& e.getY() + (e.getHeight() / 2) < bounds[x][y].getY() && tiles[x][y].bouncy) { 
					return true;
				}
			}
		}
		return false;
	}	
}
