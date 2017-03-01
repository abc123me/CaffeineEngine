package net.net16.jeremiahlowe.caffeineengine.renderer;

import java.awt.Color;
import java.awt.Graphics;

import net.net16.jeremiahlowe.caffeineengine.camera.RenderListener;

public abstract class Renderer implements RenderListener{
	public Color color = new Color(0, 0, 0, 0);
	public boolean render = true;
	public abstract void onRender(Graphics g);
	public void onEnterRender(Graphics g){
		if(render) onRender(g);
	}
	public void onExitRender(Graphics g){}
}
