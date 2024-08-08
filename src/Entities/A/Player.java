package Entities.A;
 
import java.awt.Color;

import A.Frame;
import A.Level;
import A.Main;
import Blocks.Block;
import Entities.Auxiliars.FistAttack;
import Entities.Auxiliars.MaceAttack;
import Items.Weapons.*;
import Panels.Equipment;
import Panels.GamePanel;
import Panels.Inventory;

public class Player extends Entity{
	
	public Inventory inventory;
	public Equipment equipment;
	
	public Auxiliar immune;
	
	private boolean dash;
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	public final int inventoryWidth = 8;
	public final int inventoryHeight = 12;
		
	private int counter = 0;
	private int rightCounter = 0;
	private int leftCounter = 0;
	public boolean rightReady = true;
	public boolean leftReady = true;
	
	private final int dashCost = 5;
	public double xDashV;
	public double yDashV;
	public int maxV;
	public int maxHealth;
	public int health;
	public int maxStamina;
	public int stamina;
	public int strength;
	
	public int extraHealth;
	public int extraSpeed;
	public int extraDamage;
	
	private double xHitVel;
	private double yHitVel;
	
	public boolean hit;
	
	public Player() {
		super(90, 90, 0, 0, 70, 70, 0);
		inventory = new Inventory(8, 12);
		equipment = new Equipment();
		
		maxV = 20;
		maxHealth = 50;
		maxStamina = 50;
		strength = 1;
		
		health = maxHealth;
		stamina = maxStamina;
		
		immune = new Auxiliar(0, 0, 0, 0, 0, 0, 0);
		
		hit = false;
		
		color = Color.red;
	}
	
	private void updateExtras() {
		maxHealth = 50;
		maxV = 20;
		strength = 1;
		
		extraHealth = 0;
		extraSpeed = 0;
		extraDamage = 0;
		if(equipment.equipment[0] != null) equipment.equipment[0].action();
		if(equipment.equipment[1] != null) equipment.equipment[1].action();
		if(equipment.equipment[2] != null) equipment.equipment[2].action();
		
		maxHealth += extraHealth;
		maxV += extraSpeed;
		strength += extraDamage;
	}

	private void useItem() {
		if(GamePanel.leftClick) {
			if(equipment.equipment[4] != null) {
				if(leftReady) {
					leftReady = false;
					equipment.equipment[4].action();
					if(equipment.equipment[4].quantity <= 0) equipment.equipment[4] = null;
				}else {
					leftCounter++;
					if(leftCounter >= equipment.equipment[4].cooldown) { 
						leftCounter = 0;
						leftReady = true;
						GamePanel.leftClick = false;
					}
				}
			}else if(stamina >= 5){
				FistAttack attack;
				double h = Math.sqrt(Math.pow(Level.player.xPos - GamePanel.xClick, 2) + Math.pow(Level.player.yPos - GamePanel.yClick, 2));
				if(GamePanel.yClick < Level.player.yPos) attack = new FistAttack(Level.player.xPos, Level.player.yPos, -Math.acos((Level.player.xPos - GamePanel.xClick) / h), strength);
				else attack = new FistAttack(Level.player.xPos, Level.player.yPos, Math.acos((Level.player.xPos - GamePanel.xClick) / h), strength);
				Level.entityList.add(attack);
				immune = attack;
				stamina -= 5;
				GamePanel.leftClick = false;
			}
		}
		if(GamePanel.rightClick) {
			if(equipment.equipment[3] != null) {
				if(rightReady) {
					equipment.equipment[3].action();
					if(equipment.equipment[3].quantity <= 0) equipment.equipment[3] = null;
				}else {
					rightCounter++;
					if(rightCounter >= equipment.equipment[3].cooldown) {
						rightCounter = 0;
						rightReady = true;
						GamePanel.rightClick = false;
					}
				}
			}else if(stamina >= 5){
				FistAttack attack;
				double h = Math.sqrt(Math.pow(Level.player.xPos + 35 - GamePanel.xClick, 2) + Math.pow(Level.player.yPos + 35 - GamePanel.yClick, 2));
				if(GamePanel.yClick < Level.player.yPos) attack = new FistAttack(Level.player.xPos + 35, Level.player.yPos + 35, -Math.acos((Level.player.xPos - GamePanel.xClick) / h), 1);
				else attack = new FistAttack(Level.player.xPos + 35, Level.player.yPos + 35, Math.acos((Level.player.xPos - GamePanel.xClick) / h), 1);
				Level.entityList.add(attack);
				immune = attack;
				GamePanel.rightClick = false;
			}
		}
	}
	
	private void updateHealth() {
		if(health >= maxHealth) health = maxHealth;
	}

	public void updateEntity() {
		updateExtras();
		useItem();
		updateStamina();
		updateHealth();
		if(hit) {
			xVel = xHitVel;
			yVel = yHitVel;
			counter++;
			if(counter >= 5) {
				hit = false;
				counter = 0;
			}
		}else {
			setSpeed();
		}
		super.updateEntity();
		if(health <= 0) {
			Main.defeat = true;
			Main.inGame = false;
		}
	}
	
	public void hit(double xHitVel, double yHitVel, int damage) {
		hit = true;
		health -= damage;
				
		this.xHitVel = xHitVel;
		this.yHitVel = yHitVel;
	}
	
	private void setSpeed() {
		if(!dash) {
			if(up) {
				yVel = -maxV / 10;
			}else yVel = 0;
			if(down) {
				yVel = maxV / 10;
			}else if(!up) yVel = 0;
			if(up && down) yVel = 0;

			if(right) {
				xVel = maxV / 10;
			}else xVel = 0;
			if(left) {
				xVel = -maxV / 10;
			}else if(!right) xVel = 0;
			if(right && left) xVel = 0;
		}else {
			xVel = xDashV;
			yVel = yDashV;
			counter++;
			if(counter >= 17) resetDash();
		}
	}
	
	private void updateStamina() {
		if(Main.frameCount % 70 == 0) stamina++;
		if(stamina > maxStamina) stamina = maxStamina;
	}
	
	public void setUp() {
		up = true;
	}
	
	public void setDown() {
		down = true;
	}
	
	public void setRight() {
		right = true;
	}
	
	public void setLeft() {
		left = true;
	}
	
	public void setDash() {
		if(stamina > 4) {
			dash = true;
			if(right && !left) {
				xDashV = maxV /2;
				yDashV = 0;
			}else if(left && !right) {
				xDashV = -maxV /2;
				yDashV = 0;
			}else if(up && !down) {
				xDashV = 0;
				yDashV = -maxV / 2;
			}else if(down && !up) {
				xDashV = 0;
				yDashV = maxV / 2;
			}else dash = false;
			stamina -= 5;
		}
	}

	public void resetUp() {
		up = false;
	}
	
	public void resetDown() {
		down = false;
	}
	
	public void resetRight() {
		right = false;
	}
	
	public void resetLeft() {
		left = false;
	}
	
	public void resetDash() {
		counter = 0;
		dash = false;
		xDashV = 0;
		yDashV = 0;
	}

}
