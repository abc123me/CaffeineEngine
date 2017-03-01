package net.net16.jeremiahlowe.caffeineengine;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

import net.net16.jeremiahlowe.TickedOff.TickManager;
import net.net16.jeremiahlowe.caffeineengine.camera.Camera;
import net.net16.jeremiahlowe.caffeineengine.camera.CameraRendererPanel;
import net.net16.jeremiahlowe.caffeineengine.gameui.UIElement;
import net.net16.jeremiahlowe.caffeineengine.geometry.Bounds;

public class Game{
	public Camera mainCamera;
	private boolean paused = false;
	public TickManager gameTick;
	private Thread gameTickThread;
	public CameraRendererPanel cameraRendererPanel;
	public JFrame frame;
	
	public Game(){
		gameTick = new TickManager(); 
		mainCamera = new Camera();
		frame = new JFrame(); 
		cameraRendererPanel = new CameraRendererPanel(mainCamera);
		cameraRendererPanel.setVisible(true); 
		cameraRendererPanel.setEnabled(true);
		cameraRendererPanel.setBackground(new Color(255, 255, 255));
		Bounds bds = new Bounds();
		bds.w = 500; bds.h = 350;
		bds.x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - bds.w / 2;
		bds.y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - bds.h / 2;
		frame.add(cameraRendererPanel); 
		frame.setBounds(bds.toRectangle());
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
		return cameraRendererPanel.getInput();
	}
	public void addUIElement(UIElement elem){
		cameraRendererPanel.addUIElement(elem);
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
