package net.net16.jeremiahlowe.caffeineengine.game;

import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.TickedOff.OnTick;

public class World implements OnTick{
	private List<GameObject> gameObjects;
	private List<GameObject> gameObjectsToRemove;
	public World(){
		gameObjects = new ArrayList<GameObject>();
		gameObjectsToRemove = new ArrayList<GameObject>();
	}
	public void addGameObject(GameObject g){
		gameObjects.add(g);
	}
	public GameObject findGameObjectByName(String name){
		for(GameObject g : gameObjects) if(name == g.name || name.matches(g.name)) return g;
		return null;
	}
	@Override
	public void onTick() {
		for(GameObject g : gameObjects) if(g.inDestroyQueue()) gameObjectsToRemove.add(g);
		gameObjectsToRemove.clear();
	}
}
