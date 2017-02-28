package net.net16.jeremiahlowe.caffeineengine.geometry;

import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Transform implements Cloneable{
	public Vector2 position;
	public Rotation rotation;
	public Transform(){this(Vector2.ZERO, Rotation.NORTH);}
	public Transform(Vector2 position){this(position, Rotation.NORTH);}
	public Transform(Rotation rotation){this(Vector2.ZERO, rotation);}
	public Transform(Vector2 position, Rotation rotation){
		this.position = position; this.rotation = rotation;
	}
	public void reset(){
		position = Vector2.ZERO;
		rotation = Rotation.NORTH;
	}
	@Override
	public Transform clone(){
		Transform out = new Transform();
		out.position = position.clone();
		out.rotation = rotation.clone();
		return out;
	}
}
