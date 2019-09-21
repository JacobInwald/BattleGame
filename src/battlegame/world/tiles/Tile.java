package battlegame.world.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import battlegame.graphics.Assets;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile skyTile = new SkyTile(Assets.skyTile, 0);
	public static Tile topDirtTile = new TopDirtTile(Assets.topDirtTile, 1);
	public static Tile bouncyTile = new BouncyTile(Assets.player, 2);
	public static Tile iceTile = new IceTile(Assets.player, 3);
	
	public static int tileHeight = 32, tileWidth = 32;
	
	private BufferedImage texture;
	private int id;
	protected boolean bouncy = false;
	protected boolean solid = false;
	protected boolean turnsGravityOn = true;
	protected float frictionCoefficient = (float) 100;
	public float x, y;
	public Rectangle bounds;
	
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, (int)x, (int)y, null);
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isSolid() {
		return solid;
	}
	
	public boolean isTurnsGravityOn() {
		return turnsGravityOn;
	}
	
}
