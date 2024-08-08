package Items.A;

import java.awt.Color;

import A.Level;
import Blocks.Block;
import Blocks.StoneFloor;
import Blocks.Torch;
import Entities.A.Item;

public class TorchI extends ItemI{
	
	public final int radius = 4;
	
	public TorchI(int quantity) {
		super(1, 2, quantity);
		super.item = new Item(0, 0);
		item.setItem(this);
		color = Color.yellow;
	}
	
	public void action() {
		if(Level.field[(int) (Level.player.yPos + 35) / Block.cellHeight][(int) (Level.player.xPos + 35) / Block.cellWidth].getClass() == StoneFloor.class) {
			Level.field[(int) (Level.player.yPos + 35) / Block.cellHeight][(int) (Level.player.xPos + 35) / Block.cellWidth] 
					= new Torch((int) (Level.player.xPos + 35) / Block.cellWidth * Block.cellWidth, (int) (Level.player.yPos + 35) / Block.cellHeight * Block.cellHeight);
			quantity--;
		}
	}

}
