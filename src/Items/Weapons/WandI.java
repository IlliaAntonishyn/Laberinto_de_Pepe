package Items.Weapons;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.MaceAttack;
import Entities.Auxiliars.Projectile;
import Entities.Auxiliars.SwordAttack;
import Items.A.WeaponsI;
import Panels.GamePanel;

public class WandI extends WeaponsI{
	
	public WandI() {
		super(1, 3, 5, 5);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		
		cooldown = 50;
	}
	
	public void action() {
		if(Level.player.stamina >= stamina) {
			Projectile attack;
			double h = Math.sqrt(Math.pow(Level.player.xPos + 25 - GamePanel.xClick, 2) + Math.pow(Level.player.yPos + 25 - GamePanel.yClick, 2));
			if(GamePanel.yClick < Level.player.yPos) attack = new Projectile(Level.player.xPos, Level.player.yPos, -Math.acos((Level.player.xPos + 25 - GamePanel.xClick) / h), Level.player.strength + strength);
			else attack = new Projectile(Level.player.xPos, Level.player.yPos, Math.acos((Level.player.xPos + 25 - GamePanel.xClick) / h), Level.player.strength + strength);
			Level.entityList.add(attack);
			Level.player.immune = attack;
			Level.player.stamina -= stamina;
		}
	}

}
