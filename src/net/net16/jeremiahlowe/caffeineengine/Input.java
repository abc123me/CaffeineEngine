package net.net16.jeremiahlowe.caffeineengine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Input implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener{
	private static List<KeyEvent> keysPressed = new ArrayList<KeyEvent>();
	private static boolean[] mouseButtons = new boolean[10];
	private static Vector2 mousePosition = new Vector2();
	private static List<Runnable> mouseCallbacks = new ArrayList<Runnable>();
	private static List<Runnable> mouseMoveCallbacks = new ArrayList<Runnable>();
	private static List<Runnable> mouseWheelCallbacks = new ArrayList<Runnable>();
	private static List<Runnable> keyCallbacks = new ArrayList<Runnable>();
	private static int wheelPos = 0;
	private static boolean mouseEntered = false;
	public static final Input instance;
	static{
		instance = new Input();
	} 
	public static boolean isMouseButtonPressed(int btn){
		return mouseButtons[btn];
	}
	public static boolean isKeyPressed(int keyCode){
		for(KeyEvent k : keysPressed) if(k.getKeyCode() == keyCode) return true;
		return false;
	}
	public static int getMouseWheelPos(){
		return wheelPos;
	}
	public static Vector2 getMousePosition(){
		return mousePosition.clone();
	}
	public static void addKeyCallback(Runnable r){
		keyCallbacks.add(r);
	}
	public static void addMouseCallback(Runnable r){
		mouseCallbacks.add(r);
	}
	public static void addMouseMoveCallback(Runnable r){
		mouseMoveCallbacks.add(r);
	}
	public static void addMouseWheelCallback(Runnable r){
		mouseWheelCallbacks.add(r);
	}
	public static void removeKeyCallback(Runnable r){
		keyCallbacks.remove(r);
	}
	public static void removeMouseCallback(Runnable r){
		mouseCallbacks.remove(r);
	}
	public static void removeMouseMoveCallback(Runnable r){
		mouseMoveCallbacks.remove(r);
	}
	public static void removeMouseWheelCallback(Runnable r){
		mouseWheelCallbacks.remove(r);
	}
	private Input(){}
	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		wheelPos = mwe.getWheelRotation();
		for(Runnable r : mouseWheelCallbacks) r.run();
	}
	@Override
	public void mouseDragged(MouseEvent me) {
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		for(Runnable r : mouseMoveCallbacks) r.run();
	}
	@Override
	public void mouseMoved(MouseEvent me) {
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		for(Runnable r : mouseMoveCallbacks) r.run();
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		for(Runnable r : mouseCallbacks) r.run();
	}
	@Override
	public void mousePressed(MouseEvent me) {
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		if(me.getButton() > mouseButtons.length){
			boolean[] n = new boolean[me.getButton()];
			for(int i = 0; i < me.getButton(); i++){
				if(i < mouseButtons.length) n[i] = mouseButtons[i];
				else n[i] = false;
			}
			mouseButtons = n;
		}
		mouseButtons[me.getButton()] = true;
		for(Runnable r : mouseCallbacks) r.run();
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		if(me.getButton() > mouseButtons.length){
			boolean[] n = new boolean[me.getButton()];
			for(int i = 0; i < me.getButton(); i++){
				if(i < mouseButtons.length) n[i] = mouseButtons[i];
				else n[i] = false;
			}
			mouseButtons = n;
		}
		mouseButtons[me.getButton()] = false;
		for(Runnable r : mouseCallbacks) r.run();
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		keysPressed.add(ke);
		for(Runnable r : keyCallbacks) r.run();
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		keysPressed.remove(ke);
		for(Runnable r : keyCallbacks) r.run();
	}
	@Override
	public void keyTyped(KeyEvent ke) {
		for(Runnable r : keyCallbacks) r.run();
	}
	@Override
	public void mouseEntered(MouseEvent me) {
		mouseEntered = true;
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		for(Runnable r : mouseMoveCallbacks) r.run();
	}
	@Override
	public void mouseExited(MouseEvent me) {
		mouseEntered = false;
		mousePosition.x = me.getX();
		mousePosition.y = me.getY();
		for(Runnable r : mouseMoveCallbacks) r.run();
	}
	public boolean isMouseEntered(){
		return mouseEntered;
	}
}