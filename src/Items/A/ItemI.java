package Items.A;

import java.awt.Color;

import javax.swing.JLabel;

import Entities.A.Item;

public class ItemI {
	
	public Color color;
	
	public int cooldown;
	
	public static final int cellHeight = 40;
	public static final int cellWidth = 40;
	
	public int xInventory;
	public int yInventory;
	
	public int width;
	public int height;
	
	public int quantity;
	
	public Item item;
		
	public ItemI(int width, int height, int quantity) {
		this.width = width;
		this.height = height;
		this.quantity = quantity;
		
		cooldown = 0;
	}
	
	public void action() {}

}
