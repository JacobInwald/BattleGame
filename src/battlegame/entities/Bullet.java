package battlegame.entities;

import java.awt.Graphics;

import battlegame.graphics.Assets;
import battlegame.world.tiles.TileManager;

public class Bullet extends Creature{

	protected float bulletSpeed = 1F;
	
	public Bullet(float x, float y) {
		super(x, y, 16, 16, Assets.player);
		affectedByFriction = false;
		gravityOn = false;
	}

	@Override
	public void tick() {
		xSpeed = bulletSpeed;
		checkHits();
		move();
	}
	
	public void render(Graphics g) {
		g.fillRect((int) x, (int) y, width, height);
	}

	public void checkHits() {
		if(TileManager.checkTileCollision(this)) {
			active = false;
			return;
		}
	}
	
	public void fire(Entity e) {
		
	}
	
	@Override
	public void die() {
		active = false;
	}

	
	
}
