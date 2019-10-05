package battlegame.entities.enemies;

import battlegame.graphics.Assets;
import battlegame.graphics.AudioLoader;
import javafx.scene.canvas.GraphicsContext;

public class Gooi extends Enemy{

	
	public Gooi(float x, float y) {
		super(x, y, 48, 48, Assets.gooiFront);
		bounds.height = 25;
		bounds.width = 40;
		AudioLoader.loadMusic(Assets.samMusic);
		AudioLoader.playMusic();
	}

	@Override
	public void tick() {
		platformerMove();
	}

	public void attack() {
		
	}
	
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(texture, (int)(x), (int)(y), width, height);	
		//g.setColor(Color.GREEN);
		//g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public void die() {
		AudioLoader.stopMusic();
		active = false;
	}

}
