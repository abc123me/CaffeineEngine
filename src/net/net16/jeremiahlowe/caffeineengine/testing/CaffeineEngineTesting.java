package net.net16.jeremiahlowe.caffeineengine.testing;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.caffeineengine.Game;
import net.net16.jeremiahlowe.caffeineengine.gameui.Button;

public class CaffeineEngineTesting {
	public static void main(String[] args) {
		Game game = new Game();
		Button btn = new Button(new Runnable(){
			@Override
			public void run(){
				System.out.println("Hello world!");
			}
		}, "Hello world!");
		btn.position = new Vector2(100, 100);
		game.addUIElement(btn);
		game.frame.setVisible(true);
	}
}
