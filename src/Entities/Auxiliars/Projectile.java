package Entities.Auxiliars;

import A.Level;
import Blocks.Block;
import Entities.A.Auxiliar;

public class Projectile extends Auxiliar{
	
	public Projectile(double xPos, double yPos, double angle, int damage) {
		super(xPos + 25, yPos + 25, - Math.cos(angle) * 5, Math.sin(angle) * 5, 20, 20, damage);
	}
	
	public void updateEntity() {
		super.updateEntity();
		
		playerInteraction();
		
		int blockX = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].xPos;
		int blockY = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].yPos;
		int blockWidth = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].width;
		int blockHeight = Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].height;
		
		if(Level.field[(int) yPos / Block.cellHeight][(int) xPos / Block.cellWidth].solid &&
			(blockX <= xPos && blockX + blockWidth >= xPos && blockY <= yPos && blockY + blockHeight >= yPos
			|| blockX <= xPos + width && blockX + blockWidth >= xPos + width && blockY <= yPos && blockY + blockHeight >= yPos
			|| blockX <= xPos && blockX + blockWidth >= xPos && blockY <= yPos + height && blockY + blockHeight >= yPos + height
			|| blockX <= xPos + width && blockX + blockWidth >= xPos + width && blockY <= yPos + height && blockY + blockHeight >= yPos + height)) {
			Level.entityList.remove(this);
		}
	}
	
	private void playerInteraction() {
		if(this.getClass() != Level.player.immune.getClass()) {
			if(xPos >= Level.player.xPos && xPos <= Level.player.xPos + Level.player.width 
					&& yPos >= Level.player.yPos && yPos <= Level.player.yPos + Level.player.height
					|| xPos + width >= Level.player.xPos && xPos + width <= Level.player.xPos + Level.player.width 
					&& yPos >= Level.player.yPos && yPos <= Level.player.yPos + Level.player.height
					|| xPos >= Level.player.xPos && xPos <= Level.player.xPos + Level.player.width 
					&& yPos + height >= Level.player.yPos && yPos + height <= Level.player.yPos + Level.player.height
					|| xPos + width >= Level.player.xPos && xPos + width <= Level.player.xPos + Level.player.width 
					&& yPos + height >= Level.player.yPos && yPos + height <= Level.player.yPos + Level.player.height) {
				double h = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
				Level.player.hit(10 * xVel / h, 10 * yVel / h, damage);
				Level.entityList.remove(this);
			}
		}
	}

}
