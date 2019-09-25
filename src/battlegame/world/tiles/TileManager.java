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
				if (e.getBounds().intersects(bounds[x][y])) {
					if((y + 1) >= World.getHeight() || (x + 1) >= World.getWidth())
						return Tile.skyTile.frictionCoefficient;
					if(((Creature) e).getxSpeed() < 0)
						return tiles[x][y + 1].frictionCoefficient;
					else if(((Creature) e).getxSpeed() > 0)
						return tiles[x + 1][y + 1].frictionCoefficient;
				}
			}
		}
		return Tile.skyTile.frictionCoefficient;
	} 
	
	/*public static float tileWallCoefficient(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if((x + 1) > World.getHeight() || (x - 1) < 0)
					return Tile.skyTile.wallSlideCoefficient;
					
				if (e.getBounds().intersects(bounds[x][y]) && tiles[x + 1][y].isSolid()) {
					return tiles[x + 1][y].frictionCoefficient;
				}
				
				if (e.getBounds().intersects(bounds[x][y]) && tiles[x - 1][y].isSolid()) {
					return tiles[x - 1][y].frictionCoefficient;
				}
				
			}
		}
		return Tile.skyTile.wallSlideCoefficient;
	}*/
	
	public static boolean ableToWallSlide(Entity e) {
		// This stops there being an array out of bounds exception when using the 2d array
		if((int)(e.getBounds().getX() / Tile.tileWidth) + 1 >= World.getWidth()|| 
			(int)(e.getBounds().getX() / Tile.tileWidth) + 2 >= World.getWidth()|| 
			(int)(e.getBounds().getY() / Tile.tileHeight) + 1 >= World.getHeight()) 
			return false;
		// This checks the tiles to the left, underneath and on the entity to check whether 
		// they are solid, not solid and not solid respectively. This is to ensure that the
		// entity is actually allowed to wall slide
		if (tiles[(int)(e.getBounds().getX() / Tile.tileWidth)][(int)(e.getBounds().getY() / Tile.tileHeight)].isSolid() 
				&& !tiles[(int)(e.getBounds().getX() / Tile.tileWidth) + 1][(int)(e.getBounds().getY() / Tile.tileHeight)].isSolid()
				&& !tiles[(int)(e.getBounds().getX() / Tile.tileWidth) + 1][(int)(e.getBounds().getY() / Tile.tileHeight) + 1].isSolid()
				&& TileManager.isCollidingInNegX(e)) {
			return true;
		}
		// This checks the tiles to the right, underneath and on the entity to check whether 
		// they are solid, not solid and not solid respectively. This is to ensure that the
		// entity is actually allowed to wall slide
		if (tiles[(int)(e.getBounds().getX() / Tile.tileWidth) + 1][(int)(e.getBounds().getY() / Tile.tileHeight)].isSolid() 
				&& !tiles[(int)(e.getBounds().getX() / Tile.tileWidth)][(int)(e.getBounds().getY() / Tile.tileHeight)].isSolid()
				&& !tiles[(int)(e.getBounds().getX() / Tile.tileWidth)][(int)(e.getBounds().getY() / Tile.tileHeight) + 1].isSolid()
				&& TileManager.isCollidingInPosX(e)) {
			return true;
		}

		return false;
	}
	
	
	public static boolean isCollidingInPosX(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) 
					&& e.getBounds().x - (e.getBounds().width / 2) < bounds[x][y].getX() 
					&& (e.getBounds().y + e.getBounds().height) >= bounds[x][y].y + 5){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCollidingInNegX(Entity e) {
		for(int x = 0; x < World.getWidth(); x++) {
			for(int y = 0; y < World.getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])
					&& (bounds[x][y].getX() + (bounds[x][y].getWidth() / 2) < e.getBounds().x) 
					&& (e.getBounds().y + e.getBounds().height) >= bounds[x][y].y + 5) { 
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
					&& e.getBounds().y + (e.getBounds().height / 2) < bounds[x][y].getY() - 3) { 
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
					&& bounds[x][y].getY() + (bounds[x][y].getHeight() / 2) < e.getBounds().y + 3 ) { 
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
				//The statements (tiles[(int)(e.getBounds().x / Tile.tileWidth)][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) and 
				// (tiles[(int)(e.getBounds().x / Tile.tileWidth) + 1][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) checks whether the tiles
				// to the left of the player is solid
				// The statement (tiles[(int)((e.getBounds().x + e.getBounds().width) / Tile.tileWidth)][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) checks
				// whether the tiles to the right of the player are solid

				if ((e.getBounds().y + Tile.tileHeight)  >= World.getHeight() * Tile.tileHeight 
					|| e.getBounds().x + Tile.tileWidth >= World.getWidth() * Tile.tileWidth) {
					return true;
				}
				if (e.getBounds().intersects(bounds[x][y]) &&
					(tiles[(int)(e.getBounds().x / Tile.tileWidth)][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) &&
					!(tiles[(int)((e.getBounds().x + e.getBounds().width) / Tile.tileWidth)][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid)) {
						return tiles[x][y + 1].isTurnsGravityOn();
				}
				else if (e.getBounds().intersects(bounds[x][y]) && 
					!(tiles[(int)((e.getBounds().x + e.getBounds().width) / Tile.tileWidth)][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) &&
					(tiles[(int)(e.getBounds().x / Tile.tileWidth) + 1][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) ) {
						return tiles[x + 1][y + 1].isTurnsGravityOn();
				}
				else if(e.getBounds().intersects(bounds[x][y]) && 
						(tiles[(int)((e.getBounds().x + e.getBounds().width) / Tile.tileWidth)][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) &&
						(tiles[(int)(e.getBounds().x / Tile.tileWidth) + 1][(int)(e.getBounds().y / Tile.tileHeight) + 1].solid) ) {
					return false;
				}
			}
		}
		return true;
	} 
}
