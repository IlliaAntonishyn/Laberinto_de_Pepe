package Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import A.Frame;
import A.Main;

public class EndGamePanel extends JPanel implements ActionListener{
	
	private JButton button;
	
	public EndGamePanel() {
		this.setSize(Frame.width, 200);
		this.setLocation(0, (Frame.height - 200) / 2);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		this.setOpaque(true);
		this.setLayout(null);
		
		button = new JButton("Back to Menu");
		button.setSize(300, 200);
		button.setLocation((Frame.width - 300) / 2, 0);
		button.setOpaque(true);
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.white);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setVisible(true);
		button.addActionListener(this);
		this.add(button);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.fillRect(0,  0, Frame.width, 200);
	}
	
	public void setVictoryText() {
		button.setText("YOU WON");
	}
	
	public void setDefeatText() {
		button.setText("YOU LOST");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.goToMenu();
	}
	
}
