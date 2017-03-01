package net.net16.jeremiahlowe.caffeineengine.camera;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.caffeineengine.geometry.Transform;

public class Camera{
	public Transform transform;
	private float viewWindowWidth = 1, viewWindowHeight = 1;
	private int pixelWidth, pixelHeight;
	private List<RenderListener> uiRenderListeners = new ArrayList<RenderListener>();
	private List<RenderListener> gameRenderListeners = new ArrayList<RenderListener>();
	
	public void setPixelSize(int w, int h){
		if(w <= 0 || h <= 0) throw new RuntimeException("Camera width/height must be greater than zero!");
		pixelWidth = w; pixelHeight = h;
	}
	public void renderGraphics(Graphics g){
		for(RenderListener rl : gameRenderListeners) rl.onEnterRender(g);
		for(RenderListener rl : gameRenderListeners) rl.onExitRender(g);
		for(RenderListener rl : uiRenderListeners) rl.onEnterRender(g);
		for(RenderListener rl : uiRenderListeners) rl.onExitRender(g);
	}
	public void addRenderListener(RenderListener rl){addRenderListener(rl, false);}
	public void removeRenderListener(RenderListener rl){removeRenderListener(rl, false);}
	public void addRenderListener(RenderListener rl, boolean isUI){
		(isUI ? uiRenderListeners : gameRenderListeners).add(rl);
	}
	public void removeRenderListener(RenderListener rl, boolean isUI){
		(isUI ? uiRenderListeners : gameRenderListeners).remove(rl);
	}
	public Vector2 castWorldToScreen(Vector2 world){
		Vector2 screen = new Vector2();
		if(positionInBounds(world)) throw new RuntimeException("Position out of bounds: " + world + " from camera " + this);
		screen.x = pixelWidth / (world.x - transform.position.x);
		screen.y = pixelHeight / (transform.position.y - world.y);
		return screen;
	}
	public Vector2 castScreenToWorld(Vector2 screen){
		Vector2 camPos = transform.position;
		Vector2 world = new Vector2();
		world.x = camPos.x + pixelWidth / screen.x;
		world.y = camPos.y + pixelHeight / screen.y;
		return world;
		
	}
	public boolean positionInBounds(Vector2 world){
		Vector2 camPos = transform.position;
		return (world.x < camPos.x || world.x > camPos.x + viewWindowWidth || world.y < camPos.y || world.y > camPos.y + viewWindowHeight);
	}
	@Override
	public String toString(){
		return "Camera " + transform.position + " " + viewWindowWidth + "x" + viewWindowHeight;
	}
}
