package net.net16.jeremiahlowe.caffeineengine.camera;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.caffeineengine.game.World;
import net.net16.jeremiahlowe.caffeineengine.geometry.Bounds;

public class Camera{
	public Bounds bounds;
	private int pixelWidth, pixelHeight;
	private List<RenderListener> uiRenderListeners = new ArrayList<RenderListener>();
	
	private World worldReference;
	
	public Camera(World worldReference){
		bounds = new Bounds(2.5f, 2.5f, 5, 5);
		this.worldReference = worldReference;
	}
	
	public void setPixelSize(int w, int h){
		if(w <= 0 || h <= 0) throw new RuntimeException("Camera width/height must be greater than zero!");
		pixelWidth = w; pixelHeight = h;
	}
	public void renderGraphics(Graphics g){
		worldReference.renderObjectsOnCamera(g, this, true);
		worldReference.renderObjectsOnCamera(g, this, false);
		for(RenderListener rl : uiRenderListeners) rl.onEnterRender(g);
		for(RenderListener rl : uiRenderListeners) rl.onExitRender(g);
	}
	public void addUIRenderListener(RenderListener rl){
		uiRenderListeners.add(rl);
	}
	public void removeUIRenderListener(RenderListener rl){
		uiRenderListeners.remove(rl);
	}
	public Vector2 castWorldToScreen(Vector2 world){
		Vector2 screen = new Vector2();
		if(positionInBounds(world)) throw new RuntimeException("Position out of bounds: " + world + " from camera " + this);
		screen.x = pixelWidth / (world.x - bounds.x);
		screen.y = pixelHeight / (bounds.y - world.y);
		return screen;
	}
	public Vector2 castScreenToWorld(Vector2 screen){
		Vector2 camPos = bounds.getPosition();
		Vector2 world = new Vector2();
		world.x = camPos.x + pixelWidth / screen.x;
		world.y = camPos.y + pixelHeight / screen.y;
		return world;
	}
	public boolean positionInBounds(Vector2 world){
		Vector2 camPos = bounds.getPosition();
		return (world.x < camPos.x || world.x > camPos.x + bounds.w || world.y < camPos.y || world.y > camPos.y + bounds.h);
	}
	@Override
	public String toString(){
		return "Camera " + bounds.getPosition() + " " + bounds.w + "x" + bounds.h;
	}
}
