package battlegame.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import battlegame.Main;
import battlegame.graphics.Assets;
import battlegame.world.World;

public class Player extends Creature{
	
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;	
	public Player(float x, float y){
		super(x, y, 32, 45, Assets.player);
		health = 10;
		walkingSpeed = 3;
		jumpSpeed = -6.0F;
		bounds.x = (int) x;
		bounds.y = (int) (y - bounds.height);
		bounds.height = 38;
		bounds.width = 22;
	}

	@Override
	public void tick() {
		if(health <= 0)
			die();
		getInput();
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int)x, (int)y, width, height, null);
		
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void getInput() {
		if(Main.getGame().getKeyController().down || Main.getGame().getKeyController().downAlt) {
				return;
			}
		if(Main.getGame().getKeyController().keyJustPressed(KeyEvent.VK_W) 
			|| Main.getGame().getKeyController().keyJustPressed(KeyEvent.VK_UP)) {
			jump();
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
			World.getEntityManager().getAddEntityList().add(new Bullet(this.x + this.width + 1, this.y - 1, 1, this));
		else if(lastAttackDirectionPressed == KeyEvent.VK_A)
			World.getEntityManager().getAddEntityList().add(new Bullet(this.x - 1, this.y - 1, -1, this));
		
		attackTimer = 0;
	}
	
	@Override
	public void die() {
		active = false;
		System.out.println("You Died!");
	}

}
