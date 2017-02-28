package net.net16.jeremiahlowe.caffeineengine.base;

import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Transform implements Cloneable{
	public Vector2 position;
	public Rotation rotation;
	@Override
	public Transform clone(){
		Transform out = new Transform();
		out.position = position.clone();
		out.rotation = rotation.clone();
		return out;
	}
}
