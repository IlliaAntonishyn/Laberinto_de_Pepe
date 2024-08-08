package Items.Weapons;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.MaceAttack;
import Entities.Auxiliars.SpearAttack;
import Entities.Auxiliars.SwordAttack;
import Items.A.WeaponsI;
import Panels.GamePanel;

public class SwordI extends WeaponsI{
	
	public SwordI() {
		super(1, 2, 10, 10);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		
		cooldown = 70;
	}
	
	public void action() {
		if(Level.player.stamina >= stamina) {
			SwordAttack attack;
			double h = Math.sqrt(Math.pow(Level.player.xPos - GamePanel.xClick, 2) + Math.pow(Level.player.yPos - GamePanel.yClick, 2));
			if(GamePanel.yClick < Level.player.yPos) attack = new SwordAttack(Level.player.xPos, Level.player.yPos, -Math.acos((Level.player.xPos - GamePanel.xClick) / h), Level.player.strength + strength);
			else attack = new SwordAttack(Level.player.xPos, Level.player.yPos, Math.acos((Level.player.xPos - GamePanel.xClick) / h), Level.player.strength + strength);
			Level.entityList.add(attack);
			Level.player.immune = attack;
			Level.player.stamina -= stamina;
		}
	}

}
