package Entities.A;

import A.Level;

public class Auxiliar extends Entity{
	
	protected int counter = 0;
		
	public Auxiliar(double xPos, double yPos, double xVel, double yVel, int width, int height, int damage) {
		super(xPos, yPos, xVel, yVel, width, height, damage);
	}
	
	public void updateEntity() {
		xPos += xVel;
		yPos += yVel;
	}

}
