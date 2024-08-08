package Items.Bombs;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.BombEntity;
import Items.A.BombsI;

public class MediumBombI extends BombsI{
	
	public MediumBombI(int quantity) {
		super(2, 2, quantity, 20);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	
	public void action() {
		Level.entityList.add(new BombEntity(Level.player.xPos, Level.player.yPos, 1, damage));
		super.action();
	}

}
