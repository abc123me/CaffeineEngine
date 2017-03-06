package net.net16.jeremiahlowe.caffeineengine.testing;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

import net.net16.jeremiahlowe.caffeineengine.Game;
import net.net16.jeremiahlowe.caffeineengine.game.GameObject;
import net.net16.jeremiahlowe.caffeineengine.graphics.DummyRenderer;

public class CaffeineEngineTesting {
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setVisible(true);
		
		GameObject g = new GameObject();
		g.transform.position = new Vector2(0, 0);
		DummyRenderer dr = new DummyRenderer(g);
		dr.render = true;
		game.addGameObject(g);
		
		game.cameraRendererPanel.repaint();
	}
}
