package battlegame.entities.entitymanager;

import java.awt.Graphics;
import java.util.ArrayList;

import battlegame.entities.Bullet;
import battlegame.entities.Entity;

public class EntityManager {
	
	private static ArrayList<Entity> entities;
	private ArrayList<Entity> addEntityList;
	private ArrayList<Entity> removeEntityList;

	public EntityManager() {
		entities = new ArrayList<Entity>();
		addEntityList = new ArrayList<Entity>();
		removeEntityList = new ArrayList<Entity>();
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public void tickEntities() {
		//System.out.println("---------");
		for(Entity e : entities) {
			//System.out.println(e);
			if(!e.isActive()) {
				removeEntityList.add(e);
			}
			e.tick();
		}
		for(Entity e : addEntityList) {
			addEntity(e);
		}
		for(Entity e : removeEntityList) {
			removeEntity(e);
		}
		
	}
	
	public void renderEntities(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}
	
	public static boolean checkEntityCollision(Entity o) {
		for(Entity e : entities) {
			
			if(o.getBounds().intersects(e.getBounds()))
				return true;
		}
		
		return false;
	}

	public ArrayList<Entity> getAddEntityList() {
		return addEntityList;
	}
	
	
	
}

