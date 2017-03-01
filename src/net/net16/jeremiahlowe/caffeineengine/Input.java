package net.net16.jeremiahlowe.caffeineengine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Input implements NativeMouseListener, NativeMouseMotionListener, NativeMouseWheelListener, NativeKeyListener{
	private static List<NativeKeyEvent> keysPressed = new ArrayList<NativeKeyEvent>();
	private static boolean[] mouseButtons = new boolean[10];
	private static Vector2 mousePosition = new Vector2();
	private static List<Runnable> mouseCallbacks = new ArrayList<Runnable>();
	private static List<Runnable> mouseMoveCallbacks = new ArrayList<Runnable>();
	private static List<Runnable> mouseWheelCallbacks = new ArrayList<Runnable>();
	private static List<Runnable> keyCallbacks = new ArrayList<Runnable>();
	private static int wheelPos = 0;
	static{
		Input i = new Input();
		Logger.getLogger(GlobalScreen.class.getName()).setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();
			GlobalScreen.getInstance().addNativeKeyListener(i);
			GlobalScreen.getInstance().addNativeMouseListener(i);
			GlobalScreen.getInstance().addNativeMouseMotionListener(i);
			GlobalScreen.getInstance().addNativeMouseWheelListener(i);
		}catch(Exception e){
			Utility.onLibraryError(e, "jnativehook", "www.github.com/kwhat/jnativehook", "1.1.5");
			System.exit(-1);
		}
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
			@Override
			public void run(){
				if(GlobalScreen.isNativeHookRegistered()) 
					GlobalScreen.unregisterNativeHook();
			}
		}));
	} 
	public static boolean isMouseButtonPressed(int btn){
		return mouseButtons[btn];
	}
	public static boolean isKeyPressed(int keyCode){
		for(NativeKeyEvent k : keysPressed) if(k.getKeyCode() == keyCode) return true;
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
	public void nativeMouseWheelMoved(NativeMouseWheelEvent arg0) {
		wheelPos = arg0.getWheelRotation();
		for(Runnable r : mouseWheelCallbacks) r.run();
	}
	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
		for(Runnable r : mouseMoveCallbacks) r.run();
	}
	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
		for(Runnable r : mouseMoveCallbacks) r.run();
	}
	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
		for(Runnable r : mouseCallbacks) r.run();
	}
	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
		if(arg0.getButton() > mouseButtons.length){
			boolean[] n = new boolean[arg0.getButton()];
			for(int i = 0; i < arg0.getButton(); i++){
				if(i < mouseButtons.length) n[i] = mouseButtons[i];
				else n[i] = false;
			}
			mouseButtons = n;
		}
		mouseButtons[arg0.getButton()] = true;
		for(Runnable r : mouseCallbacks) r.run();
	}
	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		mousePosition.x = arg0.getX();
		mousePosition.y = arg0.getY();
		if(arg0.getButton() > mouseButtons.length){
			boolean[] n = new boolean[arg0.getButton()];
			for(int i = 0; i < arg0.getButton(); i++){
				if(i < mouseButtons.length) n[i] = mouseButtons[i];
				else n[i] = false;
			}
			mouseButtons = n;
		}
		mouseButtons[arg0.getButton()] = false;
		for(Runnable r : mouseCallbacks) r.run();
	}
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		keysPressed.add(arg0);
		for(Runnable r : keyCallbacks) r.run();
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		keysPressed.remove(arg0);
		for(Runnable r : keyCallbacks) r.run();
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		for(Runnable r : keyCallbacks) r.run();
	}
}