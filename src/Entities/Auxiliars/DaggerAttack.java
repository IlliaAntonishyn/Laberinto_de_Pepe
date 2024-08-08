package Entities.Auxiliars;

import A.Level;
import Blocks.Block;
import Entities.A.Auxiliar;

public class DaggerAttack extends Auxiliar{
	
	private static double radius = 1.25;
	
	public DaggerAttack(double xPos, double yPos, int damage) {
		super(xPos + 35 - radius * Block.cellWidth, yPos + 35 - radius * Block.cellHeight,
				0, 0, (int) (radius * 2 * Block.cellWidth), (int) (radius * 2 * Block.cellHeight), damage);
	}
	
	public void updateEntity() {
		if(counter >= 1) {
			Level.entityList.remove(this);
		}
		counter++;
	}

}
