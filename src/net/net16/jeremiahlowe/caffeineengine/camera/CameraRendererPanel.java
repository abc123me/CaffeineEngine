package net.net16.jeremiahlowe.caffeineengine.camera;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import javax.swing.JPanel;

import net.net16.jeremiahlowe.caffeineengine.Input;
import net.net16.jeremiahlowe.caffeineengine.gameui.UIElement;

public class CameraRendererPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public Camera camera;
	private BufferedImage buffer;
	private Input input;
	public CameraRendererPanel(Camera camera){
		this.camera = camera;
	}
	@Override
	public void paintComponent(Graphics g){
		camera.setPixelSize(getWidth(), getHeight());
		buffer = new BufferedImage(getWidth(), getHeight(), ColorModel.TRANSLUCENT);
		camera.renderGraphics(buffer.getGraphics());
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}
	public Input getInput(){
		return input;
	}
	public void addUIElement(UIElement elem){
		camera.addRenderListener(elem, true);
	}
	public void removeUIElement(UIElement elem){
		camera.removeRenderListener(elem, true);
	}
}
