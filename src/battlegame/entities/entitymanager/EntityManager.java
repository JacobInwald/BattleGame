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
		addEntityList.clear();
		removeEntityList.clear();
		for(Entity e : entities) {
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
	
	public static boolean checkEntityCollisionWithBullet(Entity firer, Entity o) {
		for(Entity e : entities) {
			
			if(e.equals(firer)) 
				return false;
			
			if(o.getBounds().intersects(e.getBounds()))
				return true;
		}
		
		return false;
	}

	public static Entity returnEntityCollisions(Entity o) {
		for(Entity e : entities) {
			if(o.getBounds().intersects(e.getBounds()))
				return e;
		} 
		
		return null;
	}
	
	public ArrayList<Entity> getAddEntityList() {
		return addEntityList;
	}
	
	
	
}

