package net.net16.jeremiahlowe.caffeineengine.gameui;

import java.awt.Graphics;

import net.net16.jeremiahlowe.caffeineengine.camera.RenderListener;

public abstract class UIElement implements RenderListener{
	public boolean visible = true, enabled = true;
	
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
}
