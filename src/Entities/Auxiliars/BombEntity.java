package Entities.Auxiliars;

import A.Level;
import Entities.A.Auxiliar;

public class BombEntity extends Auxiliar{
	
	private int level;
	
	public BombEntity(double xPos, double yPos, int level, int damage) {
		super(xPos, yPos, 0, 0, 70, 70, damage);
		this.level = level;
	}
	
	public void updateEntity() {
		counter++;
		if(counter >= 300) {
			counter = 0;
			switch (level){
				case 0:
					Level.entityList.add(new Explosion(xPos, yPos, damage, 1));
					break;
				case 1:
					Level.entityList.add(new Explosion(xPos, yPos, damage, 2));
					break;
				case 2:
					Level.entityList.add(new Explosion(xPos, yPos, damage, 3));
					break;
			}
			Level.entityList.remove(this);
		}
	}

}
