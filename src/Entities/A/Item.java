package Entities.A;

import A.Level;
import A.Main;
import Items.A.ItemI;

public class Item extends Entity{
	
	public static final int itemHeight = 50;
	public static final int itemWidth = 50;
	
	public ItemI item;

	public Item(double xPos, double yPos) {
		super(xPos, yPos, 0, 0, itemWidth, itemHeight, 0);
	}
	
	public void updateEntity() {
		int xClick = Main.gamePanel.xClick;
		int yClick = Main.gamePanel.yClick;
		if((Main.gamePanel.leftClick || Main.gamePanel.rightClick) && xPos < xClick && xPos + itemWidth > xClick
			&& yPos < yClick && yPos + itemHeight > yClick 
			&& 8100 >= Math.pow((double) xPos - Level.player.xPos, 2) + Math.pow((double) yPos - Level.player.yPos, 2)) {
				Level.player.inventory.addItem(item);
				Level.entityList.remove(this);
			}
	}
	
	public void setItem(ItemI item) {
		this.item = item;
	}
	
}
