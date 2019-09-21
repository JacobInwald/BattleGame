package battlegame.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import battlegame.Main;
import battlegame.graphics.Assets;
import battlegame.world.World;

public class Player extends Creature{
	
	private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;	
	public Player(float x, float y){
		super(x, y, 32, 32, Assets.player);
		health = 10;
		walkingSpeed = 180 / 60;
		jumpSpeed = 5.0F;
	}

	@Override
	public void tick() {
		if(health <= 0)
			die();
		getInput();
		attack();
		move();
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(texture, (int)x, (int)y, width, height, null);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void getInput() {
		if(Main.getGame().getKeyController().down) {
				return;
			}
		if(Main.getGame().getKeyController().up) {
			ySpeed = -jumpSpeed;
			gravityOn = true;
		}
		if(Main.getGame().getKeyController().right) {
			xSpeed = walkingSpeed;
		}
		if(Main.getGame().getKeyController().left) {
			xSpeed = -walkingSpeed;
		}
	}
	
	public void attack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer <  attackCooldown)
			return;
		
		if(Main.getGame().getKeyController().o) {
			World.getEntityManager().getAddEntityList().add(new Bullet(this.x + this.width + 1, this.y - 1, this));
		}
		
		attackTimer = 0;
	}
	
	@Override
	public void die() {
		active = false;
		System.out.println("You Died!");
	}

}
