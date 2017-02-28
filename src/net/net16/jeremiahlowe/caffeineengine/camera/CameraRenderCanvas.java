package net.net16.jeremiahlowe.caffeineengine.camera;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import net.net16.jeremiahlowe.caffeineengine.Input;

@SuppressWarnings("serial")
public class CameraRenderCanvas extends Canvas{
	public Camera camera;
	private BufferedImage buffer;
	private Input input;
	public CameraRenderCanvas(Camera camera){
		this.camera = camera;
		input = new Input();
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
	}
	@Override
	public void update(Graphics g){
		buffer = new BufferedImage(getWidth(), getHeight(), ColorModel.TRANSLUCENT);
		camera.renderGraphics(buffer.getGraphics());
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}
	public Input getInput(){
		return input;
	}
}
