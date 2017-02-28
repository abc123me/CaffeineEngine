package net.net16.jeremiahlowe.caffeineengine;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

import net.net16.jeremiahlowe.TickedOff.TickManager;
import net.net16.jeremiahlowe.caffeineengine.camera.Camera;
import net.net16.jeremiahlowe.caffeineengine.camera.CameraRenderCanvas;

public class Game{
	public Camera mainCamera;
	private boolean paused = false;
	public TickManager gameTick;
	private Thread gameTickThread;
	public CameraRenderCanvas cameraRendererCanvas;
	public JFrame frame;
	
	public Game(){
		gameTick = new TickManager(); 
		frame = new JFrame(); cameraRendererCanvas = new CameraRenderCanvas(mainCamera);
		cameraRendererCanvas.setVisible(true); cameraRendererCanvas.setEnabled(true);
		cameraRendererCanvas.setBackground(new Color(255, 255, 255));
		Rectangle bds = new Rectangle();
		bds.width = 500; bds.height = 350;
		bds.x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - bds.width / 2;
		bds.y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - bds.height / 2;
		frame.add(cameraRendererCanvas); frame.setBounds(bds);
		gameTickThread = new Thread(new GameTicker(this));
	}
	
	public void start(){
		gameTickThread.start();
	}
	public synchronized void pause(){
		paused = true;
	}
	public synchronized boolean isPaused(){
		return paused;
	}
	public Input getInput(){
		return cameraRendererCanvas.getInput();
	}
}
class GameTicker implements Runnable{
	private Game g;
	private long waitMillis = 0, lastTime = 0, epoch = 0;
	public GameTicker(Game g) {
		this.g = g;
	}
	@Override
	public void run(){
		while(!Thread.interrupted()){
			if(!g.isPaused()){
				if(waitMillis <= 0) g.gameTick.callAll();
				else if(lastTime + waitMillis <= (epoch = System.currentTimeMillis())){
					g.gameTick.callAll();
					lastTime = epoch;
				}
			}
		}
	}
	public synchronized void setMaxFramerate(float fps){
		if(fps < 0) waitMillis = -1;
		else waitMillis = Math.round(1000f * (1f / fps));
	}
}
