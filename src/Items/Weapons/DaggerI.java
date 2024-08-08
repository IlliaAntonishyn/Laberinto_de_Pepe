package Items.Weapons;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.DaggerAttack;
import Items.A.WeaponsI;

public class DaggerI extends WeaponsI{
		
	public DaggerI() {
		super(1, 2, 5, 2);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		
		cooldown = 30;
	}
	
	public void action() {
		if(Level.player.stamina >= stamina) {
			DaggerAttack attack = new DaggerAttack(Level.player.xPos, Level.player.yPos, Level.player.strength + strength);
			Level.entityList.add(attack);
			Level.player.immune = attack;
			Level.player.stamina -= stamina;
		}
		
	}

}
