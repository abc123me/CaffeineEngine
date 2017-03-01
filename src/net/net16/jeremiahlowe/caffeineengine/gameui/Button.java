package net.net16.jeremiahlowe.caffeineengine.gameui;

import java.awt.Color;
import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.caffeineengine.Input;
import net.net16.jeremiahlowe.caffeineengine.geometry.Bounds;

public class Button extends UIElement{
	public Runnable onClick;
	public String text = "New Button";
	public Vector2 borderSize = new Vector2(5, 15);
	public int buttonToTriggerUpon = 1;
	public int buttonBorderRoundness = 5;
	private Bounds bounds = new Bounds();
	
	public Button(Runnable onClick, String text){
		super();
		if(onClick == null) throw new NullPointerException("onClick action is null for button (" + text + ")");
		this.onClick = onClick; this.text = text;
		this.addMouseCallback();
	}

	@Override
	public void paint(Graphics g) {
		int txtW = g.getFontMetrics().stringWidth(text), txtH = g.getFontMetrics().getHeight();
		g.setColor(Color.WHITE);
		Bounds bounds = new Bounds();
		bounds.x = position.x; bounds.y = position.y;
		bounds.w = txtW + borderSize.x;
		bounds.h = txtH + borderSize.y;
		g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.w, (int) bounds.h);
		g.setColor(Color.BLACK);
		g.drawRoundRect((int) bounds.x, (int) bounds.y, (int) bounds.w, (int) bounds.h, buttonBorderRoundness, buttonBorderRoundness);
		Vector2 strSize = new Vector2(0, 0);
		strSize.x = position.x + borderSize.x / 2;
		strSize.y = position.y + borderSize.y / 2 + 3 * (txtH / 4);
		g.drawString(text, (int) strSize.x, (int) strSize.y);
	}
	@Override
	public void paintDisabled(Graphics g) {
		
	}
	@Override
	public void onMouse() {
		Vector2 mousePos = Input.getMousePosition();
		if(bounds.inBounds(mousePos))
			if(Input.isMouseButtonPressed(buttonToTriggerUpon)) 
				onClick.run();
	}
}
