package battlegame.entities;

import java.awt.event.KeyEvent;

import battlegame.Game;
import battlegame.world.tiles.Tile;
import javafx.scene.image.Image;

public abstract class Creature extends Entity {

	protected float xSpeed, ySpeed;
	protected float walkingSpeed = 2, jumpSpeed = -6;
	protected float weight;
	public int lastAttackDirectionPressed = 0;
	protected boolean affectedByPhysics = true, isJumping = false;
	protected boolean isBouncing = false, ableToWallSlide = true, ableToWallJump = true;
	protected boolean isWeapon = false;
	protected int numberOfJumps = 1, jumpsDone = 0,  justJumped = 0;
	protected static final float gPerFrame = 0.16333333333333333333F;
	protected boolean gravityOn = false;
	protected Tile[][] tiles;

	public Creature(float x, float y, int width, int height, Image texture) {
		super(x, y, width, height, texture);

	}
//These are the functions for when the player is platforming. 
			public void platformerMove() { 
				ableToWallSlide = Game.getCurrentWorld().getTileManager().ableToWallSlide(this);
				if(ableToWallSlide && justJumped < 2)
					jumpsDone = 0;
				if(ableToWallSlide && ySpeed < 0 && ableToWallJump) 
					ySpeed += 0.5;  
				if(ableToWallSlide  && ySpeed >= 0 && ableToWallJump)
					ySpeed += 0.05;
				if(gravityOn && !ableToWallSlide && affectedByPhysics) 
					ySpeed  += gPerFrame; 

				checkCreatureCollisionsPlatforming();
				moveXPlatforming();
				moveYPlatforming();
				
				x += xSpeed;
				y += ySpeed;
				
				if(!gravityOn) 
					ySpeed = 0;
				
				if(affectedByPhysics) { 
					xSpeed = (float) (xSpeed / Game.getCurrentWorld().getTileManager().tileFrictionCoefficient(this));
					ySpeed = (float) (ySpeed / (1 + gPerFrame / 10)); 
				}
				bounds.x = (int) (x + (width - bounds.width) / 2);
				bounds.y = (int) (y + (height - bounds.height));

				if(y >= (Game.getCurrentWorld().getHeight() + 1)* Tile.tileHeight)
					die(); 
				if(x >= (Game.getCurrentWorld().getWidth() + 3) *Tile.tileWidth)
					die(); 
				if(x <= -(3) * Tile.tileWidth)
					die();
				if(health <= 0)
					die();

			}

			public void moveXPlatforming() {
				if(Game.getCurrentWorld().getTileManager().isCollidingInPosX(this)) { 
					if (xSpeed > 0)
						xSpeed = 0;
					lastAttackDirectionPressed = KeyEvent.VK_A;
				} 
				if (Game.getCurrentWorld().getTileManager().isCollidingInNegX(this)){
					if (xSpeed < 0) 
						xSpeed = 0;
					lastAttackDirectionPressed = KeyEvent.VK_D;
				}

			}
			
			public void moveYPlatforming() {
				if(Game.getCurrentWorld().getTileManager().isCollidingWithBouncyTile(this)) {
					gravityOn = true;
					isBouncing = true;
					ySpeed = jumpSpeed * 2;
					jumpsDone = 1000;
					justJumped = 1;
					return;
				}
				if (Game.getCurrentWorld().getTileManager().isCollidingInNegY(this)
						&& !Game.getCurrentWorld().getTileManager().isCollidingInPosY(this)
						&& !ableToWallSlide) { 
					ySpeed = 1;
					isBouncing = false;
				} 
				if (Game.getCurrentWorld().getTileManager().isCollidingInPosY(this)
						&& !Game.getCurrentWorld().getTileManager().isCollidingInNegY(this)
						&& !ableToWallSlide) { 
					ySpeed  = -2;
					isBouncing = false;
					jumpsDone = 0;
					justJumped = 0;
				}
 

				if(!isBouncing && !ableToWallSlide) 
					gravityOn = Game.getCurrentWorld().getTileManager().returnTileBelowTurnsGravityOn(this); 
				else { 
					gravityOn = true; 
				} 
			}
			
			public void checkCreatureCollisionsPlatforming()  {
				if((collisionWithEntity(0, ySpeed) && ySpeed > 0) && !isWeapon) 
					ySpeed = (float) (jumpSpeed);
				if(collisionWithEntity(0, ySpeed) && ySpeed < 0 && !isWeapon)
					ySpeed = 0.01f;
				if(collisionWithEntity(xSpeed, 0) && xSpeed < 0 && !isWeapon) 
					xSpeed = 0.01f;
				if(collisionWithEntity(xSpeed, 0) && xSpeed > 0 && !isWeapon) 
					xSpeed = -0.01f;
				if(collisionWithEntity(xSpeed, 0) || collisionWithEntity(0, ySpeed))
					gravityOn = false;
			}
			
			public void jumpPlatforming() { 
				if(jumpsDone < numberOfJumps) {
					isBouncing = true;
					ySpeed = jumpSpeed;
					jumpsDone ++; 
					justJumped++;
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