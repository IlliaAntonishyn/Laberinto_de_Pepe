package Entities.A;

import A.Level;
import A.Main;

public class Heart extends Entity{
	
	public Heart(int xPos, int yPos) {
		super((double) xPos + 10, (double) yPos + 10, 0, 0, 70, 70, 0);
	}
	
	public void updateEntity() {
		if(500 >= Math.pow(Level.player.xPos - xPos, 2) + Math.pow(Level.player.yPos - yPos, 2)) {
			Main.inGame = false;
			Main.victory = true;
		}
	}

}
