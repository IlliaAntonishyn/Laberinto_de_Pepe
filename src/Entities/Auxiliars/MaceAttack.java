package Entities.Auxiliars;

import A.Level;
import Blocks.Block;
import Entities.A.Auxiliar;

public class MaceAttack extends Auxiliar{
	
	private static double radius = 1;
	
	public MaceAttack(double xPos, double yPos, double angle, int damage) {
		super(xPos + 35 - Math.cos(angle) * 90 - radius * Block.cellWidth, yPos + 35 + Math.sin(angle) * 90 - radius * Block.cellHeight,
				0, 0, (int) (radius * 2 * Block.cellWidth), (int) (radius * 2 * Block.cellHeight), damage);
	}
	
	public void updateEntity() {
		if(counter >= 1) {
			Level.entityList.remove(this);
		}
		counter++;
	}

}
