package net.net16.jeremiahlowe.caffeineengine.geometry;

import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class GameMath {
	public static final Vector2[] rotateVerticiesAround(Rotation rot, Vector2 origin, Vector2... verticies){
		Vector2[] out = new Vector2[verticies.length];
		float angle = rot.getAngleRadians();
		for(int i = 0; i < out.length; i++){
			float x1 = verticies[i].x - origin.x, y1 = verticies[i].y - origin.y;
			double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
			double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
			out[i].x = (float) x2 + origin.x;
			out[i].y = (float) y2 + origin.y;
		}
		return out;
	}
	public static final Vector2[] rotateVerticiesAroundMidpoint(Rotation rot, Vector2... verticies){
		Vector2[] out = new Vector2[verticies.length];
		Vector2 origin = new Vector2();
		float angle = rot.getAngleRadians();
		for(int i = 0; i < verticies.length; i++) origin.translate(verticies[i]);
		origin.x /= verticies.length; origin.y /= verticies.length;
		for(int i = 0; i < out.length; i++){
			float x1 = verticies[i].x - origin.x, y1 = verticies[i].y - origin.y;
			double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
			double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
			out[i].x = (float) x2 + origin.x;
			out[i].y = (float) y2 + origin.y;
		}
		return out;
	}
	public static final Bounds getBoundingBox(Vector2... verticies){
		if(verticies.length < 3) throw new RuntimeException("Invalid vericie length (must be greater than 3): " + verticies.length);
		Vector2 max = new Vector2(Float.MIN_VALUE, Float.MIN_VALUE);
		Vector2 min = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);
		for(int i = 0; i < verticies.length; i++){
			Vector2 v = verticies[i];
			if(v.x >= max.x) max.x = v.x;
			else if(v.x < min.x) min.x = v.x;
			if(v.y >= max.y) max.y = v.y;
			else if(v.y < min.y) min.y = v.y;
		}
		return new Bounds(min, max);
	}
}
