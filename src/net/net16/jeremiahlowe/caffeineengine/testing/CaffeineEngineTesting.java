package net.net16.jeremiahlowe.caffeineengine.testing;

import net.net16.jeremiahlowe.caffeineengine.base.Camera;
import net.net16.jeremiahlowe.caffeineengine.base.Game;
import net.net16.jeremiahlowe.caffeineengine.base.GameObject;

public class CaffeineEngineTesting {
	public static void main(String[] args) {
		GameObject camera = new GameObject();
		Camera mainCamera = new Camera();
		camera.addComponent(mainCamera);
		Game game = new Game();
		game.frame.setVisible(true);
	}
}
