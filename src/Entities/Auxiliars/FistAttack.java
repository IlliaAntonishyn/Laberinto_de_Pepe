package Entities.Auxiliars;

import A.Level;
import Entities.A.Auxiliar;

public class FistAttack extends Auxiliar{
	
	public FistAttack(double xPos, double yPos, double angle, int damage) {
		super(xPos - Math.cos(angle) * 90 + 10, yPos + Math.sin(angle) * 90 + 10, 0, 0, 50, 50, damage);
	}
	
	public void updateEntity() {
		if(counter >= 1) {
			Level.entityList.remove(this);
		}
		counter++;
	}

}
