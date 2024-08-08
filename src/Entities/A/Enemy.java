package Entities.A;

import A.Level;
import Blocks.Block;
import Entities.Auxiliars.*;
import Entities.Auxiliars.Projectile;

public class Enemy extends Entity{
	
	protected Entity immune;
	
	protected int counter = 0;
	
	protected int health;
	protected int maxHealth;
	protected int maxV;
	
	protected double xHitVel;
	protected double yHitVel;
	
	public Enemy(double xPos, double yPos, int maxHealth, int maxV, int strength) {
		super(xPos, yPos, 0, 0, 70, 70, strength);
		this.maxHealth = maxHealth;
		this.maxV = maxV;
		health = maxHealth;
		immune = new Auxiliar(0, 0, 0, 0, 0, 0, 0);
	}
	
	protected boolean collideWithAuxiliar() {
		for(int i = 0; i < Level.entityList.size(); i++) {
			if(!immune.equals(Level.entityList.get(i))) {
				if(Level.entityList.get(i).getClass() == Projectile.class
						|| Level.entityList.get(i).getClass() == SwordAttack.class
						|| Level.entityList.get(i).getClass() == FistAttack.class
						|| Level.entityList.get(i).getClass() == SpearAttack.class) {
					double entityX = Level.entityList.get(i).xPos;
					double entityY = Level.entityList.get(i).yPos;
					double entityWidth = Level.entityList.get(i).width;
					double entityHeight = Level.entityList.get(i).height;
					if((xPos <= entityX && xPos + width >= entityX && yPos <= entityY && yPos + height >= entityY
							|| xPos <= entityX + entityWidth && xPos + width >= entityX + entityWidth && yPos <= entityY && yPos + height >= entityY
							|| xPos <= entityX && xPos + width >= entityX && yPos <= entityY + entityHeight && yPos + height >= entityY + entityHeight
							|| xPos <= entityX + entityWidth && xPos + width >= entityX + entityWidth && yPos <= entityY + entityHeight && yPos + height >= entityY + entityHeight)) {
						takeSquareDamage(Level.entityList.get(i));
						if(Level.entityList.get(i).getClass() == Projectile.class 
								|| Level.entityList.get(i).getClass() == FistAttack.class) Level.entityList.remove(Level.entityList.get(i));
						return true;
					}
				}else if(Level.entityList.get(i).getClass() == Explosion.class
						|| Level.entityList.get(i).getClass() == DaggerAttack.class
						|| Level.entityList.get(i).getClass() == MaceAttack.class) {
					int radius = Level.entityList.get(i).width / Block.cellWidth / 2 + 1;
					if(Math.pow(radius * Block.cellWidth, 2) >= Math.pow(xPos + 35 - (Level.entityList.get(i).xPos + radius * Block.cellWidth + 1), 2) 
							+ Math.pow(yPos + 35 - (Level.entityList.get(i).yPos + radius * Block.cellHeight + 1), 2)) {
						takeCircleDamage(Level.entityList.get(i));
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void takeCircleDamage(Entity entity) {
		health -= entity.damage;
		immune = entity;
		int maxSpeed = 10;
		if(entity.getClass() == Explosion.class) maxSpeed = 20;
		double h = Math.sqrt(Math.pow(xPos + 35 - (entity.xPos + entity.width / 2), 2) + Math.pow(yPos + 35 - (entity.yPos + entity.height / 2), 2));
		xHitVel = maxSpeed * (xPos + 35 - (entity.xPos + entity.width / 2)) / h;
		yHitVel = maxSpeed * (yPos + 35 - (entity.yPos + entity.height / 2)) / h;
	}
	
	private void takeSquareDamage(Entity entity) {
		health -= entity.damage;
		immune = entity;
		double h = Math.sqrt(Math.pow(entity.xPos - (this.xPos + (this.width - entity.width) / 2), 2) 
				+ Math.pow(entity.yPos - (this.yPos + (this.height - entity.height) / 2), 2));
		xHitVel = - 10 * (entity.xPos - (this.xPos + (this.width - entity.width) / 2)) / h;
		yHitVel = - 10 * (entity.yPos - (this.yPos + (this.height - entity.height) / 2)) / h;
	}
	
	protected boolean checkForObstacles(int startX, int startY, double vX, double vY) {
		int blockX;
		int blockY;
		int blockWidth;
		int blockHeight;
		
		double playerX = Level.player.xPos + 35;
		double playerY = Level.player.yPos + 35;
		
		for(int i = 0; i <= Math.abs(Level.player.yPos - this.yPos) / Block.cellHeight; i++) {
			for(int j = 0; j <= Math.abs(Level.player.xPos - this.xPos) / Block.cellWidth; j++) {
				blockX = Level.field[startY / Block.cellHeight + i][startX / Block.cellWidth + j].xPos;
				blockY = Level.field[startY / Block.cellHeight + i][startX / Block.cellWidth + j].yPos;
				blockWidth = Level.field[startY / Block.cellHeight + i][startX / Block.cellWidth + j].width;
				blockHeight = Level.field[startY / Block.cellHeight + i][startX / Block.cellWidth + j].height;
				
				if(Level.field[startY / Block.cellHeight + i][startX / Block.cellWidth + j].solid && (
					blockX <= playerX + vX * (blockY - playerY) / vY && blockX + blockWidth >= playerX + vX * (blockY - playerY) / vY
					|| blockX <= playerX + vX * (blockY - playerY + blockHeight) / vY && blockX + blockWidth >= playerX + vX * (blockY - playerY + blockHeight) / vY
					|| blockY <= playerY + vY * (blockX - playerX) / vX && blockY + blockHeight >= playerY + vY * (blockX - playerX) / vX
					|| blockY <= playerY + vY * (blockX - playerX + blockWidth) / vX && blockY + blockHeight >= playerY + vY * (blockX - playerX + blockWidth) / vX)) {
					return true;
				}
			}
		}
		return false;
	}
}
