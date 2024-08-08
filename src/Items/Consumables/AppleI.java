package Items.Consumables;

import java.awt.Color;

import A.Level;
import Entities.A.Item;
import Items.A.ConsumablesI;

public class AppleI extends ConsumablesI{
	
	public AppleI(int quantity) {
		super(1, 1, quantity, 10, 10);
		super.item = new Item(0, 0);
		super.item.setItem(this);
		color = Color.red;
	}

}
