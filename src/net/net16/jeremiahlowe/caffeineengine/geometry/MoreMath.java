package net.net16.jeremiahlowe.caffeineengine.geometry;

import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class MoreMath {
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
}
