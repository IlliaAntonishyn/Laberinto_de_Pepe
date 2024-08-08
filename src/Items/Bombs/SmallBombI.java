package Items.Bombs;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.BombEntity;
import Items.A.BombsI;

public class SmallBombI extends BombsI{
	
	public SmallBombI(int quantity) {
		super(1, 1, quantity, 10);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	
	public void action() {
		Level.entityList.add(new BombEntity(Level.player.xPos, Level.player.yPos, 0, damage));
		super.action();
	}

}
