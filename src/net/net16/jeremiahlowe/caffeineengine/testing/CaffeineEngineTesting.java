package net.net16.jeremiahlowe.caffeineengine.testing;

import net.net16.jeremiahlowe.caffeineengine.Game;
import net.net16.jeremiahlowe.caffeineengine.game.GameObject;

public class CaffeineEngineTesting {
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setVisible(true);
		
		GameObject g = new GameObject();
		game.addGameObject(g);
	}
}
