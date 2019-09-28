package battlegame.entities.entitymanager;

import java.awt.Graphics;
import java.util.ArrayList;

import battlegame.entities.Entity;

public class EntityManager {
	
	private  ArrayList<Entity> entities;
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
	
	public boolean checkEntityCollision(Entity o) {
		for(Entity e : entities) {
			
			if(e.equals(o))
				continue;
			
			if(o.getBounds().intersects(e.getBounds()))
				return true;
		}
		
		return false;
	}
	
	public boolean checkEntityCollisionWithBullet(Entity firer, Entity o) {
		for(Entity e : entities) {
			
			if(e.equals(firer) || e.equals(o)) 
				return false;
			
			if(o.getBounds().intersects(e.getBounds()))
				return true;
		}
		
		return false;
	}
	
	public Entity returnEntityCollisions(Entity o) {
		for(Entity e : entities) {
			
			if(e.equals(o))
				continue;
			
			if(o.getBounds().intersects(e.getBounds()))
				return e;
		} 
		
		return null;
	}
	
	public ArrayList<Entity> getAddEntityList() {
		return addEntityList;
	}

	public  ArrayList<Entity> getEntities() {
		return entities;
	}
	
}

