package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class QuickBootsI extends ArmorsI{
	
	public QuickBootsI() {
		super(2, 2, 10, 15, 0);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	


}
