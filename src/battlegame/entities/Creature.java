package battlegame.entities;

import java.awt.image.BufferedImage;

import battlegame.Main;
import battlegame.world.tiles.Tile;

public abstract class Creature extends Entity {

	protected float xSpeed, ySpeed;
	protected float walkingSpeed = 0, jumpSpeed = 0;
	protected float weight;
	public int lastAttackDirectionPressed = 0;
	protected boolean affectedByPhysics = true, isJumping = false;
	protected boolean isBouncing = false, ableToWallSlide = true, ableToWallJump = true;
	protected boolean isWeapon = false;
	protected int numberOfJumps = 2, jumpsDone = 0;
	protected static final float gPerFrame = 0.1633333333333F;
	protected boolean gravityOn = false;
	protected Tile[][] tiles;

			public Creature(float x, float y, int width, int height, BufferedImage
					texture){ super(x, y, width, height, texture);

			}
//These are the functions for when the player is platforming. 
			public void platformerMove() { 
				ableToWallSlide = Main.getGame().getCurrentWorld().getTileManager().ableToWallSlide(this);
				if((collisionWithEntity(0, ySpeed) && ySpeed > 0) && !isWeapon) 
					ySpeed = -(walkingSpeed * 3);
				if(collisionWithEntity(0, ySpeed) && ySpeed < 0 && !isWeapon)
					ySpeed = walkingSpeed;
				if(collisionWithEntity(xSpeed, 0) && xSpeed < 0 && !isWeapon) 
					xSpeed = 0.01f;
				if(collisionWithEntity(xSpeed, 0) && xSpeed > 0 && !isWeapon) 
					xSpeed = -0.01f;
				if(collisionWithEntity(xSpeed, 0) || collisionWithEntity(0, ySpeed))
					gravityOn = false;
				
				if(ableToWallSlide) {
					jumpsDone = 1;
				}
				if(ableToWallSlide && ySpeed < 0 && ableToWallJump) 
					ySpeed += 0.5;  
				if(ableToWallSlide  && ySpeed >= 0 && ableToWallJump)
					ySpeed += 0.05;
				if(gravityOn && !ableToWallSlide && affectedByPhysics) 
					ySpeed  += gPerFrame; 
				
				moveXPlatforming();
				moveYPlatforming();

				
					x += xSpeed;
					y += ySpeed;
				if(isWeapon) {
					x += xSpeed;
					y += ySpeed;
				}
				if(!gravityOn) 
					ySpeed = 0;
				
				if(affectedByPhysics) { 
					xSpeed = (float) (xSpeed / Main.getGame().getCurrentWorld().getTileManager().tileFrictionCoefficient(this));
					//ySpeed = (float) (ySpeed /Main.getGame().getCurrentWorld().getTileManager().tileFrictionCoefficient(this));
					ySpeed = (float) (ySpeed / (1 + gPerFrame / 10)); 
				}
				bounds.x = (int) (x + (width - bounds.width) / 2);
				bounds.y = (int) (y + (height - bounds.height));

				if(y >= (Main.getGame().getCurrentWorld().getHeight() + 1)* Tile.tileHeight)
					die(); 
				if(x >= (Main.getGame().getCurrentWorld().getWidth() + 3) *Tile.tileWidth)
					die(); 
				if(x <= -(3) * Tile.tileWidth)
					die();
				if(health <= 0)
					die();

			}

			public void moveXPlatforming() {
				if(Main.getGame().getCurrentWorld().getTileManager().isCollidingInPosX(this)) { 
					if (xSpeed > 0)
						xSpeed = 0;
				} 
				if (Main.getGame().getCurrentWorld().getTileManager().isCollidingInNegX(this)){
					if (xSpeed < 0) 
						xSpeed = 0;
				}

			}
			
			public void moveYPlatforming() {
				if(Main.getGame().getCurrentWorld().getTileManager().isCollidingWithBouncyTile(this)) {
					gravityOn = true;
					isBouncing = true;
					ySpeed = (float) -(ySpeed * 1.75);
					jumpsDone = 0;
					return;
				}
				if (Main.getGame().getCurrentWorld().getTileManager().isCollidingInNegY(this)
						&& !Main.getGame().getCurrentWorld().getTileManager().isCollidingInPosY(this)) { 
					if(ableToWallSlide) 
						return;
					ySpeed = walkingSpeed / 2;
					isBouncing = false;
				} 
				if (Main.getGame().getCurrentWorld().getTileManager().isCollidingInPosY(this)
						&& !Main.getGame().getCurrentWorld().getTileManager().isCollidingInNegY(this)) { 
					if(ableToWallSlide) 
						return;
					ySpeed  = -walkingSpeed / 2;
					isBouncing = false;
					jumpsDone = 0; 
				}
 

				if(!isBouncing && !ableToWallSlide) 
					gravityOn = Main.getGame().getCurrentWorld().getTileManager().returnTileBelowTurnsGravityOn(this); 
				else { 
					gravityOn = true; 
				} 
			}

			public void jumpPlatforming() { 
				if(jumpsDone < numberOfJumps) {
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
 