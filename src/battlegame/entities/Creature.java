package battlegame.entities;

import java.awt.image.BufferedImage;

import battlegame.world.World;
import battlegame.world.tiles.Tile;
import battlegame.world.tiles.TileManager;

public abstract class Creature extends Entity {

	protected float xSpeed, ySpeed;
	protected float walkingSpeed = 0, jumpSpeed = 0;
	protected float weight;
	protected boolean affectedByFriction = true, isJumping = false;
	protected boolean isBouncing = false;
	protected int numberOfJumps = 3, jumpsDone = 0;
	protected static final float gPerFrame = 0.1633333333333F;
	protected boolean gravityOn = false;
	protected Tile[][] tiles;
	
	public Creature(float x, float y, int width, int height, BufferedImage texture){
		super(x, y, width, height, texture);

	}

	public void move() {
		if(gravityOn) 
			ySpeed += gPerFrame;
		
		jump();
		moveX();
		moveY();
		isJumping = false;
		x += xSpeed;
		y += ySpeed;

		if(!gravityOn)
			ySpeed = 0;
		if(xSpeed <= 0.1 && xSpeed >= -0.1)
			xSpeed = 0;
		if(affectedByFriction) {
			xSpeed = (float) (xSpeed / TileManager.tileFrictionCoefficient(this));
			ySpeed = (float) (ySpeed / (1 + (gPerFrame / 50)));
		}
		bounds.x = (int) x;
		bounds.y = (int) y;
		if(y >= (World.getHeight() + 1)* Tile.tileHeight) 
			die();
		if(x >= (World.getWidth() + 3) * Tile.tileWidth) 
			die();
		if(x <= -(3) * Tile.tileWidth) 
			die();
		
	}

	public void moveX() {
		if(TileManager.isCollidingInPosX(this))
			xSpeed = -xSpeed;
		else if (TileManager.isCollidingInNegX(this))
			xSpeed = -xSpeed;
	}

	public void moveY() {
		if(TileManager.isCollidingWithBouncyTile(this)) {
			gravityOn = true;
			isBouncing = true;
			ySpeed = (float) -((ySpeed) * 0.5);
			jumpsDone = 0;
		}
		else if (TileManager.isCollidingInNegY(this)) {
			ySpeed = walkingSpeed;
			isBouncing = false;
		}
		else if (TileManager.isCollidingInPosY(this)) {
			System.out.println("Youch");
			ySpeed = -walkingSpeed;
			isBouncing = false;
			jumpsDone = 0;
		}
		if(!isBouncing)
			gravityOn = TileManager.returnTileBelow(this);
	}
	
	public void jump() {
		if(jumpsDone <= numberOfJumps && isJumping) {
			ySpeed = jumpSpeed;
			jumpsDone += 1;
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