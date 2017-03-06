package net.net16.jeremiahlowe.caffeineengine.graphics;

import net.net16.jeremiahlowe.caffeineengine.camera.RenderListener;
import net.net16.jeremiahlowe.caffeineengine.game.GameObject;

public abstract class Renderer implements RenderListener{
	public GameObject parent;
	public boolean render = true;
	public Renderer(GameObject parent){
		this.parent = parent;
		parent.renderer = this;
	}
}
