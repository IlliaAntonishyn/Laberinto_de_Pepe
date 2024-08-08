package Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import A.Difficulties;
import A.Frame;
import A.Main;

public class StartPanel extends JPanel implements ActionListener{
	
	private List<String> saveFiles = new ArrayList<String>();
	
	private boolean newGameMenu;
	private boolean loadGameMenu;
	private boolean controlsMenu;
	
	private JLabel title;
	private JButton newGame;
	private JButton loadGame;
	private JButton controls;
	private JLabel creators;
	
	private JButton nextPage;
	private JButton prevPage;
	private JButton[] saves;
	private int savesPage;
	
	private JButton tutorialMode;
	private JButton easyMode;
	private JButton mediumMode;
	private JButton hardMode;
	private JButton extremeMode;
	
	private JLabel instruction1;
	private JLabel instruction2;
	private JLabel instruction3;
	private JLabel instruction4;
	private JLabel instruction5;
	private JLabel instruction6;
	private JLabel instruction7;
	private JLabel instruction8;
	private JLabel instruction9;
	
	public StartPanel() {
		this.setBackground(Color.black);
		this.setSize(Frame.width, Frame.height);
		this.setLayout(null);
		
		refresh();
		
		prepareLabels();
		prepareHiddenLabels();
		
		SwingUtilities.updateComponentTreeUI(this);

	}
	
	public void refresh() {
		saveFiles = listSaves();
		savesPage = 0;
		saves = new JButton[8];
		for(int i = 0; i < saves.length; i++) {
			saves[i] = new JButton();
			saves[i].setSize(Frame.width / 2 - 10,  50);
			saves[i].setBackground(Color.DARK_GRAY);
			saves[i].setForeground(Color.white);
			saves[i].setFont(new Font("Arial", Font.BOLD, 18));
			saves[i].setOpaque(true);
			saves[i].setVisible(false);
			saves[i].setHorizontalAlignment(JButton.LEFT);
			saves[i].addActionListener(this);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0,  0, Frame.width, Frame.height);
	}
	
	private List<String> listSaves(){
		File directory = new File("PrivateSaves");
		return Stream.of(directory.listFiles()).filter(file -> !file.isDirectory()).map(File::getName).collect(Collectors.toList());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGame) {
			newGameMenu = true;
			loadGameMenu = false;
			controlsMenu = false;
		}else if(e.getSource() == loadGame) {
			newGameMenu = false;
			loadGameMenu = true;
			controlsMenu = false;
		}else if(e.getSource() == controls) {
			newGameMenu = false;
			loadGameMenu = false;
			controlsMenu = true;
		}
		if(newGameMenu) {
			tutorialMode.setVisible(true);
			easyMode.setVisible(true);
			mediumMode.setVisible(true);
			hardMode.setVisible(true);
			extremeMode.setVisible(true);
			if(e.getSource() == tutorialMode) {
				Main.difficulty = Difficulties.TUTORIAL;
				try {
					Main.startNewGame();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}else if(e.getSource() == easyMode) {
				Main.difficulty = Difficulties.EASY;
				try {
					Main.startNewGame();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}			}else if(e.getSource() == mediumMode) {
				Main.difficulty = Difficulties.MEDIUM;
				try {
					Main.startNewGame();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}			}else if(e.getSource() == hardMode) {
				Main.difficulty = Difficulties.HARD;
				try {
					Main.startNewGame();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}			}else if(e.getSource() == extremeMode) {
				Main.difficulty = Difficulties.EXTREME;
				try {
					Main.startNewGame();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}			}
		}else {
			tutorialMode.setVisible(false);
			easyMode.setVisible(false);
			mediumMode.setVisible(false);
			hardMode.setVisible(false);
			extremeMode.setVisible(false);
		}
		if(loadGameMenu) {
			File file;
			Scanner data;
			
			if(e.getSource() == nextPage) {
				savesPage++;
			}else if(e.getSource() == prevPage) {
				savesPage--;
			}
			if(savesPage > saves.length / 8) savesPage--;
			else if(savesPage < 0) savesPage++;
			
			int slots;
			if(saveFiles.size() - 8 * savesPage > 8) slots = 8;
			else slots = saveFiles.size() - 8 * savesPage;
			
			for(int i = 0; i < 8; i++) {
				if(i < slots) { 
					file = new File("PrivateSaves/" + saveFiles.get(i + 8 * savesPage));
					String difficulty = "";
					int height;
					int width;
					try {
						data = new Scanner(file);
						height = data.nextInt();
						width = data.nextInt();
						if(height == 17 && width == 11) {
							difficulty = "Tutorial";
						}else if(height == 16 && width == 27) {
							difficulty = "Easy";
						}else if(height == 23 && width == 35) {
							difficulty = "Medium";
						}else if(height == 33 && width == 33) {
							difficulty = "Hard";
						}else {
							difficulty = "Extreme";
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					saves[i].setText(saveFiles.get(i + 8 * savesPage) + "   " + difficulty);
				}else saves[i].setText(null);
				saves[i].setLocation(Frame.width / 2, 100 + 50 * i);
				saves[i].setVisible(true);
				this.add(saves[i]);
			}
			
			for(int i = 0; i < 8; i++) {
				if(e.getSource() == saves[i] && saves[i].getText() != null) {
					try {
						Main.loadGame(saveFiles.get(i + 8 * savesPage));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			nextPage.setVisible(true);
			prevPage.setVisible(true);
			
		}else {
			int slots;
			if(saves.length > 8) slots = 8;
			else slots = saves.length;
			for(int i = 0; i < slots; i++) {
				saves[i].setVisible(false);
			}
			nextPage.setVisible(false);
			prevPage.setVisible(false);
		}
		if(controlsMenu) {
			instruction1.setVisible(true);
			instruction2.setVisible(true);
			instruction3.setVisible(true);
			instruction4.setVisible(true);
			instruction5.setVisible(true);
			instruction6.setVisible(true);
			instruction7.setVisible(true);
			instruction8.setVisible(true);
			instruction9.setVisible(true);
		}else {
			instruction1.setVisible(false);
			instruction2.setVisible(false);
			instruction3.setVisible(false);
			instruction4.setVisible(false);
			instruction5.setVisible(false);
			instruction6.setVisible(false);
			instruction7.setVisible(false);
			instruction8.setVisible(false);
			instruction9.setVisible(false);		
		}
	}
	
private void prepareHiddenLabels() {
		
		tutorialMode = new JButton("Tutorial:   Size 11x17   Boss null");
		tutorialMode.setSize(Frame.width / 2 - 10,  70);
		tutorialMode.setLocation(Frame.width / 2, 100);
		tutorialMode.setBackground(Color.DARK_GRAY);
		tutorialMode.setOpaque(true);
		tutorialMode.setVisible(false);
		tutorialMode.setFont(new Font("Arial", Font.BOLD, 15));
		tutorialMode.setForeground(Color.white);
		tutorialMode.addActionListener(this);
		this.add(tutorialMode);
		
		easyMode = new JButton("Easy:   Size 27x16   Boss null");
		easyMode.setSize(Frame.width / 2 - 10,  70);
		easyMode.setLocation(Frame.width / 2, 200);
		easyMode.setBackground(Color.DARK_GRAY);
		easyMode.setOpaque(true);
		easyMode.setVisible(false);
		easyMode.setFont(new Font("Arial", Font.BOLD, 15));
		easyMode.setForeground(Color.white);
		easyMode.addActionListener(this);
		this.add(easyMode);
		
		mediumMode = new JButton("Medium:   Mida 35x23   Boss null");
		mediumMode.setSize(Frame.width / 2 - 10,  70);
		mediumMode.setLocation(Frame.width / 2, 300);
		mediumMode.setBackground(Color.DARK_GRAY);
		mediumMode.setOpaque(true);
		mediumMode.setVisible(false);
		mediumMode.setFont(new Font("Arial", Font.BOLD, 15));
		mediumMode.setForeground(Color.white);
		mediumMode.addActionListener(this);
		this.add(mediumMode);
		
		hardMode = new JButton("Hard:   Mida 33x33   Boss null");
		hardMode.setSize(Frame.width / 2 - 10,  70);
		hardMode.setLocation(Frame.width / 2, 400);
		hardMode.setBackground(Color.DARK_GRAY);
		hardMode.setOpaque(true);
		hardMode.setVisible(false);
		hardMode.setFont(new Font("Arial", Font.BOLD, 15));
		hardMode.setForeground(Color.white);
		hardMode.addActionListener(this);
		this.add(hardMode);
		
		extremeMode = new JButton("Extreme:   Mida 65x49   Boss null");
		extremeMode.setSize(Frame.width / 2 - 10,  70);
		extremeMode.setLocation(Frame.width / 2, 500);
		extremeMode.setBackground(Color.DARK_GRAY);
		extremeMode.setOpaque(true);
		extremeMode.setVisible(false);
		extremeMode.setFont(new Font("Arial", Font.BOLD, 15));
		extremeMode.setForeground(Color.white);
		extremeMode.addActionListener(this);
		this.add(extremeMode);
		
		instruction1 = new JLabel("W - Move forward");
		instruction1.setSize(Frame.width / 2 - 10,  50);
		instruction1.setLocation(Frame.width / 2, 100);
		instruction1.setBackground(Color.DARK_GRAY);
		instruction1.setOpaque(true);
		instruction1.setVisible(false);
		instruction1.setFont(new Font("Arial", Font.BOLD, 15));
		instruction1.setForeground(Color.white);
		instruction1.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction1);
		
		instruction2 = new JLabel("A - Move left");
		instruction2.setSize(Frame.width / 2 - 10,  50);
		instruction2.setLocation(Frame.width / 2, 150);
		instruction2.setBackground(Color.DARK_GRAY);
		instruction2.setOpaque(true);
		instruction2.setVisible(false);
		instruction2.setFont(new Font("Arial", Font.BOLD, 15));
		instruction2.setForeground(Color.white);
		instruction2.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction2);
		
		instruction3 = new JLabel("S - Move down");
		instruction3.setSize(Frame.width / 2 - 10,  50);
		instruction3.setLocation(Frame.width / 2, 200);
		instruction3.setBackground(Color.DARK_GRAY);
		instruction3.setOpaque(true);
		instruction3.setVisible(false);
		instruction3.setFont(new Font("Arial", Font.BOLD, 15));
		instruction3.setForeground(Color.white);
		instruction3.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction3);
		
		instruction4 = new JLabel("D - Move right");
		instruction4.setSize(Frame.width / 2 - 10,  50);
		instruction4.setLocation(Frame.width / 2, 250);
		instruction4.setBackground(Color.DARK_GRAY);
		instruction4.setOpaque(true);
		instruction4.setVisible(false);
		instruction4.setFont(new Font("Arial", Font.BOLD, 15));
		instruction4.setForeground(Color.white);
		instruction4.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction4);
		
		instruction5 = new JLabel("E - Open/Close inventory");
		instruction5.setSize(Frame.width / 2 - 10,  50);
		instruction5.setLocation(Frame.width / 2, 300);
		instruction5.setBackground(Color.DARK_GRAY);
		instruction5.setOpaque(true);
		instruction5.setVisible(false);
		instruction5.setFont(new Font("Arial", Font.BOLD, 15));
		instruction5.setForeground(Color.white);
		instruction5.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction5);
		
		instruction6 = new JLabel("Spacebar - Use dash");
		instruction6.setSize(Frame.width / 2 - 10,  50);
		instruction6.setLocation(Frame.width / 2, 350);
		instruction6.setBackground(Color.DARK_GRAY);
		instruction6.setOpaque(true);
		instruction6.setVisible(false);
		instruction6.setFont(new Font("Arial", Font.BOLD, 15));
		instruction6.setForeground(Color.white);
		instruction6.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction6);
		
		instruction7 = new JLabel("Left Click - Use item in right hand");
		instruction7.setSize(Frame.width / 2 - 10,  50);
		instruction7.setLocation(Frame.width / 2, 400);
		instruction7.setBackground(Color.DARK_GRAY);
		instruction7.setOpaque(true);
		instruction7.setVisible(false);
		instruction7.setFont(new Font("Arial", Font.BOLD, 15));
		instruction7.setForeground(Color.white);
		instruction7.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction7);
		
		instruction8 = new JLabel("Right Click - Use item in left hand");
		instruction8.setSize(Frame.width / 2 - 10,  50);
		instruction8.setLocation(Frame.width / 2, 450);
		instruction8.setBackground(Color.DARK_GRAY);
		instruction8.setOpaque(true);
		instruction8.setVisible(false);
		instruction8.setFont(new Font("Arial", Font.BOLD, 15));
		instruction8.setForeground(Color.white);
		instruction8.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction8);
		
		instruction9 = new JLabel("Q - Open menu");
		instruction9.setSize(Frame.width / 2 - 10,  50);
		instruction9.setLocation(Frame.width / 2, 500);
		instruction9.setBackground(Color.DARK_GRAY);
		instruction9.setOpaque(true);
		instruction9.setVisible(false);
		instruction9.setFont(new Font("Arial", Font.BOLD, 15));
		instruction9.setForeground(Color.white);
		instruction9.setHorizontalAlignment(JLabel.CENTER);
		this.add(instruction9);
	}

private void prepareLabels() {
	
	nextPage = new JButton("Next Page");
	nextPage.setSize(Frame.width / 4 - 5, 50);
	nextPage.setLocation(3 * Frame.width / 4 - 5, 500);
	nextPage.setBackground(Color.white);
	nextPage.setForeground(Color.black);
	nextPage.setFont(new Font("Arial", Font.BOLD, 20));
	nextPage.setOpaque(true);
	nextPage.setVisible(false);
	nextPage.addActionListener(this);
	this.add(nextPage);
	
	prevPage = new JButton("Prev Page");
	prevPage.setSize(Frame.width / 4 - 5, 50);
	prevPage.setLocation(Frame.width / 2, 500);
	prevPage.setBackground(Color.white);
	prevPage.setForeground(Color.black);
	prevPage.setFont(new Font("Arial", Font.BOLD, 20));
	prevPage.setOpaque(true);
	prevPage.setVisible(false);
	prevPage.addActionListener(this);
	this.add(prevPage);
	
	title = new JLabel("Pepe's Laberynth");
	title.setSize(Frame.width,  70);
	title.setLocation(10, 10);
	title.setBackground(Color.DARK_GRAY);
	title.setOpaque(true);
	title.setFont(new Font("Arial", Font.BOLD, 50));
	title.setForeground(Color.white);
	this.add(title);
	
	newGame = new JButton("New Game");
	newGame.setSize(300,  100);
	newGame.setLocation(10, 100);
	newGame.setOpaque(true);
	newGame.setFont(new Font("Arial", Font.BOLD, 30));
	newGame.setForeground(Color.black);
	newGame.addActionListener(this);
	this.add(newGame);
	
	loadGame = new JButton("Load Game");
	loadGame.setSize(300,  100);
	loadGame.setLocation(10, 280);
	loadGame.setOpaque(true);
	loadGame.setFont(new Font("Arial", Font.BOLD, 30));
	loadGame.setForeground(Color.black);
	loadGame.addActionListener(this);
	this.add(loadGame);
	
	controls = new JButton("Controls");
	controls.setSize(300,  100);
	controls.setLocation(10, 460);
	controls.setOpaque(true);
	controls.setFont(new Font("Arial", Font.BOLD, 30));
	controls.setForeground(Color.black);
	controls.addActionListener(this);
	this.add(controls);
	
	creators = new JLabel("v.01 - Illia Antonishyn && Lucas Husa");
	creators.setSize(Frame.width,  30);
	creators.setLocation(10, Frame.height - 30);
	creators.setBackground(Color.black);
	creators.setOpaque(true);
	creators.setFont(new Font("Arial", Font.BOLD, 10));
	creators.setForeground(Color.white);
	this.add(creators);
}

}
