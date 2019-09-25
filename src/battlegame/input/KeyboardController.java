package battlegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener{

	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right, p, o, downAlt, rightAlt, leftAlt, z, x;
	public int lastDirectionPress;
	
	public KeyboardController() {
		keys = new boolean[256];
		cantPress = new boolean[keys.length];
		justPressed = new boolean[keys.length];
		lastDirectionPress = KeyEvent.VK_D;	}

	public void tick() {
		
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		downAlt = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_A];
		leftAlt = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D];
		rightAlt = keys[KeyEvent.VK_RIGHT];
		p = keys[KeyEvent.VK_P];
		x = keys[KeyEvent.VK_X];
		o = keys[KeyEvent.VK_O];
		z = keys[KeyEvent.VK_Z];
		}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
				return false;
		return justPressed[keyCode];
		}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() > 256)
			return;
		keys[e.getKeyCode()] = true;
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

}
