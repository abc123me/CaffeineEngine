package net.net16.jeremiahlowe.caffeineengine.base;

import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class GameObject {
	private static List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public Transform transform = new Transform();
	private List<GameComponent> components = new ArrayList<GameComponent>();
	private boolean registered = false;
	
	public GameObject(){this(true);}
	public GameObject(boolean register){
		if(register) register();
	}
	
	public void addComponent(GameComponent g){
		if(!components.contains(g)) components.add(g);
	}
	public List<GameComponent> getComponents(){
		return components;
	}
	public void unRegister(){
		if(registered){
			registered = false;
			gameObjects.remove(this);
		}
	}
	public void register(){
		if(!registered){
			registered = true;
			gameObjects.add(this);
		}
	}	
	public Vector2 getPosition(){return transform.position;}
	public Rotation getRotation(){return transform.rotation;}
	public Vector2 getPositionClone(){return transform.position.clone();}
	public Rotation getRotationClone(){return transform.rotation.clone();}
	
	public static List<GameObject> getGameObjects(){return gameObjects;}
}
