package net.net16.jeremiahlowe.caffeineengine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Input implements MouseListener, KeyListener, MouseMotionListener{
	private List<Integer> keysPressed = new ArrayList<Integer>();
	private List<Integer> mouseButtonsPressed = new ArrayList<Integer>();
	private Vector2 mousePosition = new Vector2();
	private boolean mouseEntered;
	
	public boolean isKeyPressed(int keycode){
		return keysPressed.contains(new Integer(keycode));
	}
	public Vector2 getMousePosition(){
		return mousePosition.clone();
	}
	public boolean isMousePressed(int button){
		return mouseButtonsPressed.contains(new Integer(button));
	}
	public boolean isMouseEntered(){
		return mouseEntered;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keysPressed.add(new Integer(arg0.getKeyCode()));
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		keysPressed.remove(new Integer(arg0.getKeyCode()));
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		mouseEntered = true;
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		mouseEntered = false;
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseButtonsPressed.add(new Integer(arg0.getButton()));
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseButtonsPressed.remove(new Integer(arg0.getButton()));
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
	}
}
