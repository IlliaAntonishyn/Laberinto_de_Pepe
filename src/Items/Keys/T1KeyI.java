package Items.Keys;

import java.awt.Color;

import A.Level;
import Blocks.Block;
import Blocks.Door1;
import Blocks.StoneFloor;
import Entities.A.Item;
import Items.A.ItemI;
import Panels.GamePanel;

public class T1KeyI extends ItemI{
	
	public T1KeyI() {
		super(1, 2, 1);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		color = Color.magenta;
	}

	public void action() {
		if(Level.field[GamePanel.yClick / Block.cellHeight][GamePanel.xClick / Block.cellWidth].getClass() == Door1.class
				&& 20000 >= Math.pow(Level.player.xPos - GamePanel.xClick, 2) + Math.pow(Level.player.yPos - GamePanel.yClick, 2)) {
			Level.field[GamePanel.yClick / Block.cellHeight][GamePanel.xClick / Block.cellWidth] = new StoneFloor(GamePanel.xClick / Block.cellWidth, GamePanel.yClick / Block.cellHeight);
		}
	}
}
