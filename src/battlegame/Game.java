package battlegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import battlegame.graphics.Assets;
import battlegame.graphics.Display;
import battlegame.input.KeyboardController;
import battlegame.world.World;

public class Game implements Runnable{
	
	public static int screenWidth = 1248;
	public static int screenHeight = 640;
	
	private Thread thread;
	private boolean running = false;
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	private World currentWorld;
	private KeyboardController keyController;
	
	public Game() {
		keyController = new KeyboardController();
	}
	
	public void init() throws IOException {
		display = new Display(screenWidth, screenHeight);
		display.getFrame().addKeyListener(keyController);
		Assets.init();
		currentWorld = new World("/testlvl.txt");
		currentWorld.init();
	}

	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {	
		keyController.tick();
		currentWorld.tick();
	}
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, screenWidth, screenHeight);
		currentWorld.render(g);
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		long currentTime = System.nanoTime();
		long newTime = 0;
		while(running) {
			newTime = System.nanoTime();
			if((newTime - currentTime) <= timePerTick)
				continue;
			
			currentTime = System.nanoTime();
			//System.out.println("Tick");
			render();
			tick();
		}
			
		}
	
	public KeyboardController getKeyController(){
		return keyController;
	}

	public World getCurrentWorld() {
		return currentWorld;
	}
	
	}


