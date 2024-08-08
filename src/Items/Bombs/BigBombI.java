package Items.Bombs;

import A.Level;
import Entities.A.Item;
import Entities.Auxiliars.BombEntity;
import Items.A.BombsI;

public class BigBombI extends BombsI{
	
	public BigBombI(int quantity) {
		super(3, 3, quantity, 30);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	
	public void action() {
		Level.entityList.add(new BombEntity(Level.player.xPos, Level.player.yPos, 2, damage));
		super.action();
	}

}
