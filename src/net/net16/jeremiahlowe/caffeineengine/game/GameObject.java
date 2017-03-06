package net.net16.jeremiahlowe.caffeineengine.game;

import net.net16.jeremiahlowe.caffeineengine.geometry.Bounds;
import net.net16.jeremiahlowe.caffeineengine.geometry.Transform;
import net.net16.jeremiahlowe.caffeineengine.graphics.Renderer;

public class GameObject{
	public Transform transform;
	public Renderer renderer;
	public String name;
	private boolean inDestroyQueue = false;
	public GameObject(){
		name = "GameObject";
		transform = new Transform();
	}
	public void destroy(){
		inDestroyQueue = true;
	}
	public void cancelDestroy(){
		inDestroyQueue = false;
	}
	public boolean inDestroyQueue(){
		return inDestroyQueue;
	}
	public Bounds getBounds(){
		return new Bounds(transform.position, transform.size);
	}
}
