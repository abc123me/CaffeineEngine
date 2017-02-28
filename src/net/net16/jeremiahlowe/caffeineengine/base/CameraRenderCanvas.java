package net.net16.jeremiahlowe.caffeineengine.base;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

@SuppressWarnings("serial")
public class CameraRenderCanvas extends Canvas{
	public Camera camera;
	private BufferedImage buffer;
	public CameraRenderCanvas(Camera camera){
		this.camera = camera;
	}
	@Override
	public void update(Graphics g){
		buffer = new BufferedImage(getWidth(), getHeight(), ColorModel.TRANSLUCENT);
		camera.renderGraphics(buffer.getGraphics());
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}
}
