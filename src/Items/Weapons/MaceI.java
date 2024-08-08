package Items.Weapons;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.DaggerAttack;
import Entities.Auxiliars.FistAttack;
import Entities.Auxiliars.MaceAttack;
import Entities.Auxiliars.Projectile;
import Items.A.WeaponsI;
import Panels.GamePanel;

public class MaceI extends WeaponsI{
	
	public MaceI() {
		super(2, 3, 20, 20);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		
		cooldown = 200;
	}
	
	public void action() {
		if(Level.player.stamina >= stamina) {
			MaceAttack attack;
			double h = Math.sqrt(Math.pow(Level.player.xPos - GamePanel.xClick, 2) + Math.pow(Level.player.yPos - GamePanel.yClick, 2));
			if(GamePanel.yClick < Level.player.yPos) attack = new MaceAttack(Level.player.xPos, Level.player.yPos, -Math.acos((Level.player.xPos - GamePanel.xClick) / h), Level.player.strength + strength);
			else attack = new MaceAttack(Level.player.xPos, Level.player.yPos, Math.acos((Level.player.xPos - GamePanel.xClick) / h), Level.player.strength + strength);
			Level.entityList.add(attack);
			Level.player.immune = attack;
			Level.player.stamina -= stamina;
		}
		
	}

}
