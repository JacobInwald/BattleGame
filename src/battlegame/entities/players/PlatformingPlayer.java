package battlegame.entities.players;

import java.awt.event.KeyEvent;

import battlegame.Game;
import battlegame.entities.Bullet;
import battlegame.entities.Creature;
import battlegame.graphics.Assets;
import battlegame.graphics.AudioLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlatformingPlayer extends Creature{
	
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;	
	public PlatformingPlayer(float x, float y){
		super(x, y, 48, 68, Assets.playerStandingForward);
		health = 10;
		walkingSpeed = 3.0F;
		jumpSpeed = -6.0F;
		bounds.x = (int) x;
		bounds.y = (int) (y - bounds.height);
		bounds.height = 51;
		bounds.width = 30;
	}

	@Override
	public void tick() {
		if(health <= 0)
			die();
		getInput();
		platformerMove();
	}

	@Override
	public void render(GraphicsContext g) {
		if(lastAttackDirectionPressed == KeyEvent.VK_D) 
			g.drawImage(Assets.playerStandingRight, (int)x, (int)y, width, height);
		else if(lastAttackDirectionPressed == KeyEvent.VK_A) 
			g.drawImage(Assets.playerStandingLeft, (int)x, (int)y, width, height);
		else {
			g.drawImage(texture, (int)x, (int)y, width, height);
		}
		//g.setFill(Color.RED);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void getInput() {
		if(Game.getKeyController().down) {
				lastAttackDirectionPressed = KeyEvent.VK_S;
				bounds.width = 30;
				return;
			}
		if(Game.getKeyController().up) {
			jumpPlatforming();
		}
		if(Game.getKeyController().right) {
			lastAttackDirectionPressed = KeyEvent.VK_D;
			bounds.width = 22;
			xSpeed = walkingSpeed;
		}
		if(Game.getKeyController().left) {
			lastAttackDirectionPressed = KeyEvent.VK_A;
			bounds.width = 22;
			xSpeed = -walkingSpeed;
		}
		if(Game.getKeyController().select) {
			attack();
		}
	}
	
	public void attack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  attackCooldown)
			return;
		this.platformerMove();
		if(lastAttackDirectionPressed == KeyEvent.VK_D)
			Game.getCurrentWorld().getEntityManager().getAddEntityList().add(new Bullet(this.x + this.width + 1, this.y + (height / 2), 1, this));
		else if(lastAttackDirectionPressed == KeyEvent.VK_A)
			Game.getCurrentWorld().getEntityManager().getAddEntityList().add(new Bullet(this.x - (this.width / 2), this.y + (height / 2), -1, this));
		
		attackTimer = 0;
	}
	
	@Override
	public void die() {
		AudioLoader.loadMusic(Assets.testSong);
		AudioLoader.playMusic();
		active = false;
		System.out.println("You Died!");
		x = 300;
		y = 200;
		active = true;
	}

}
