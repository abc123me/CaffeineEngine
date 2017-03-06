package net.net16.jeremiahlowe.caffeineengine.graphics;

import java.awt.Graphics;

import net.net16.jeremiahlowe.caffeineengine.game.GameObject;

public class DummyRenderer extends Renderer{
	public DummyRenderer(GameObject parent) {
		super(parent);
	}
	@Override
	public void onEnterRender(Graphics g) {
		System.out.println(parent.name + ": Enter render!");
	}
	@Override
	public void onExitRender(Graphics g) {
		System.out.println(parent.name + ": Exit render");
	}
}
