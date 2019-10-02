package battlegame.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private int screenWidth;
	private int screenHeight;
	
	public Display(int width, int height) {
		this.screenHeight = height;
		this.screenWidth = width;
		makeDisplay();
	}
	
	public void makeDisplay() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(screenWidth, screenHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GameIcon.png")));;
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(screenWidth, screenHeight));
		canvas.setMinimumSize(new Dimension(screenWidth, screenHeight));
		canvas.setMaximumSize(new Dimension(screenWidth, screenHeight));
		canvas.setFocusable(false);
		canvas.setVisible(true);
		
		frame.add(canvas);
		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
