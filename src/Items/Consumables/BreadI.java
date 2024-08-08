package Items.Consumables;

import java.awt.Color;

import A.Level;
import Entities.A.Item;
import Items.A.ConsumablesI;

public class BreadI extends ConsumablesI{
	
	public BreadI(int quantity) {
		super(1, 2, quantity, 20, 5);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		color = Color.orange;
	}

}
