package Entities.A;

import java.awt.Color;

import A.Level;
import Blocks.Block;

public class Entity {
	
	public Color color;

	public int height;
	public int width;
	
	public double xPos;
	public double yPos;
	
	public double xVel;
	public double yVel;
	
	public int damage;
	
	public Entity(double xPos, double yPos, double xVel, double yVel, int width, int height, int damage) {
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.xVel = xVel;
		this.yVel = yVel;
		
		this.width = width;
		this.height = height;
		
		this.damage = damage;
	}
	
	public void updateEntity() {
		checkMovementCollisions(checkMovementCollisions(1));
	}
	
	private double checkMovementCollisions(double time) {		
		double z = time;
		double zb, z1, z2;
		boolean v = true;
		boolean vb;
		int x0, y0, width0, height0;
		for(int i = -1; i < 3; i++) {
			for(int j = -1; j < 3; j++) {
				height0 = Block.cellHeight;
				width0 = Block.cellWidth;
				if(xPos / width0 + j < Level.field[0].length && yPos / height0 + i < Level.field.length 
					&& xPos / width0 + j >= 0 && yPos / height0 + i >= 0 
					&& Level.field[(int) yPos / height0 + i][(int) xPos / width0 + j].solid) {
					x0 = Level.field[(int) yPos / height0 + i][(int) xPos / width0 + j].xPos;
					y0 = Level.field[(int) yPos / height0 + i][(int) xPos / width0 + j].yPos;
					if((xPos + xVel > x0 && xPos + xVel < x0 + width0 || xPos + xVel + width > x0 && xPos + xVel + width < x0 + width0)
						&& (yPos + yVel > y0 && yPos + yVel < y0 + height0 || yPos + yVel + height > y0 && yPos + yVel + height < y0 + height0)){
							z1 = calcZ1((int) yPos / height0 + i, (int) xPos / width0 + j);
							z2 = calcZ2((int) yPos / height0 + i, (int) xPos / width0 + j);
							if(z1 > z2) {
								zb = z1;
								vb = false;
							}else if(z1 < z2){
								zb = z2;
								vb = true;
							}else {
								zb = z1;
								if(x0 < xPos && Level.field[(int) yPos / height0 + i][(int) xPos / width0 + j + 1].solid
									|| x0 > xPos && Level.field[(int) yPos / height0 + i][(int) xPos / width0 + j - 1].solid) vb = true;
								else vb = false;
							}
							if(z > zb && zb >= 0) {
								z = zb;
								v = vb;
							}
					}
				}
			}
		}
		getPos(z);
		if(z != 1) {
			if(v) yVel = 0;
			else xVel = 0;
		}
		return(time - z);
	}
	
	private double calcZ1(int i, int j) {
		if(xVel > 0) {
			return (double) (Level.field[i][j].xPos - xPos - width) / xVel;
		}else if(xVel < 0) {
			return (double) (Level.field[i][j].xPos - xPos + Level.field[i][j].width) / xVel;
		}else return -1;
	}
	
	private double calcZ2(int i, int j) {
		if(yVel > 0) {
			return (double) (Level.field[i][j].yPos - yPos - height) / yVel;
		}else if(yVel < 0) {
			return (double) (Level.field[i][j].yPos - yPos + Level.field[i][j].height) / yVel;
		}else return -1;
	}
	
	private void getPos(double time) {
		xPos += xVel * time;
		yPos += yVel * time;
	}
	
}
