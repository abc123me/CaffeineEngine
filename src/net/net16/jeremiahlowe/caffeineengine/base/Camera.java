package net.net16.jeremiahlowe.caffeineengine.base;

public class Camera {
	public Transform transform;
	private int pixelWidth, pixelHeight;
	public void setPixelSize(int w, int h){
		if(w <= 0 || h <= 0) throw new RuntimeException("Camera width/height must be greater than zero!");
		
	}
}
