package battlegame.entities;

import java.awt.Rectangle;

import battlegame.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
	
	protected float x , y;
	protected int width, height;
	protected Image texture;
	protected int health = 10;
	protected Rectangle bounds;
	protected boolean active = true;
	
	public Entity(float x, float y, int width, int height, Image texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		
		bounds = new Rectangle((int)x, (int)y, width, height);
	}

	public abstract void tick();
	
	public abstract void render(GraphicsContext g);
	
	public abstract void die();
	
	public boolean collisionWithEntity(float xOffset, float yOffset) {
		for(Entity e : Game.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getBounds().intersects(new Rectangle((int)(bounds.x + xOffset), (int)(bounds.y + yOffset), bounds.width, bounds.height)))
				return true;
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isActive() {
		return active;
	}

	public void damage(int health) {
		this.health -= health;
	}
	
	
	
	
}
