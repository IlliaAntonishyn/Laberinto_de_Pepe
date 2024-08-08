package Entities.Enemies;

import java.awt.Color;

import A.Level;
import Blocks.Block;
import Entities.A.Auxiliar;
import Entities.A.Enemy;
import Entities.A.Entity;
import Entities.Auxiliars.Explosion;
import Entities.Auxiliars.Projectile;
import Panels.GamePanel;

public class RangeEnemy extends Enemy{
		
	private int counter = 0;
	
	private boolean approachMove;
	private boolean fleeMove;
	private boolean attackMove;
	private boolean getHitMove;
	
	public RangeEnemy(double xPos, double yPos) {
		super(xPos, yPos, 40, 15, 0);
		color = Color.CYAN;
	}
	
	public void updateEntity() {
		if(collideWithAuxiliar()) {
			counter = 0;
			getHitMove = true;
			attackMove = false;
			approachMove = false;
		}else if(!getHitMove) {
			if(129600 >= Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2)) {
				fleeMove = true;
			}else if(810000 >= Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2)) {
				approachMove = true;
				fleeMove = false;
			} 
		}
		if(getHitMove) {
			counter++;
			xVel = xHitVel;
			yVel = yHitVel;
			if(counter == 5) {
				xHitVel = 0;
				yHitVel = 0;
			}
			if(counter >= 8) {
				counter = 0;
				getHitMove = false;
				if(health <= 0) Level.entityList.remove(this);
			}
		}else {
			if(attackMove) {
				counter++;
				if(counter >= 120) {
					attackMove();
					counter = 0;
				}
			}
			if(fleeMove) {
				movementMove(false);
			}else if(approachMove) {
				movementMove(true);
			}
		}
		super.updateEntity();
	}
	
	private void attackMove() {
		if(396900 >= Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2)) {
			double h = Math.sqrt(Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2));
			if(Level.player.yPos < this.yPos) immune = new Projectile(xPos, yPos, -Math.acos( - (Level.player.xPos - this.xPos) / h), 5);
			else immune = new Projectile(xPos, yPos, Math.acos( - (Level.player.xPos - this.xPos) / h), 5);
			Level.entityList.add(immune);
		}
	}
	
	private void movementMove(boolean approach) {
		double vX = Level.player.xPos - this.xPos;
		double vY = Level.player.yPos - this.yPos;
		if(vX == 0) vX = 0.0000001;
		if(vY == 0) vY = 0.0000001;
		int startX;
		int startY;
		if(Level.player.xPos < this.xPos) {
			startX = (int) Level.player.xPos;
		}else {
			startX = (int) this.xPos;
		}
		if(Level.player.yPos < this.yPos) {
			startY = (int) Level.player.yPos;
		}else {
			startY = (int) this.yPos;
		}
		if(!checkForObstacles(startX, startY, vX, vY)) {
			setSpeed(vX, vY, approach);
			attackMove = true;
		}else {
			attackMove = false;
			xVel = 0;
			yVel = 0;
		}
	}
	
	private void setSpeed(double vX, double vY, boolean approach) {
		double h = Math.sqrt(Math.pow(vX, 2) + Math.pow(vY, 2));
		if(approach) {
			xVel = vX * maxV / 10 / h;
			yVel = vY * maxV / 10 / h;
		}else {
			xVel = -vX * maxV / 10 / h;
			yVel = -vY * maxV / 10 / h;
		}
	}

}
