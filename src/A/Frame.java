package A;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public static final int height = 700;
	public static final int width = 700;

	public Frame() {
		this.setLocation(0 ,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().setPreferredSize(new Dimension(width, height));
		this.pack();
	}
	
	
	
}
