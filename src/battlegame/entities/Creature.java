package battlegame.entities;

import java.awt.image.BufferedImage;

import battlegame.world.tiles.Tile;
import battlegame.world.tiles.TileManager;

public abstract class Creature extends Entity {

	protected float xSpeed, ySpeed;
	protected float walkingSpeed = 0, jumpSpeed = 0;
	protected float weight;
	protected int health = 10;
	protected boolean affectedByFriction = true;	protected static final float gPerFrame = 0.1633333333333F;
	protected boolean gravityOn = false;
	protected Tile[][] tiles;
	
	public Creature(float x, float y, int width, int height, BufferedImage texture){
		super(x, y, width, height, texture);

	}

	public void move() {
		if(gravityOn)
			ySpeed += gPerFrame;
		moveX();
		moveY();
		
		x += xSpeed;
		y += ySpeed;

		if(!gravityOn)
			ySpeed = 0;
		if(xSpeed <= 0.1 && xSpeed >= -0.1)
			xSpeed = 0;
		if(affectedByFriction) {
			xSpeed = (float) (xSpeed / TileManager.tileFrictionCoefficient(this));
			ySpeed = (float) (ySpeed / (1 + (gPerFrame / 10)));
		}
		bounds.x = (int) x;
		bounds.y = (int) y;
		
	}

	public void moveX() {
		if(TileManager.isCollidingInPosX(this))
			xSpeed = -xSpeed;
		else if (TileManager.isCollidingInNegX(this))
			xSpeed = -xSpeed;
	}

	public void moveY() {
		if(TileManager.isCollidingWithBouncyTile(this)) {
			if(ySpeed <= 0.1) {
				ySpeed = 0;
				gravityOn = false;
			}
			ySpeed = (float) -((ySpeed) * 1.1); 
		}
		else if (TileManager.isCollidingInNegY(this)) {
			ySpeed = -ySpeed;
		}
		else if (TileManager.isCollidingInPosY(this)) {
			ySpeed = -0.6F;
			//gravityOn = false;
		}
	}
	
	public float getxSpeed() {
		return xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public int getHealth() {
		return health;
	}
	
	
	

}