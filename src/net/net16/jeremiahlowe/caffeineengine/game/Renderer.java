package net.net16.jeremiahlowe.caffeineengine.game;

import java.awt.Graphics;

import net.net16.jeremiahlowe.caffeineengine.camera.RenderListener;

public class Renderer implements RenderListener{
	public GameObject parent;
	public Renderer(GameObject parent){
		this.parent = parent;
		parent.renderer = this;
	}
	@Override
	public void onEnterRender(Graphics g) {
		
	}

	@Override
	public void onExitRender(Graphics g) {
		
	}
}
