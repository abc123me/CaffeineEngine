package net.net16.jeremiahlowe.caffeineengine.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.TickedOff.OnTick;
import net.net16.jeremiahlowe.caffeineengine.camera.Camera;
import net.net16.jeremiahlowe.caffeineengine.geometry.Bounds;

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
	public void renderObjectsOnCamera(Graphics gx, Camera c, boolean enter){
		Bounds camBds = c.bounds.clone();
		for(GameObject g : gameObjects){
			if(g.renderer != null && g.renderer.render){
				if(camBds.inBounds(g.transform.position.x, g.transform.position.y)){
					if(enter) g.renderer.onEnterRender(gx);
					else g.renderer.onExitRender(gx);
				}
				else{
					System.out.println("Not rendering GameObject: " + g.name);
					System.out.println("Because gameobject isn't in bounds");
				}
			}
			else{
				System.out.println("Not rendering GameObject: " + g.name);
				if(g.renderer == null) System.out.println("Becuase renderer is null");
				else if(g.renderer.render) System.out.println("Because rendering is disabled");
				else System.out.println("Reason unknown");
			}
		}
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
