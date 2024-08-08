package A;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Blocks.*;
import Panels.EndGamePanel;
import Panels.GamePanel;
import Panels.StartPanel;

public class Main {
	
	public static int frameCount = 0;
	
	public static boolean inGame;
	public static boolean victory;
	public static boolean defeat;
	
	public static Difficulties difficulty;
		
	public static Frame frame;
	public static StartPanel startPanel;
	public static GamePanel gamePanel;
	public static EndGamePanel endGamePanel;
		
	public static TimerTask task;
	public static Timer timer;
	
	public static void main(String[] args) {
		frame = new Frame();
		startPanel = new StartPanel();
		frame.add(startPanel);
		startPanel.repaint();
		frame.pack();	
	}
	
	private static void startGameLoop() {
		gamePanel = new GamePanel();
		frame.add(gamePanel);
		endGamePanel = new EndGamePanel();
		gamePanel.requestFocus();
		frame.pack();
		
		inGame = true;
		victory = false;
		defeat = false;
		
		task = new TimerTask() {
			public void run() {
				if(victory) {
					endGamePanel.setVictoryText();
				}else if(defeat) {
					endGamePanel.setDefeatText();
				}
				if(inGame) {
					Level.updateEntities();
					gamePanel.paintScreen();
					frameCount++;
				}else {
					gamePanel.add(endGamePanel);
				}
			}	
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 10, 10);
		SwingUtilities.updateComponentTreeUI(frame);
		
	}
	
	public static void loadGame(String url) throws FileNotFoundException {
		frame.remove(startPanel);
		Level.loadLevel(url);
		startGameLoop();
	}
	
	public static void saveGame() {
		Level.saveLevel();
		frameCount = 0;
		goToMenu();
	}
	
	public static void goToMenu() {
		timer.cancel();
		inGame = false;
		frame.remove(gamePanel);
		SwingUtilities.updateComponentTreeUI(frame);
		frame.add(startPanel);
		startPanel.refresh();
	}
	
	public static void startNewGame() throws FileNotFoundException {
		frame.remove(startPanel);
		Level.loadNewLevel(difficulty.url);
		startGameLoop();
	}
}
