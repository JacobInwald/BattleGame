package battlegame.entities;

import battlegame.Game;
import battlegame.graphics.Assets;
import battlegame.world.tiles.Tile;
import javafx.scene.canvas.GraphicsContext;

public class Bullet extends Creature{

	protected float bulletSpeed = 10F;
	protected int damage = 2;
	protected float startingX = 0, distanceAbleToTravel = 5 * Tile.tileWidth;
	protected Entity firer;
	
	public Bullet(float x, float y, int direction, Entity firer) {
		super(x, y, 16, 16, Assets.topDirtTile);
		if(!(firer == null))
			this.firer = firer;
		else
			bulletSpeed = 0;
		if(direction < 0)
			this.bulletSpeed = -bulletSpeed;
		if(direction == 0)
			this.bulletSpeed = 0;
		startingX = x;
		affectedByPhysics = false;
		isWeapon = true;
		xSpeed = bulletSpeed;
	}

	@Override
	public void tick() {
		checkDistanceTravelled();
		checkHits();
		platformerMove();
	}
	
	public void render(GraphicsContext g) {
		g.drawImage(texture, (int) x, (int) y, width, height);

	}

	public void checkHits() {
		if(Game.getCurrentWorld().getTileManager().checkTileCollision(this)) {
			die();
			return;
		}
		for(Entity e : Game.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(firer) || e.equals(this)) {
				continue;
			}
			if(e.getBounds().intersects(this.getBounds())) {
				e.damage(damage);
				die();
				return;
			}
		}
	}
	
	public void checkDistanceTravelled() {
		float distance = x - startingX;
		
		if(distance < -distanceAbleToTravel || distance > distanceAbleToTravel)
			die();
		
	}
	
	public void fire(Entity e) {
		
	}
	
	@Override
	public void die() {
		active = false;
	}

	
	
}
