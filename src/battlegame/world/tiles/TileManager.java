package battlegame.world.tiles;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import battlegame.Game;
import battlegame.entities.Creature;
import battlegame.entities.Entity;

public class TileManager {
	
	private Rectangle[][] bounds;
	private Tile[][] tiles;
	
	public TileManager() {
		
	}
	
	public void init() {
		bounds = new Rectangle[Game.getCurrentWorld().getWidth()][Game.getCurrentWorld().getHeight()];
		tiles = new Tile[Game.getCurrentWorld().getWidth()][Game.getCurrentWorld().getHeight()];	
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x ++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				tiles[x][y] = Game.getCurrentWorld().getTile(x, y);
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
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				bounds[x][y] = new Rectangle((x * Tile.tileWidth), (y * Tile.tileHeight), Tile.tileWidth, Tile.tileHeight);

			}
		}
	}
	
	public boolean checkTileCollision(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*public float tileWallCoefficient(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if((x + 1) > Game.getCurrentWorld().getHeight() || (x - 1) < 0)
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
	
	public boolean isCollidingInPosX(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y]) 
					&& e.getBounds().x - (e.getBounds().width / 2) < bounds[x][y].getX() 
					&& ((e.getBounds().y + e.getBounds().height) >= bounds[x][y].y + 7.5
					|| e.getBounds().y >= bounds[x][y].y + bounds[x][y].height - 7.5)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCollidingInNegX(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])
					&& (bounds[x][y].getX() + (bounds[x][y].getWidth() / 2) < e.getBounds().x) 
					&& ((e.getBounds().y + e.getBounds().height) >= bounds[x][y].y + 7.5
					|| e.getBounds().y >= bounds[x][y].y + bounds[x][y].height - 7.5)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCollidingInPosY(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])
					&& e.getBounds().y + e.getBounds().height < bounds[x][y].getY() + 7.5) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCollidingInNegY(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if (tiles[x][y].isSolid() && e.getBounds().intersects(bounds[x][y])
					&& bounds[x][y].getY() + bounds[x][y].getHeight() - 7.5 < e.getBounds().y) { 
					return true;
				}
			}
		}
		return false;
		
	}
	
	
	public boolean isCollidingWithBouncyTile(Entity e) {
		for(int x = 0; x < Game.getCurrentWorld().getWidth(); x++) {
			for(int y = 0; y < Game.getCurrentWorld().getHeight(); y++) {
				if (e.getBounds().intersects(bounds[x][y]) && tiles[x][y].bouncy && isCollidingInPosY(e)) { 
					return true;
				}
			}
		}
		return false;
	}	
	
	public float tileFrictionCoefficient(Entity e) {
		if((int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth) >= Game.getCurrentWorld().getWidth()|| 
				(int)(e.getBounds().getX() / Tile.tileWidth) <= 0 || 
				(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight) >= Game.getCurrentWorld().getHeight() ||
				(int) ((e.getBounds().y) / Tile.tileHeight) <= 0) 
			return Tile.skyTile.frictionCoefficient;
		
		if ((tiles[(int) (e.getBounds().x / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)
				&& !(tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)) {
			return tiles[(int)(e.getBounds().getX()/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].frictionCoefficient;
		} 
		else if (!(tiles[(int) ((e.getBounds().x) / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)
				&& (tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)) {
			return tiles[(int) ((e.getBounds().x + e.getBounds().width) / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].frictionCoefficient;
		} 
		else if ((tiles[(int) ((e.getBounds().x) / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)
				&& (tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)) {
			return tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].frictionCoefficient;
		}
		return Tile.skyTile.frictionCoefficient;
	} 
	
	public boolean ableToWallSlide(Entity e) {
		// This stops there being an array out of bounds exception when using the 2d array
		if((int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth) >= Game.getCurrentWorld().getWidth()|| 
			(int)(e.getBounds().getX() / Tile.tileWidth) < 0 || 
			(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight) + 1 >= Game.getCurrentWorld().getHeight() ||
			(int) ((e.getBounds().y) / Tile.tileHeight) < 0 ||
			(int) (e.getBounds().y + (e.getBounds().height) / Tile.tileHeight) - 1 < 0 )
			return false;

		
		// This checks the tiles to the left, underneath and on the entity to check whether 
		// they are solid, not solid and not solid respectively. This is to ensure that the
		// entity is actually allowed to wall slide
		if (tiles[(int)(e.getBounds().getX() / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].isSolid() 
				&& !tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].isSolid()
				&& !tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight) - 1].isSolid()
				&& isCollidingInNegX(e)) {
			((Creature) (e)).lastAttackDirectionPressed = KeyEvent.VK_D;
			return true;
		}
		// This checks the tiles to the right, underneath and on the entity to check whether 
		// they are solid, not solid and not solid respectively. This is to ensure that the
		// entity is actually allowed to wall slide
		if (tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].isSolid() 
				&& !tiles[(int)(e.getBounds().getX() / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].isSolid()
				&& !tiles[(int)(e.getBounds().getX() / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight) - 1].isSolid()
				&& isCollidingInPosX(e)) {
			((Creature) (e)).lastAttackDirectionPressed = KeyEvent.VK_A;
			return true;
		}

		return false;
	}

	public boolean returnTileBelowTurnsGravityOn(Entity e) {
		//This first if statement makes sure that an array out of bounds exeption is not called while running this method.

		if((int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth) >= Game.getCurrentWorld().getWidth()|| 
				(int)(e.getBounds().getX() / Tile.tileWidth) < 0 || 
				(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight) >= Game.getCurrentWorld().getHeight() ||
				(int) ((e.getBounds().y) / Tile.tileHeight) < 0) 
			return true;
		// This statement checks if an entity is to the left of a ledge and if so, returns the gravityOn value of the tile that is to the left of the ledge
		if ((tiles[(int) (e.getBounds().x / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid) 
			&& !(tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)) {
			return tiles[(int) (e.getBounds().x / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].turnsGravityOn;
		}
		// This statement checks if an entity is to the right of a ledge and if so, returns the gravityOn value of the tile that is to the right of the ledge
		else if (!(tiles[(int) ((e.getBounds().x) / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)
				&& (tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)) {
			return tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].turnsGravityOn;
		} 
		// This statement checks whether the entity is on solid ground and if it is turns gravity off. 
		else if ((tiles[(int) ((e.getBounds().x) / Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)
				&& (tiles[(int)((e.getBounds().getX() + e.getBounds().width)/ Tile.tileWidth)][(int) ((e.getBounds().y + (e.getBounds().height)) / Tile.tileHeight)].solid)) {
			return false;
		}
		// If none of these statements are correct it will return that gravity is on. 
		return true;
	}
}
