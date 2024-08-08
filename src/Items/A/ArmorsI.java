package Items.A;

import java.awt.Color;

import A.Level;

public class ArmorsI extends ItemI{
	
	public int health;
	public int speed;
	public int strength;
	
	public ArmorsI(int width, int height, int health, int speed, int strength) {
		super(width, height, 1);
		this.health = health;
		this.speed = speed;
		this.strength = strength;
		color = Color.blue;
	}
	
	public void action() {
		Level.player.extraDamage = strength;
		Level.player.extraHealth = health;
		Level.player.extraSpeed = speed;
	}
}
