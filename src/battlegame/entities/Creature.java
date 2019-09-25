// package battlegame.entities;
// 
// import java.awt.image.BufferedImage;
// 
// import battlegame.world.World;
// import battlegame.world.tiles.Tile;
// import battlegame.world.tiles.TileManager;
// 
// public abstract class Creature extends Entity {
// 
// 	protected float xSpeed, ySpeed;
// 	protected float walkingSpeed = 0, jumpSpeed = 0;
// 	protected float weight;
// 	protected boolean affectedByFriction = true, isJumping = false;
// 	protected boolean isBouncing = false, ableToMoveX = true;
// 	protected int numberOfJumps = 1, jumpsDone = 0;
// 	protected static final float gPerFrame = 0.1633333333333F;
// 	protected boolean gravityOn = false;
// 	protected Tile[][] tiles;
// 	
// 	public Creature(float x, float y, int width, int height, BufferedImage texture){
// 		super(x, y, width, height, texture);
// 
// 	}
// 
// 	public void move() {
// 		moveX();
// 		moveY();
// 		if(gravityOn) 
// 			ySpeed += gPerFrame;
// 		if(!ableToMoveX)
// 			ySpeed += 0.1;
// 		ableToMoveX = !TileManager.ableToWallSlide(this);
// 		x += xSpeed;
// 		y += ySpeed;
// 
// 		if(!gravityOn)
// 			ySpeed = 0;
// 		if(affectedByFriction) {
// 			xSpeed = (float) (xSpeed / TileManager.tileFrictionCoefficient(this));
// 			 ySpeed = (float) (ySpeed / TileManager.tileWallCoefficient(this));
// 			ySpeed = (float) (ySpeed / (1 + gPerFrame / 10));
// 		}
// 		
// 		bounds.x = (int) x;
// 		bounds.y  = (int) (y + (height - bounds.height));
// 		
// 		if(y >= (World.getHeight() + 1)* Tile.tileHeight) 
// 			die();
// 		if(x >= (World.getWidth() + 3) * Tile.tileWidth) 
// 			die();
// 		if(x <= -(3) * Tile.tileWidth) 
// 			die();
// 		
// 		if(xSpeed <= (walkingSpeed / 2) && xSpeed >= -(walkingSpeed / 2))
// 			ableToMoveX = true;
// 	}
// 
// 	public void moveX() {
// 		
// 		if(TileManager.isCollidingInPosX(this)) {
// 			 xSpeed = -walkingSpeed;
// 			if(xSpeed >= 0)
// 				xSpeed = 0;
// 			ableToMoveX = false;
// 		}
// 		else if (TileManager.isCollidingInNegX(this)) {
// 			 xSpeed = walkingSpeed;
// 			if(xSpeed <= 0)
// 				xSpeed = 0;
// 			ableToMoveX = false;
// 		}
// 	}
// 
// 	public void moveY() {
// 
// 		if (TileManager.isCollidingInPosY(this)) {
// 			ySpeed = -walkingSpeed;
// 			isBouncing = false;
// 		}	
// 		if (TileManager.isCollidingInNegY(this)) { 
// 			ySpeed = walkingSpeed;
// 			isBouncing = false;
// 		}	
// 		else if(TileManager.isCollidingWithBouncyTile(this)) {
// 			gravityOn = true;
// 			isBouncing = true;
// 			ySpeed = (float) (jumpSpeed * 1.5);
// 			jumpsDone = 0;
// 		}
// 		if(ySpeed == 0){
// 			jumpsDone = 0;
// 		}
// 		
// 		if(!isBouncing)
// 			gravityOn = TileManager.returnTileBelow(this);
// 		else {
// 			gravityOn = true;
// 		}
// 	}
// 	
// 	public void jump() {
// 		if(jumpsDone <= numberOfJumps) {
// 			isBouncing = true;
// 			ySpeed = jumpSpeed;
// 			jumpsDone += 1;
// 		}
// 	}
// 	
// 	public float getxSpeed() {
// 		return xSpeed;
// 	}
// 
// 	public float getySpeed() {
// 		return ySpeed;
// 	}
// 
// 	public int getHealth() {
// 		return health;
// 	}
// 
// }



package battlegame.entities;

import java.awt.image.BufferedImage;

import battlegame.world.World; import battlegame.world.tiles.Tile; import
battlegame.world.tiles.TileManager;

public abstract class Creature extends Entity {

	protected float xSpeed, ySpeed;
	protected float walkingSpeed = 0, jumpSpeed = 0;
	protected float weight;
	public int lastAttackDirectionPressed = 0;
	protected boolean affectedByPhysics = true, isJumping = false;
	protected boolean isBouncing = false, ableToWallSlide = true;
	protected int numberOfJumps = 3, jumpsDone = 0;
	protected static final float gPerFrame = 0.1633333333333F;
	protected boolean gravityOn = false;
	protected Tile[][] tiles;

			public Creature(float x, float y, int width, int height, BufferedImage
					texture){ super(x, y, width, height, texture);

			}

			public void move() { 
				ableToWallSlide = TileManager.ableToWallSlide(this);  
				moveX();
				moveY(); 
				if(ableToWallSlide && ySpeed <= 0)
					ySpeed += 0.1;
				if(ableToWallSlide) 
					ySpeed += 0.05; 
				if(gravityOn && !ableToWallSlide && affectedByPhysics) 
					ySpeed  += gPerFrame; 

				x += xSpeed;
				y += ySpeed;

				if(!gravityOn) 
					ySpeed = 0;
				if(affectedByPhysics) { 
					xSpeed = (float) (xSpeed / TileManager.tileFrictionCoefficient(this));
					//ySpeed = (float) (ySpeed /TileManager.tileFrictionCoefficient(this));
					ySpeed = (float) (ySpeed / (1 + gPerFrame / 10)); 
				}
				bounds.x = (int) x;
				bounds.y = (int) (y + (height - bounds.height));

				if(y >= (World.getHeight() + 1)* Tile.tileHeight)
					die(); 
				if(x >= (World.getWidth() + 3) *Tile.tileWidth)
					die(); 
				if(x <= -(3) * Tile.tileWidth)
					die();

			}

			public void moveX() {
				if(TileManager.isCollidingInPosX(this)) { 
					if (xSpeed > 0)
						xSpeed = 0;
					jumpsDone = 1;
				} 
				else if (TileManager.isCollidingInNegX(this)){
					if (xSpeed < 0) 
						xSpeed = 0;
					jumpsDone = 1;
				}

			}

			public void moveY() {
				if (TileManager.isCollidingInPosY(this) && !ableToWallSlide) { 
					ySpeed  = -walkingSpeed;
					isBouncing = false;
					jumpsDone = 0; 
				}
				if (TileManager.isCollidingInNegY(this)) { 
					/*if(TileManager.isCollidingInNegX(this) && xSpeed == 0) {
						return;
					}
					else if(TileManager.isCollidingInPosX(this) && xSpeed == 0)
						return;*/
					if(ableToWallSlide && xSpeed == 0) {
						ySpeed += 0.1;
						xSpeed = 0;
						return;
					}
					ySpeed = walkingSpeed; 
					isBouncing = false; 
				} 
				else if(TileManager.isCollidingWithBouncyTile(this)) {
					gravityOn = true;
					isBouncing = true;
					ySpeed = (float) (jumpSpeed * 1.5);
					jumpsDone = 0;
				} 

				if(!isBouncing && !ableToWallSlide) 
					gravityOn = TileManager.returnTileBelowTurnsGravityOn(this); 
				else { 
					gravityOn = true; 
				} 
			}

			public void jump() { 
				if(jumpsDone <= numberOfJumps) {
					isBouncing = true;
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
 