package Panels;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import A.Frame;
import A.Level;
import A.Main;
import Blocks.*;
import Entities.A.Item;
import Entities.A.Player;

public class GamePanel extends JPanel implements MouseListener, KeyListener{
			
	private JLabel timeLabel;
	private JLabel playerHealth;
	private JLabel playerStamina;
	private JLabel playerStrength;
	private JLabel playerSpeed;
	
	private PlayerInventory playerInventory;
	private ChestInventory chestInventory;
	private MenuPanel menuPanel;
	
	public static boolean rightClick = false;
	public static boolean leftClick = false;
	public static int xClick;
	public static int yClick;
	
	public int cX;
	public int cY;
	
	public boolean inventoryOpened;
	public boolean chestOpened;
	public boolean menuOpened;

	public GamePanel() {
		inventoryOpened = false;
		chestOpened = false;
		menuOpened = false;
		
		playerInventory = new PlayerInventory();
		chestInventory = new ChestInventory();
		menuPanel = new MenuPanel();
		
		createLabels();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(Frame.width, Frame.height);
		this.setLayout(null);
		this.setVisible(true);
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	
	public void openInventory() {
		this.add(playerInventory);
	}
	
	public void closeInventory() {
		this.remove(playerInventory);
	}
	
	public void openChest(Inventory chest) {
		chestInventory.setChest(chest);
		this.add(chestInventory);
	}
	
	public void closeChest() {
		this.remove(chestInventory);
	}
	
	public void paintScreen() {
		calcTime();
		showPlayerStats();
		calcCameraCords(Level.player);
		repaint();
		if(inventoryOpened) {
			playerInventory.repaint();
		}
	}
	
	private void calcTime() {
		int minutes, seconds, frames;
		String minutesS = "";
		String secondsS = "";
		String framesS = "";
		
		minutes = Main.frameCount / 6000;
		seconds = Main.frameCount % 6000 / 100;
		frames = Main.frameCount % 100;
	
		if(minutes < 10) minutesS += "0";
		if(seconds < 10) secondsS += "0";
		if(frames < 10) framesS += "0";
		minutesS += String.valueOf(minutes);
		secondsS += String.valueOf(seconds);
		framesS += String.valueOf(frames);
		
		timeLabel.setText(minutesS + ":" + secondsS + "." + framesS);
	}
	
	private void showPlayerStats() {
		playerHealth.setText(String.valueOf(Level.player.health) + " / " + String.valueOf(Level.player.maxHealth) + " Health");
		playerStamina.setText(String.valueOf(Level.player.stamina) + " / " + String.valueOf(Level.player.maxStamina) + " Stamina");
		playerSpeed.setText(String.valueOf(Level.player.maxV) + " Speed");
		playerStrength.setText(String.valueOf(Level.player.strength) + " Strength");
	}
	
	private void calcCameraCords(Player player) {
		if(player.xPos + player.width / 2 <= Frame.width / 2) cX = 0;
		else if(player.xPos + player.width / 2 >= Level.field[0].length * Block.cellWidth - Frame.width / 2) 
			cX = Level.field[0].length * Block.cellWidth - Frame.width;
		else cX = (int) player.xPos + player.width / 2 - Frame.width / 2;
		
		if(player.yPos + player.height / 2 <= Frame.height / 2) cY = 0;
		else if(player.yPos + player.height / 2 >= Level.field.length * Block.cellHeight - Frame.height / 2) 
			cY = Level.field.length * Block.cellHeight - Frame.height;
		else cY = (int) player.yPos + player.height / 2 - Frame.height / 2;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < (cY + Frame.height) / Block.cellHeight + 1; i++) {
			for(int j = 0; j < (cX + Frame.width) / Block.cellWidth + 1; j++) {
				if(i < Level.field.length && j < Level.field[0].length) {
					g.setColor(new Color(Level.field[i][j].red, Level.field[i][j].green, Level.field[i][j].blue));
					g.fillRect(Level.field[i][j].xPos - cX, Level.field[i][j].yPos - cY, Level.field[i][j].width, Level.field[i][j].height);
				}
			}
		}
		
		for(int i = 0; i < Level.entityList.size(); i++) {
			g.setColor(Level.entityList.get(i).color);
			g.fillRect((int) Level.entityList.get(i).xPos - cX, (int) Level.entityList.get(i).yPos - cY, 
				Level.entityList.get(i).width, Level.entityList.get(i).height);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(Level.field[(y + cY) / (Block.cellHeight)][(x + cX) / (Block.cellWidth)].getClass() == Chest.class
				&& 16200 >= Math.pow(Level.player.xPos - Level.field[(y + cY) / (Block.cellHeight)][(x + cX) / (Block.cellWidth)].xPos, 2) + 
				Math.pow(Level.player.yPos - Level.field[(y + cY) / (Block.cellHeight)][(x + cX) / (Block.cellWidth)].yPos, 2)) {
			chestOpened = true;
			Level.field[(y + cY) / Block.cellHeight][(x + cX) / Block.cellWidth].action();
		}else {
			if(e.getButton() == 1) leftClick = true;
			else if(e.getButton() == 3) rightClick = true;
			xClick = e.getX() + cX;
			yClick = e.getY() + cY;
		}

	}
	
	private void createLabels() {
		timeLabel = new JLabel("00:00.00");
		timeLabel.setSize(150,  50);
		timeLabel.setLocation(Frame.width - 150, 0);
		timeLabel.setBackground(Color.gray);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setFont(new Font("Arial", Font.BOLD, 28));
		timeLabel.setForeground(Color.GREEN);
		this.add(timeLabel);
		
		playerHealth = new JLabel("00 / 00 Health");
		playerHealth.setSize(250,  40);
		playerHealth.setLocation(Frame.width - 250, Frame.height - 40);
		playerHealth.setBackground(Color.gray);
		playerHealth.setOpaque(true);
		playerHealth.setHorizontalAlignment(JLabel.CENTER);
		playerHealth.setFont(new Font("Arial", Font.BOLD, 28));
		playerHealth.setForeground(Color.red);
		this.add(playerHealth);
		
		playerStamina = new JLabel("00 / 00 Stamina");
		playerStamina.setSize(250,  40);
		playerStamina.setLocation(Frame.width - 250, Frame.height - 40 * 2);
		playerStamina.setBackground(Color.gray);
		playerStamina.setOpaque(true);
		playerStamina.setHorizontalAlignment(JLabel.CENTER);
		playerStamina.setFont(new Font("Arial", Font.BOLD, 28));
		playerStamina.setForeground(Color.yellow);
		this.add(playerStamina);
		
		playerSpeed = new JLabel("00 Speed");
		playerSpeed.setSize(200,  40);
		playerSpeed.setLocation(0, Frame.height - 40 * 2);
		playerSpeed.setBackground(Color.gray);
		playerSpeed.setOpaque(true);
		playerSpeed.setHorizontalAlignment(JLabel.CENTER);
		playerSpeed.setFont(new Font("Arial", Font.BOLD, 28));
		playerSpeed.setForeground(Color.white);
		this.add(playerSpeed);
		
		playerStrength = new JLabel("00 Strength");
		playerStrength.setSize(200,  40);
		playerStrength.setLocation(0, Frame.height - 40);
		playerStrength.setBackground(Color.gray);
		playerStrength.setOpaque(true);
		playerStrength.setHorizontalAlignment(JLabel.CENTER);
		playerStrength.setFont(new Font("Arial", Font.BOLD, 28));
		playerStrength.setForeground(Color.red);
		this.add(playerStrength);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(Main.inGame) {
			switch(e.getKeyChar()) {
			case ' ':
				Level.player.setDash();
				break;
			case 'e':
				if(!menuOpened) {
					if(Main.gamePanel.chestOpened) {
						Main.gamePanel.chestOpened = false;
						Main.gamePanel.closeChest();
					}else if(Main.gamePanel.inventoryOpened) {
						Main.gamePanel.inventoryOpened = false;
						Main.gamePanel.closeInventory();
					}else {
						Main.gamePanel.inventoryOpened = true;
						Main.gamePanel.openInventory();
					}
				}
				break;
			case 'q':
				if(!chestOpened && !inventoryOpened) {
					menuOpened = !menuOpened;
					if(menuOpened) {
						this.add(menuPanel);
					}else {
						this.remove(menuPanel);
					}
				}
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.inGame) {
			switch(e.getKeyCode()) {
			case 87:
				Level.player.setUp();
				break;
			case 65:
				Level.player.setLeft();
				break;
			case 83:
				Level.player.setDown();
				break;
			case 68:
				Level.player.setRight();
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(Main.inGame) {
			switch(e.getKeyCode()) {
			case 87:
				Level.player.resetUp();
				break;
			case 65:
				Level.player.resetLeft();
				break;
			case 83:
				Level.player.resetDown();
				break;
			case 68:
				Level.player.resetRight();
				break;
			}
		}
	}


}
