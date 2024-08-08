package Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import A.Frame;
import A.Level;
import A.Main;

public class MenuPanel extends JPanel implements ActionListener{
	
	private final int menuWidth = 300;
	private final int menuHeight = 200;
	
	private JLabel text;
	private JButton leave;
	private JButton save;

	public MenuPanel() {
		this.setSize(menuWidth, menuHeight);
		this.setLocation((Frame.width - menuWidth) / 2, (Frame.height - menuHeight) / 2);
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setVisible(true);
		this.setOpaque(true);
		
		createTexts();
	}
	
	private void createTexts() {
		text = new JLabel("MENU");
		text.setLocation(10, 10);
		text.setSize(menuWidth - 20, menuHeight / 2 - 20);
		text.setForeground(Color.white);
		text.setBackground(Color.DARK_GRAY);
		text.setFont(new Font("Arial", Font.BOLD, 20));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setOpaque(true);
		this.add(text);
		
		leave = new JButton("Don't save");
		leave.setLocation(menuWidth / 2 + 5, menuHeight / 2);
		leave.setSize(menuWidth / 2 - 15, menuHeight / 2 - 10);
		leave.setForeground(Color.white);
		leave.setBackground(Color.DARK_GRAY);
		leave.setFont(new Font("Arial", Font.BOLD, 20));
		leave.setHorizontalAlignment(JLabel.CENTER);
		leave.setOpaque(true);
		leave.addActionListener(this);
		this.add(leave);
		
		save = new JButton("Save");
		save.setLocation(10, menuHeight / 2);
		save.setSize(menuWidth / 2 - 15, menuHeight / 2 - 10);
		save.setForeground(Color.white);
		save.setBackground(Color.DARK_GRAY);
		save.setFont(new Font("Arial", Font.BOLD, 20));
		save.setHorizontalAlignment(JLabel.CENTER);
		save.setOpaque(true);
		save.addActionListener(this);
		this.add(save);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == leave) {
			Main.goToMenu();
		}else {
			Main.saveGame();
		}
	}
	
}
