package battlegame.entities.players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import battlegame.Main;
import battlegame.entities.Bullet;
import battlegame.entities.Creature;
import battlegame.graphics.Assets;

public class PlatformingPlayer extends Creature{
	
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;	
	public PlatformingPlayer(float x, float y){
		super(x, y, 48, 68, Assets.player);
		health = 10;
		walkingSpeed = 2.75F;
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
	public void render(Graphics g) {
		g.drawImage(texture, (int)x, (int)y, width, height, null);
		g.setColor(Color.BLUE);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void getInput() {
		if(Main.getGame().getKeyController().down || Main.getGame().getKeyController().downAlt) {
				return;
			}
		if(Main.getGame().getKeyController().keyJustPressed(KeyEvent.VK_W) 
			|| Main.getGame().getKeyController().keyJustPressed(KeyEvent.VK_UP)) {
			jumpPlatforming();
		}
		if(Main.getGame().getKeyController().right || Main.getGame().getKeyController().rightAlt) {
			lastAttackDirectionPressed = KeyEvent.VK_D;
			xSpeed = walkingSpeed;
		}
		if(Main.getGame().getKeyController().left || Main.getGame().getKeyController().leftAlt) {
			lastAttackDirectionPressed = KeyEvent.VK_A;
			xSpeed = -walkingSpeed;
		}
		if(Main.getGame().getKeyController().o || Main.getGame().getKeyController().z) {
			attack();
		}
	}
	
	public void attack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  attackCooldown)
			return;
		if(lastAttackDirectionPressed == KeyEvent.VK_D)
			Main.getGame().getCurrentWorld().getEntityManager().getAddEntityList().add(new Bullet(this.x + this.width + 1, this.y + (height / 2), 1, this));
		else if(lastAttackDirectionPressed == KeyEvent.VK_A)
			Main.getGame().getCurrentWorld().getEntityManager().getAddEntityList().add(new Bullet(this.x - (this.width / 2), this.y + (height / 2), -1, this));
		
		attackTimer = 0;
	}
	
	@Override
	public void die() {
		active = false;
		System.out.println("You Died!");
	}

}
