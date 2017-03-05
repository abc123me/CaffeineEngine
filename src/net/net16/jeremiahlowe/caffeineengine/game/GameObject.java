package net.net16.jeremiahlowe.caffeineengine.game;

import net.net16.jeremiahlowe.caffeineengine.geometry.Transform;

public class GameObject {
	public Transform transform;
	public Renderer renderer;
	public String name;
	private boolean inDestroyQueue = false;
	public GameObject(){
		name = "GameObject";
		renderer = new Renderer(this);
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
}
