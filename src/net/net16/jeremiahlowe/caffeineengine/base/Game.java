package net.net16.jeremiahlowe.caffeineengine.base;

import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

import net.net16.jeremiahlowe.TickedOff.TickManager;

public class Game{
	public Camera mainCamera;
	public TickManager renderTick, gameTick;
	public CameraRenderCanvas cameraRenderer;
	public JFrame frame;
	
	public Game(){
		renderTick = new TickManager(); gameTick = new TickManager();
		frame = new JFrame(); cameraRenderer = new CameraRenderCanvas(mainCamera);
		cameraRenderer.setVisible(true); cameraRenderer.setEnabled(true);
		Rectangle bds = new Rectangle();
		bds.width = 500; bds.height = 350;
		bds.x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - bds.width / 2;
		bds.y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - bds.height / 2;
		frame.add(cameraRenderer); frame.setBounds(bds);
	}
}
