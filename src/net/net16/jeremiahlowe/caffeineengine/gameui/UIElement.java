package net.net16.jeremiahlowe.caffeineengine.gameui;

import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.caffeineengine.Input;
import net.net16.jeremiahlowe.caffeineengine.camera.RenderListener;

public abstract class UIElement implements RenderListener{
	public boolean visible = true, enabled = true;
	public Vector2 position = new Vector2(0, 0);
	private final Runnable mwc, mmc, mc, kc;
	
	public UIElement(){
		mwc = new Runnable(){@Override public void run(){onMouseWheel();}};
		mmc = new Runnable(){@Override public void run(){onMouseMove();}};
		mc = new Runnable(){@Override public void run(){onMouse();}};
		kc = new Runnable(){@Override public void run(){onKey();}};
	}
	final void addMouseCallback(){
		Input.addMouseCallback(mc);
	}
	final void addMouseMoveCallback(){
		Input.addMouseMoveCallback(mmc);
	}
	final void addMouseWheelCallback(){
		Input.addMouseWheelCallback(mwc);
	}
	final void removeMouseCallback(){
		Input.removeMouseCallback(mc);
	}
	final void removeMouseWheelCallback(){
		Input.removeMouseCallback(mwc);
	}
	final void removeMouseMoveCallback(){
		Input.removeMouseCallback(mmc);
	}
	final void addKeyCallback(){
		Input.addKeyCallback(kc);
	}
	final void removeKeyCallback(){
		Input.removeKeyCallback(kc);
	}
	public abstract void paint(Graphics g);
	public abstract void paintDisabled(Graphics g);
	@Override
	public final void onExitRender(Graphics g){
		if(visible){
			if(enabled) paint(g); 
			else paintDisabled(g);
		}
	}
	@Override
	public final void onEnterRender(Graphics g){}
	public void remove() {}
	public void onMouse() {}
	public void onMouseMove() {}
	public void onMouseWheel() {}
	public void onKey() {}
}
