package Items.Consumables;

import java.awt.Color;

import Entities.A.Item;
import Items.A.ConsumablesI;

public class CheeseI extends ConsumablesI{

	public CheeseI(int quantity) {
		super(2, 1, quantity, 15, 5);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		color = Color.yellow;
	}
	
}
