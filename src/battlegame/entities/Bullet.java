package battlegame.entities;

import java.awt.Graphics;

import battlegame.entities.entitymanager.EntityManager;
import battlegame.graphics.Assets;
import battlegame.world.tiles.TileManager;

public class Bullet extends Creature{

	protected float bulletSpeed = 10F;
	protected int damage = 4;
	protected Entity firer;
	
	public Bullet(float x, float y, Entity firer) {
		super(x, y, 16, 16, Assets.player);
		if(!(firer == null))
			this.firer = firer;
		else
			bulletSpeed = 0;
		affectedByFriction = false;
		gravityOn = false;
	}

	@Override
	public void tick() {
		if(health <= 0)
			die();
		xSpeed = bulletSpeed;
		checkHits();
		move();
	}
	
	public void render(Graphics g) {
		g.fillRect((int) x, (int) y, width, height);
	}

	public void checkHits() {
		if(TileManager.checkTileCollision(this)) {
			die();
			return;
		}
		if(EntityManager.checkEntityCollisionWithBullet(firer, this)) {
			die();
			EntityManager.returnEntityCollisions(this).damage(damage);
		}
	}
	
	public void fire(Entity e) {
		
	}
	
	@Override
	public void die() {
		active = false;
	}

	
	
}
