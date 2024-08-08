package Entities.Auxiliars;

import A.Level;
import Blocks.Block;
import Blocks.StoneFloor;
import Blocks.Wall;
import Entities.A.Auxiliar;

public class Explosion extends Auxiliar{
			
	private double radius;
	
	public Explosion(double xPos, double yPos, int damage, double radius) {
		super(xPos + 35 - radius * Block.cellWidth, yPos + 35 - radius * Block.cellHeight, 0, 0, (int) (radius * 2 * Block.cellWidth), (int) (radius * 2 * Block.cellHeight), damage);
		this.radius = radius;
	}
	
	public void updateEntity() {
		if(counter == 1) {
			explodeBlocks();
			playerInteraction();
			Level.entityList.remove(this);
		}
		counter++;
	}
	
	private void playerInteraction() {
		if(radius * radius * Block.cellWidth * Block.cellHeight >= Math.pow(Level.player.xPos + 35 - (xPos + radius * Block.cellWidth), 2) + Math.pow(Level.player.yPos + 35 - (yPos + radius * Block.cellHeight), 2)
			|| radius * radius * Block.cellWidth * Block.cellHeight >= Math.pow(Level.player.xPos + Level.player.width - (xPos + radius * Block.cellWidth), 2) + Math.pow(Level.player.yPos - (yPos + radius * Block.cellHeight), 2)
			|| radius * radius * Block.cellWidth * Block.cellHeight >= Math.pow(Level.player.xPos - (xPos + radius * Block.cellWidth), 2) + Math.pow(Level.player.yPos + Level.player.height - (yPos + radius * Block.cellHeight), 2)
			|| radius * radius * Block.cellWidth * Block.cellHeight >= Math.pow(Level.player.xPos + Level.player.width - (xPos + radius * Block.cellWidth), 2) + Math.pow(Level.player.yPos + Level.player.height - (yPos + radius * Block.cellHeight), 2)) {
			double h = Math.sqrt(Math.pow(Level.player.xPos + 35 - xPos + width / 2, 2) + Math.pow(Level.player.yPos + 35 - yPos + height / 2, 2));
			Level.player.hit(20 * (Level.player.xPos + 35 - xPos + width / 2) / h, 20 * (Level.player.yPos + 35 - yPos + height / 2) / h, damage);
		}
	}
	
	private void explodeBlocks() {
		int blockX;
		int blockY;
		int blockWidth;
		int blockHeight;
		double d = radius * radius * Block.cellWidth * Block.cellHeight;
		
		double x = xPos + radius * Block.cellWidth;
		double y = yPos + radius * Block.cellHeight;
		
		for(int i = 0; i < (radius * 2 * Block.cellHeight + 45) / Block.cellHeight; i++) {
			for(int j = 0; j < (radius * 2 * Block.cellWidth + 45) / Block.cellWidth; j++) {
				if((int) Math.round(yPos / Block.cellHeight) + i >= 0 && (int) Math.round(yPos / Block.cellHeight) + i < Level.field.length
						&& (int) Math.round(xPos / Block.cellWidth) + j >= 0 && (int) Math.round(xPos / Block.cellHeight) + j < Level.field[i].length) {
					blockX = Level.field[(int) Math.round(yPos / Block.cellHeight) + i][(int) Math.round(xPos / Block.cellWidth) + j].xPos;
					blockY = Level.field[(int) Math.round(yPos / Block.cellHeight) + i][(int) Math.round(xPos / Block.cellWidth) + j].yPos;
					blockWidth = Block.cellWidth;
					blockHeight = Block.cellHeight;
					
					if(Level.field[(int) Math.round(yPos / Block.cellHeight) + i][(int) Math.round(xPos / Block.cellWidth) + j].getClass() == Wall.class && (
							d >= Math.pow(blockX - x, 2) + Math.pow(blockY - y, 2) 
							|| d >= Math.pow(blockX + blockWidth - x, 2) + Math.pow(blockY - y, 2)
							|| d >= Math.pow(blockX - x, 2) + Math.pow(blockY + blockHeight - y, 2) 
							|| d >= Math.pow(blockX + blockWidth - x, 2) + Math.pow(blockY + blockHeight - y, 2))) {
						Level.field[(int) Math.round(yPos / Block.cellHeight) + i][(int) Math.round(xPos / Block.cellWidth) + j] 
								= new StoneFloor(((int) xPos / Block.cellWidth + j) * Block.cellWidth, ((int) yPos / Block.cellHeight + i) * Block.cellHeight);
					}
				}

			}
		}
	}

}
