package net.net16.jeremiahlowe.caffeineengine.geometry;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Bounds {
	public float x, y, w, h;
	public Bounds(){this(0, 0, 0, 0);}
	public Bounds(Vector2 pos, Vector2 size){this(pos.x, pos.y, size.x, size.y);}
	public Bounds(float x, float y, float w, float h){
		if(w < 0 || h < 0) throw new RuntimeException("Width or height must be greator than zero");
		this.x = x; 
		this.y = y; 
		this.w = w; 
		this.h = h;
	}
	public Vector2 getPosition(){return new Vector2(x, y);}
	public Vector2 getSize(){return new Vector2(w, h);}
	public boolean inBounds(float x, float y){return ((x > this.x && x < this.x + w) && (y > this.y && y < this.y + h));}
	public boolean inBounds(Vector2 pos){return inBounds(pos.x, pos.y);}
}
