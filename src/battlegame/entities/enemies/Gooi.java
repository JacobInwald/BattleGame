package battlegame.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import battlegame.Main;
import battlegame.entities.Bullet;
import battlegame.graphics.Assets;

public class Gooi extends Enemy{

	private int count = 1;
	
	public Gooi(float x, float y) {
		super(x, y, 48, 48, Assets.gooiFront);
		bounds.height = 30;
		bounds.width = 48;
	}

	@Override
	public void tick() {
		platformerMove();
	}

	public void attack() {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int)(x), (int)(y), width, height, null);	
		g.setColor(Color.CYAN);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public void die() {
		active = false;
	}

}
