package Entities.Auxiliars;

import A.Level;
import Entities.A.Auxiliar;

public class SwordAttack extends Auxiliar{
	
	public SwordAttack(double xPos, double yPos, double angle, int damage) {
		super(xPos + 10 - Math.cos(angle) * 90, yPos + 10 + Math.sin(angle) * 90, 0, 0, 50, 50, damage);
	}
	
	public void updateEntity() {
		if(counter >= 1) {
			Level.entityList.remove(this);
		}
		counter++;
	}

}
