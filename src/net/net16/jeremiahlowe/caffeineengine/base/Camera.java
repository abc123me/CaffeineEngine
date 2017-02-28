package net.net16.jeremiahlowe.caffeineengine.base;

import java.awt.Graphics;

public class Camera extends GameComponent{
	public Transform transform;
	private int pixelWidth, pixelHeight;
	public void setPixelSize(int w, int h){
		if(w <= 0 || h <= 0) throw new RuntimeException("Camera width/height must be greater than zero!");
		pixelWidth = w; pixelHeight = h;
	}
	public void renderGraphics(Graphics g){
		for(GameObject o : GameObject.getGameObjects()){
			
		}
	}
}
