package Items.A;

import java.awt.Color;

import A.Level;

public class BombsI extends ItemI{

	public int damage;
	
	public BombsI(int width, int height, int quantity, int damage) {
		super(width, height, quantity);
		this.damage = damage;
		color = Color.black;
	}
	
	public void action() {
		quantity--;
	}
	
}
