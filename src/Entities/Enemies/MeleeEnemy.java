package Entities.Enemies;

import java.awt.Color;

import A.Level;
import A.Main;
import Blocks.Block;
import Entities.A.Auxiliar;
import Entities.A.Enemy;
import Entities.A.Entity;
import Entities.Auxiliars.Explosion;
import Entities.Auxiliars.Projectile;
import Panels.GamePanel;

public class MeleeEnemy extends Enemy{
			
	private boolean attackMove;
	private boolean approachMove;
	private boolean getHitMove;
	
	public MeleeEnemy(double xPos, double yPos) {
		super(xPos, yPos, 50, 10, 5);
		color = Color.blue;
	}
	
	public void updateEntity() {
		if(collideWithAuxiliar()) {
			counter = 0;
			getHitMove = true;
			attackMove = false;
			approachMove = false;
		}else if(counter == 0) {
			if(8100 >= Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2)) {
				attackMove = true;
			}else if(810000 >= Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2)) {
				approachMove = true;
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
		}else if(attackMove) {
			xVel = 0;
			yVel = 0;
			counter++;
			if(counter >= 100) {
				attackMove();
				counter = 0;
				attackMove = false;
			}
		}else if(approachMove) {
			movementMove();
		}
		super.updateEntity();
	}
	
	private void movementMove() {
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
			setSpeed(vX, vY);
		}else {
			xVel = 0;
			yVel = 0;
		}
	}
	
	private void attackMove() {
		if(8100 >= Math.pow(Level.player.xPos - this.xPos, 2) + Math.pow(Level.player.yPos - this.yPos, 2)) {
			double h = Math.sqrt(Math.pow(Level.player.xPos - xPos, 2) + Math.pow(Level.player.yPos- yPos, 2));
			Level.player.hit(10 * (Level.player.xPos - xPos) / h, 10 * (Level.player.yPos- yPos) / h, damage);
		}
	}
	
	private void setSpeed(double vX, double vY) {
		double h = Math.sqrt(Math.pow(vX, 2) + Math.pow(vY, 2));
		xVel = vX * maxV / 10 / h;
		yVel = vY * maxV / 10 / h;
	}
	
	

}
