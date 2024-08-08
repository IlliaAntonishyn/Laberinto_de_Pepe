package Entities.Auxiliars;

import A.Level;
import Blocks.Block;
import Entities.A.Auxiliar;

public class SpearAttack extends Auxiliar{
	
	public SpearAttack(double xPos, double yPos, double angle, int damage) {
		super(xPos + 25, yPos + 25, - Math.cos(angle) * 20, Math.sin(angle) * 20, 20, 20, damage);

	}
	
	public void updateEntity() {
		super.updateEntity();
		int blockX = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].xPos;
		int blockY = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].yPos;
		int blockWidth = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].width;
		int blockHeight = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].height;
		if(Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].solid &&
				(blockX <= xPos && blockX + blockWidth >= xPos && blockY <= yPos && blockY + blockHeight >= yPos
				|| blockX <= xPos + width && blockX + blockWidth >= xPos + width && blockY <= yPos && blockY + blockHeight >= yPos
				|| blockX <= xPos && blockX + blockWidth >= xPos && blockY <= yPos + height && blockY + blockHeight >= yPos + height
				|| blockX <= xPos + width && blockX + blockWidth >= xPos + width && blockY <= yPos + height && blockY + blockHeight >= yPos + height)
				|| counter >= 7) {
			Level.entityList.remove(this);
		}
		counter++;
	}

}
