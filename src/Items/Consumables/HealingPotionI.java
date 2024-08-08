package Items.Consumables;

import java.awt.Color;

import Entities.A.Item;
import Items.A.ConsumablesI;

public class HealingPotionI extends ConsumablesI{
	
	public HealingPotionI(int quantity) {
		super(2, 2, quantity, 0, 30);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		color = Color.pink;
	}

}
