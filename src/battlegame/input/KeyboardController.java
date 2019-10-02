package battlegame.input;

import javafx.scene.input.KeyCode;

public class KeyboardController{

	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right, select, action;
	public KeyCode lastDirectionPress;
	
	public KeyboardController() {
		keys = new boolean[256];
		cantPress = new boolean[keys.length];
		justPressed = new boolean[keys.length];
		lastDirectionPress = KeyCode.A;
	}

	public void tick() {
		up = false;
	}



}
